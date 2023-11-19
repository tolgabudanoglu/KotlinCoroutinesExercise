package com.example.coroutineswithroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.coroutineswithroom.model.LoginState
import com.example.coroutineswithroom.model.UserDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(getApplication()).userDao() }

    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun login(username: String, password: String) {
        coroutineScope.launch {
            val user = db.getUser(username)
            if (user == null){
                withContext(Dispatchers.Main){
                    error.value = "user not found"
                }
            }else{
                if (user.passwordHash == password.hashCode()){
                    LoginState.login(user)
                    withContext(Dispatchers.Main){
                        loginComplete.value = true
                    }
                }else{
                    withContext(Dispatchers.Main){
                        error.value = "password is incorrect"
                    }
                }
            }
        }

    }
}