package com.turina1v.userlist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.turina1v.userlist.R
import com.turina1v.userlist.ui.userlist.UserListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) setUserListFragment()
    }

    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (f is UserListFragment) {
            finish()
        } else {
            setUserListFragment()
        }
    }

    private fun setUserListFragment(){
        supportFragmentManager.beginTransaction().replace(
            R.id.fragmentContainer,
            UserListFragment()
        ).commit()
    }
}
