package com.vanik.sendlocation.data

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.coroutines.flow.flow

class Repository(
    private val fAuth: FirebaseAuth,
    private val optionsBuilder : PhoneAuthOptions.Builder
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

}