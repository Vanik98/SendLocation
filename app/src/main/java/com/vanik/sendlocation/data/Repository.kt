package com.vanik.sendlocation.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

class Repository( private val fAuth: FirebaseAuth) {

    fun isUserSignIn() = flow {
        val fUser = fAuth.currentUser
        if (fUser != null) {
            emit(true)
        } else {
            emit(false)
        }
    }

    fun registration(phoneNumber: String): LiveData<Boolean> {
        var liveData = MutableLiveData<Boolean>()
//        val phoneAuthOptions = PhoneAuthOptions.newBuilder()
//            .setPhoneNumber(phoneNumber)
//            .setTimeout(60L, TimeUnit.SECONDS)
//            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                override fun onVerificationCompleted(p: PhoneAuthCredential) {
//                    liveData.value = true
//                }
//
//                override fun onVerificationFailed(p0: FirebaseException) {
//                    liveData.value = false
//                }
//
//            })
//            .build()
        return liveData
//         if(fAuth.currentUser != null){
//             if(fAuth.currentUser?.phoneNumber?.isEmpty() == false){
//                 //signin
//                 emit(true)
//             }else{
//                 //
//                 emit(falsew)
//             }
//         }

    }

}