package com.example.pagingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pagingdemo.model.User
import com.example.pagingdemo.model.UserResponse
import com.example.pagingdemo.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

  lateinit var viewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initViewModel()
        buttonSubmit.setOnClickListener {
           createUser()
        }
    }

    private fun createUser() {
        progressBar.visibility = View.VISIBLE
        val user = User("", editUserName.text.toString(), editjob.text.toString())
        viewModel.createUser(user)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.getCreateNewUserObserver().observe(this, Observer <UserResponse?>{
           progressBar.visibility = View.GONE
            if(it == null) {
                Toast.makeText(this@LoginActivity, "Failed to create User", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(this@LoginActivity,it.id + "\n"+  it.name + "\n"+  it.job,Toast.LENGTH_LONG).show()
                Toast.makeText(this@LoginActivity, "User Create Successfully", Toast.LENGTH_LONG).show()
            }
        })
    }
}