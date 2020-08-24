package com.turina1v.userlist.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.turina1v.userlist.R
import com.turina1v.userlist.ui.userlist.UserListFragment
import com.turina1v.userlist.ui.userlist.UserListFragment.Companion.KEY_USER_MODEL
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.item_user.view.*

class UserDetailsFragment : Fragment() {
    lateinit var viewModel: UserDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserDetailsViewModel::class.java)
        viewModel.setUser(arguments?.getParcelable(KEY_USER_MODEL))
        viewModel.userLiveData.observe(this, Observer { user ->
            Glide.with(this).load(user.avatar).into(ivAvatar)
            editTextFirstName.setText(user.firstName)
            editTextLastName.setText(user.lastName)
            editTextEmail.setText(user.email)
        })
        buttonSave.setOnClickListener {
            viewModel.saveUser(
                editTextFirstName.text.toString(),
                editTextLastName.text.toString(),
                editTextEmail.text.toString()
            )
            backToUserList()
        }

        buttonDelete.setOnClickListener {
            viewModel.deleteUser()
            backToUserList()
        }
    }

    fun backToUserList(){
        activity?.let {
            it.supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer,
                UserListFragment()
            ).commit()
        }
    }
}
