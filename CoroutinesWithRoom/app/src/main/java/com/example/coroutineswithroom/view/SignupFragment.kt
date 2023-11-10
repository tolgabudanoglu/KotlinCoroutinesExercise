package com.example.coroutineswithroom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.coroutineswithroom.databinding.FragmentSignupBinding
import com.example.coroutineswithroom.viewmodel.SignupViewModel


class SignupFragment : Fragment() {

    private var _binding : FragmentSignupBinding? = null
    private lateinit var viewModel: SignupViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)




        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupBtn.setOnClickListener { onSignup(it) }
        binding.gotoLoginBtn.setOnClickListener { onGotoLogin(it) }


        viewModel = ViewModelProvider(this)[SignupViewModel::class.java]
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signupComplete.observe(viewLifecycleOwner, Observer { isComplete ->

        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->

        })
    }

    private fun onSignup(v: View){
        val action = SignupFragmentDirections.actionGoToMain()
        Navigation.findNavController(v).navigate(action)
    }

    private fun onGotoLogin(v: View) {
        val action = SignupFragmentDirections.actionGoToLogin()
        Navigation.findNavController(v).navigate(action)
    }
}


