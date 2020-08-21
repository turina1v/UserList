package com.turina1v.userlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turina1v.userlist.R
import com.turina1v.userlist.data.entity.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserRecyclerAdapter : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {
    var users: List<User> = listOf(User("email", "first name", "last name", "https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg"))
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.apply {
                Glide.with(itemView).load(user.avatar).into(iv_avatar)
                tv_username.text = user.firstName
                tv_email.text = user.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(users[position])

    override fun getItemCount(): Int = users.size
}
