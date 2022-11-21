package com.vanik.sendlocation.data

import com.google.firebase.auth.FirebaseAuth

class Repository {
 val fAuth = FirebaseAuth.getInstance()

 val fUser = fAuth.currentUser
 fun x(){
    if (fUser != null){

    }else{
       print("login exac chi")
    }
 }

}