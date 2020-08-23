package com.turina1v.userlist.ui.userlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.turina1v.userlist.R
import com.turina1v.userlist.domain.model.UserModel
import kotlinx.android.synthetic.main.item_user.view.*

class UserRecyclerAdapter : RecyclerView.Adapter<UserRecyclerAdapter.UserViewHolder>() {
    var users: List<UserModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userModel: UserModel) {
            itemView.apply {
                Glide.with(itemView).load(userModel.avatar).into(iv_avatar)
                tv_username.text = userModel.getFullName()
                tv_email.text = userModel.email
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
