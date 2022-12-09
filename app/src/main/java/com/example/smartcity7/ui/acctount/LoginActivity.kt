package com.example.smartcity7.ui.acctount

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity7.MainActivity
import com.example.smartcity7.R
import com.example.smartcity7.extesions.edit
import com.example.smartcity7.extesions.sharedPreferences
import com.example.smartcity7.extesions.showToast
import com.example.smartcity7.network.Login
import com.example.smartcity7.network.Message
import com.example.smartcity7.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        login.setOnClickListener {
            val user =et_user.text.toString()
            val pass = et_pass.text.toString()


            ServiceCreate.smartcityService.postLogin(Login(pass,user)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                   val body = p1.body()
                    if (body != null){
                        if (body.code == 200){

                            body.msg.showToast()
                            sharedPreferences.edit {
                                putString("token",body.token)
                            }
                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                            finish()
                        }else{
                            body.msg.showToast()
                        }
                    }
                }
            })

        }

    }
}