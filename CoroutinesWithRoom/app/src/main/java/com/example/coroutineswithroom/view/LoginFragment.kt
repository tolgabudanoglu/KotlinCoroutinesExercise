package com.example.coroutineswithroom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.coroutineswithroom.databinding.FragmentLoginBinding
import com.example.coroutineswithroom.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)

        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener { onLogin(it) }
        binding.gotoSignupBtn.setOnClickListener { onGotoSignup(it) }

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginComplete.observe(viewLifecycleOwner, Observer { isComplete ->
            Toast.makeText(activity,"login complete",Toast.LENGTH_SHORT).show()
            val action = LoginFragmentDirections.actionGoToMain()
            Navigation.findNavController(binding.loginUsername).navigate(action)

        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(activity,"error : $error",Toast.LENGTH_SHORT).show()


        })
    }

    private fun onLogin(v: View) {
        val userName = binding.loginUsername.text.toString()
        val password = binding.loginPassword.text.toString()
        if (userName.isNullOrEmpty() || password.isNullOrEmpty()){
            Toast.makeText(activity,"fill all field",Toast.LENGTH_SHORT).show()
        }else{
            viewModel.login(userName,password)
        }

    }

    private fun onGotoSignup(v: View){
        val action = LoginFragmentDirections.actionGoToSignup()
        Navigation.findNavController(v).navigate(action)
    }
}