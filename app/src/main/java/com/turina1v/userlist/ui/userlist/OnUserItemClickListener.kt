package com.turina1v.userlist.ui.userlist

import com.turina1v.userlist.domain.model.UserModel

interface OnUserItemClickListener {
    fun onUserItemClick(userModel: UserModel)
}
