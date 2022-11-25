package com.vanik.sendlocation.domain

import android.content.Context
import com.vanik.sendlocation.data.Repository

class GetContactsUseCase(private val repository: Repository){
    fun execute() = repository.getContactList()
}