package com.example.smartcity7.ui.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity7.R
import com.example.smartcity7.extesions.showToast
import com.example.smartcity7.network.Message
import com.example.smartcity7.network.Pass
import com.example.smartcity7.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_pass.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass)

        supportActionBar?.hide()
        ww.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        ww.setOnClickListener { finish() }

        save2.setOnClickListener {
            val ord = et_ord.text.toString()
            val new = et_new.text.toString()
            ServiceCreate.smartcityService.putPass(Pass(new,ord)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                   val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
                            body.msg.showToast()
                        }else{
                            body.msg.showToast()
                        }
                    }
                }

            })
        }

    }
}