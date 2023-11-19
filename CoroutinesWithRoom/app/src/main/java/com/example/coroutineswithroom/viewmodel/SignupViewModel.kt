package com.example.coroutineswithroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.coroutineswithroom.model.LoginState
import com.example.coroutineswithroom.model.User
import com.example.coroutineswithroom.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }

    val signupComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun signup(username: String, password: String, info: String) {
        coroutineScope.launch {
            val user : User = db.getUser(username)
            if (user !=null){
                withContext(Dispatchers.Main){
                    error.value = "user already exist"
                }
            }else{
                val user = User(username,password.hashCode(),info)
                val userId:Long = db.insertUser(user)
                user.id = userId
                LoginState.login(user)
                withContext(Dispatchers.Main){
                    signupComplete.value = true
                }
            }
        }

    }

}