package com.example.assignmentwbpo.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.transition.Visibility
import com.example.assignmentwbpo.R
import com.example.assignmentwbpo.databinding.FragmentRegistrationBinding
import com.example.assignmentwbpo.viewmodel.RegistrationResponse
import com.example.assignmentwbpo.viewmodel.UiStateDialog
import com.example.assignmentwbpo.viewmodel.UsersViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    var progressBar: ProgressBar()
    val viewModel by viewModels<UsersViewModel>()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        progressBar = binding.progressBar

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

        binding.btnRegister.setOnClickListener {
            lifecycleScope.launch {
                viewModel.userRegistration(
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
            }
        }

        lifecycleScope.launch {
            viewModel.loginState.collect {
                if(it is RegistrationResponse.Failed) {
                    Snackbar.make(binding.root, "Registration Failed", Toast.LENGTH_LONG).show()
                }
            }
        }
        lifecycleScope.launch {
            viewModel.dialogState.collect {
                when(it) {
                    is UiStateDialog.Loading ->
                        progressBar.visibility = View.VISIBLE
                    is UiStateDialog.Failed ->
                        notifDialog.visibility = View.VISIBLE
                    is UiStateDialog.Success ->
                        progressDialog.visibility = View.GONE
                        notifDialog.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}