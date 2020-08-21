package com.turina1v.userlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.turina1v.userlist.R
import kotlinx.android.synthetic.main.fragment_list.*

class UserListFragment: Fragment() {
    lateinit var adapter: UserRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = UserRecyclerAdapter()
        recyclerUsers.adapter = adapter
    }

}
