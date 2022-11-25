package com.vanik.sendlocation.ui.home.fragments.send

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanik.sendlocation.R
import com.vanik.sendlocation.data.model.User
import com.vanik.sendlocation.databinding.FragmentSendBinding
import com.vanik.sendlocation.ui.base.BaseActivity
import com.vanik.sendlocation.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendFragment : Fragment() {
    private val viewModel: SendViewModel by viewModel()
    private lateinit var binding: FragmentSendBinding
    private lateinit var adapter:UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as HomeActivity).showDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_send, container, false)
        setupViews()
        return binding.root
    }

//    private fun getContacts() = context?.let { viewModel.getContacts(it) }

    private fun setupViews() {
        initializeAdapter()
    }

    private fun initializeAdapter() {
        val resultRecyclerView = binding.userRecyclerView
        val layoutManager = LinearLayoutManager(requireActivity())
        resultRecyclerView.layoutManager = layoutManager
        resultRecyclerView.adapter = UserAdapter(getContactList())
        adapter = resultRecyclerView.adapter as UserAdapter
    }

    @SuppressLint("Range", "NotifyDataSetChanged")
    fun getContactList(): List<User> {
        val list = arrayListOf<User>()
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS),
                100
            )
        } else {
            viewModel.getContacts().observe(requireActivity()) {
                list.addAll(it)
                adapter.notifyDataSetChanged()
                (requireActivity() as HomeActivity).logChat(it[0].fullName)
                (requireActivity() as HomeActivity).closeDialog()
            }
        }
        return list
    }
}