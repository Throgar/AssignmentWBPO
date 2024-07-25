package com.example.assignmentwbpo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.assignmentwbpo.R
import com.example.assignmentwbpo.databinding.FragmentRegistrationBinding
import com.example.assignmentwbpo.viewmodel.RegistrationResponse
import com.example.assignmentwbpo.viewmodel.UsersViewModel
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    val viewModel by viewModels<UsersViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)

        if (viewModel.hasToken()) {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        binding.buttonFirst.setOnClickListener {
            lifecycleScope.launch {
                viewModel.userRegistration(binding.email, binding.pass)
            }
        }

        lifecycleScope.launch {
            viewModel.loginState.collect {
                if(it is RegistrationResponse.Failed) {
                    //Show dialog
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}