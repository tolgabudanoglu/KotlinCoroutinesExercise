package com.example.coroutineswithroom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.coroutineswithroom.databinding.FragmentMainBinding
import com.example.coroutineswithroom.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signoutBtn.setOnClickListener { onSignout() }
        binding.deleteUserBtn.setOnClickListener { onDelete() }


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.signout.observe(viewLifecycleOwner, Observer {

        })
        viewModel.userDeleted.observe(viewLifecycleOwner, Observer {

        })
    }

    private fun onSignout() {
        val action = MainFragmentDirections.actionGoToSignup()
        Navigation.findNavController(binding.usernameTV).navigate(action)
    }

    private fun onDelete() {
        val action = MainFragmentDirections.actionGoToSignup()
        Navigation.findNavController(binding.usernameTV).navigate(action)
    }

}