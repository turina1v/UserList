package com.turina1v.userlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.turina1v.userlist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, UserListFragment()).commit()
    }
}
