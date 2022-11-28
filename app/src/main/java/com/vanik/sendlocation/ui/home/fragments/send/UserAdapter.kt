package com.vanik.sendlocation.ui.home.fragments.send

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vanik.sendlocation.data.model.User
import com.vanik.sendlocation.databinding.ItemUserBinding

class UserAdapter(
    var users: List<User>,
//    val onClick: (user: User) -> Unit
) : RecyclerView.Adapter<UserAdapter.UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        return UserHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    @SuppressLint("NotifyDataSetChanged")
    fun setFilterUsers(filterUsers : List<User>){
        users = filterUsers
        notifyDataSetChanged()
    }

    inner class UserHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            binding.tvSendMyLocation.setOnClickListener{
//                onClick.invoke(user)
            }
            binding.tvSendMyLocation.setOnClickListener{
//                onClick.invoke(user)
            }
        }
    }

}