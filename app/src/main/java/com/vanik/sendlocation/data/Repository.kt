package com.vanik.sendlocation.data

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentResolver
import android.content.Context
import android.content.pm.PackageManager
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.vanik.sendlocation.data.model.User
import kotlinx.coroutines.flow.flow
import java.util.jar.Manifest


class Repository(
    private val fAuth: FirebaseAuth,
    private val optionsBuilder : PhoneAuthOptions.Builder,
    private val applicationContext: Application
) {

    fun isUserSignIn() = flow {
        val fUser = fAuth.currentUser
        if (fUser != null) {
            emit(true)
        } else {
            emit(false)
        }
    }

    fun sendSms() = optionsBuilder

    fun verify(options:PhoneAuthOptions){
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun login(firebaseVerifyId: String, verifyNumber: String): Task<AuthResult>  {
        return fAuth.signInWithCredential(credential(firebaseVerifyId, verifyNumber))
    }

    private fun credential(firebaseVerifyCode: String, verifyNumber: String) =
        PhoneAuthProvider.getCredential(firebaseVerifyCode, verifyNumber)

    @SuppressLint("Range")
    fun getContactList(): List<User> {
        val list = arrayListOf<User>()
            val cr: ContentResolver = applicationContext.contentResolver
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
        return list
    }

}