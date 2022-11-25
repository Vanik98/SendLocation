package com.vanik.sendlocation.ui.fragments.send

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
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
import com.vanik.sendlocation.ui.adapters.UserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SendFragment : Fragment() {
    private val viewModel: SendViewModel by viewModel()
    private lateinit var binding: FragmentSendBinding
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

    private fun setupViews(){
        initializeAdapter()
    }

    private fun initializeAdapter() {
        val resultRecyclerView = binding.userRecyclerView
        val layoutManager = LinearLayoutManager(requireActivity())
        resultRecyclerView.layoutManager = layoutManager
        resultRecyclerView.adapter = UserAdapter(getContactList())
    }

    @SuppressLint("Range")
    fun getContactList(): List<User> {
        val list = arrayListOf<User>()
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_CONTACTS), 100)
        } else {
            val cr: ContentResolver = requireContext().contentResolver
            val cur = cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null
            )
            if ((cur?.count ?: 0) > 0) {
                while (cur != null && cur.moveToNext()) {
                    val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                    val name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        val pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", arrayOf(id), null)
                        while (pCur!!.moveToNext()) {
                            val phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            val user = User(fullName = "Full name: $name", phoneNumber = "Phone number: $phoneNo")
                            list.add(user)
                        }
                        pCur.close()
                    }
                }
            }
            cur?.close()
        }
        return list
    }
}