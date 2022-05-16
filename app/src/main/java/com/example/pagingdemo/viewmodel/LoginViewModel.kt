package com.example.pagingdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pagingdemo.model.User
import com.example.pagingdemo.model.UserResponse
import com.example.pagingdemo.network.CreateUser
import com.example.pagingdemo.network.RetroInstance
import com.example.pagingdemo.network.RetrofitModule
import com.example.pagingdemo.network.UserAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel: ViewModel() {

     lateinit var createNewUser: MutableLiveData<UserResponse?>

     init {
         createNewUser = MutableLiveData()
     }

     fun  getCreateNewUserObserver() : MutableLiveData<UserResponse?> {
         return createNewUser
     }

     fun createUser(user:User) {
         val retroservices = RetroInstance.getRetroInstance().create(CreateUser::class.java)
         val call = retroservices.createUser(user)
         call.enqueue(object : Callback<UserResponse>{
             override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                 if(response.isSuccessful){
                     createNewUser.postValue(response.body())
                 }else {
                     createNewUser.postValue(null)
                 }
             }
             override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                 createNewUser.postValue(null)
             }
         })
     }

}