package com.example.assignmentwbpo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmentwbpo.adapter.UserRecyclerAdapter
import com.example.assignmentwbpo.data.UserData
import com.example.assignmentwbpo.databinding.FragmentUserListBinding
import com.example.assignmentwbpo.utils.CustomDialog
import com.example.assignmentwbpo.viewmodel.UiStateDialog
import com.example.assignmentwbpo.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: UserRecyclerAdapter
    var userList = ArrayList<UserData>()
    val viewModel by viewModels<UsersViewModel>()
    lateinit var recyclerView: RecyclerView
    lateinit var customDialog: CustomDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        recyclerView = binding.usersRecyclerview
        setAdapter()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.userList()
        }

        lifecycleScope.launch {
            viewModel.userData.collect {
                recyclerView
            }
        }

        lifecycleScope.launch {
            viewModel.dialogState.collect {
                when(it) {
                    is UiStateDialog.Loading ->
                        binding.progressBar.visibility = View.VISIBLE
                    is UiStateDialog.Failed -> {
                        binding.progressBar.visibility = View.GONE
                        customDialog.setProgressDialog(
                            context = requireContext(),
                            message = "Failed to load user list"
                        )
                    }
                    is UiStateDialog.Success -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        adapter = UserRecyclerAdapter(userList, object : EventListener<UserData> {
            override fun onItemClick(pos: Int, item: UserData, view: View) {

            }
        })
        binding.usersRecyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}