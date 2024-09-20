package com.jmantello.notificationhub.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jmantello.notificationhub.R
import com.jmantello.notificationhub.adapters.NotificationAdapter
import com.jmantello.notificationhub.data.NotificationDao
import com.jmantello.notificationhub.data.NotificationDatabase
import com.jmantello.notificationhub.data.NotificationRepository
import com.jmantello.notificationhub.data.NotificationViewModel
import com.jmantello.notificationhub.data.NotificationViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotificationFragment : Fragment() {

    private lateinit var viewModel: NotificationViewModel
    private lateinit var adapter: NotificationAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize items
        val dao = NotificationDatabase.getInstance(requireContext()).notificationDao()
        val repository = NotificationRepository(dao)
        val factory = NotificationViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[NotificationViewModel::class.java]

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = NotificationAdapter()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        observeNotifications()
    }

    private fun observeNotifications() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.notifications.collectLatest { notifications ->
                adapter.submitList(notifications)
            }
        }
    }
}