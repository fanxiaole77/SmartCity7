package com.example.smartcity7.ui.me

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity7.R
import com.example.smartcity7.extesions.showToast
import com.example.smartcity7.network.Feed
import com.example.smartcity7.network.Message
import com.example.smartcity7.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_feed.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        supportActionBar?.hide()

        save3.setOnClickListener {
            val content = content_feed.text.toString()
            ServiceCreate.smartcityService.postFeed(Feed(content,"")).enqueue(object :Callback<Message>{
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