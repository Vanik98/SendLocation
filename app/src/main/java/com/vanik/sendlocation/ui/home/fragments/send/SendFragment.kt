package com.vanik.sendlocation.ui.home.fragments.send

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.vanik.sendlocation.R
import com.vanik.sendlocation.data.model.User
import com.vanik.sendlocation.databinding.FragmentSendBinding
import com.vanik.sendlocation.ui.home.HomeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendFragment : Fragment() {
    private val viewModel: SendViewModel by viewModel()
    private lateinit var binding: FragmentSendBinding
    private lateinit var adapter:UserAdapter
    private val users = arrayListOf<User>()

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
        binding.userSearchView.clearFocus()
        binding.userSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                return false
            }

            override fun onQueryTextChange(str: String): Boolean {
                filterList(str)
                return true
            }

        })
        return binding.root
    }

    fun filterList(searchName: String){
        val filterList = arrayListOf<User>()
        for (user in users){
            if(user.fullName.toLowerCase().contains(searchName.toLowerCase()) || user.phoneNumber.toLowerCase().contains(searchName.toLowerCase())){
                    filterList.add(user)
                (requireActivity() as HomeActivity).logChat(filterList.size.toString())
            }
        }
        if(filterList.isEmpty()){
            adapter.setFilterUsers(users)
            (requireActivity() as HomeActivity).showMessage("no data")
        }else{
            adapter.setFilterUsers(filterList)
        }
    }

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
                users.addAll(it)
                adapter.notifyDataSetChanged()
                (requireActivity() as HomeActivity).logChat(it[0].fullName)
                (requireActivity() as HomeActivity).closeDialog()
            }
        }
        return users
    }
}