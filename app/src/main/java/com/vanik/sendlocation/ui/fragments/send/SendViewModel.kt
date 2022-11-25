package com.vanik.sendlocation.ui.fragments.send

import android.content.Context
import androidx.lifecycle.ViewModel
import com.vanik.sendlocation.domain.GetContactsUseCase

class SendViewModel(
    private val getContactsUseCase: GetContactsUseCase
) : ViewModel(){
    fun getContacts(context : Context) = getContactsUseCase.execute(context)
}