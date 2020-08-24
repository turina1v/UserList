package com.turina1v.userlist.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.turina1v.userlist.R
import com.turina1v.userlist.domain.model.UserModel
import com.turina1v.userlist.ui.userdetails.UserDetailsFragment
import kotlinx.android.synthetic.main.fragment_list.*

class UserListFragment : Fragment(), OnUserItemClickListener {
    lateinit var adapter: UserRecyclerAdapter
    lateinit var viewModel: UserListViewModel

    companion object{
        const val KEY_USER_MODEL = "key_user_model"
    }

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
        adapter.onUserItemClickListener = this
        recyclerUsers.adapter = adapter
        recyclerUsers.addItemDecoration(ListItemDecoration(20))

        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)
        viewModel.usersLiveData.observe(this, Observer { users ->
            adapter.users = users
            recyclerUsers.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            textLoadError.visibility = View.GONE
        })
        viewModel.loaderLiveData.observe(this, Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                recyclerUsers.visibility = View.GONE
                textLoadError.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
            }
        })
        viewModel.errorLiveData.observe(this, Observer { errorMessage ->
            textLoadError.visibility = View.VISIBLE
            textLoadError.text = errorMessage
            progressBar.visibility = View.GONE
            recyclerUsers.visibility = View.GONE
        })

        buttonReloadUsers.setOnClickListener {
            viewModel.reloadUsers()
        }
    }

    override fun onUserItemClick(userModel: UserModel) {
        activity?.let {
            val detailsFragment = UserDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_USER_MODEL, userModel)
            detailsFragment.arguments = bundle
            it.supportFragmentManager.beginTransaction().replace(
                R.id.fragmentContainer,
                detailsFragment
            ).commit()
        }
    }


}
