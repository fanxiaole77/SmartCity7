package com.example.smartcity7.ui.activity.Log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.smartcity7.R
import com.example.smartcity7.network.HomeBanner
import com.example.smartcity7.network.LogContent
import com.example.smartcity7.network.ServiceCreate
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_log_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_content)

        supportActionBar?.hide()

        val id = intent.getIntExtra("log_id",0)

        ServiceCreate.smartcityService.getLogContent(id).enqueue(object :Callback<LogContent>{
            override fun onFailure(p0: Call<LogContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LogContent>, p1: Response<LogContent>) {
                val body = p1.body()
                if (body != null){

                    logo_jj.text = body.data.introduce
                    logo_ys.text = body.data.shippingMethod

                }
            }
        })

        ServiceCreate.smartcityService.getHomeBanner().enqueue(object :Callback<HomeBanner>{
            override fun onFailure(p0: Call<HomeBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeBanner>, p1: Response<HomeBanner>) {
                val body = p1.body()
                if (body != null){
                    logo_banner.adapter = object :BannerImageAdapter<HomeBanner.Row>(body.rows){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: HomeBanner.Row?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(this@LogContentActivity).load(ServiceCreate.url + p1!!.advImg).into(p0!!.imageView)
                        }

                    }
                }
            }
        })

    }
}