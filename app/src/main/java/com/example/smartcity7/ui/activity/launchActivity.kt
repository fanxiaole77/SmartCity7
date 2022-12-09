package com.example.smartcity7.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.bumptech.glide.Glide
import com.example.smartcity7.MainActivity
import com.example.smartcity7.R
import com.example.smartcity7.extesions.sharedPreferences
import com.example.smartcity7.extesions.showToast
import com.example.smartcity7.ui.acctount.LoginActivity
import com.youth.banner.Banner
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity(),View.OnClickListener {

    private val array = arrayListOf(
        R.drawable.launch01,
        R.drawable.launch02,
        R.drawable.launch03,
        R.drawable.launch04,
        R.drawable.launch05
    )

    private var LauchBanner:Banner<Int,BannerImageAdapter<Int>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        supportActionBar?.hide()

        LauchBanner = findViewById(R.id.launch_banner)

        btn_networt.setOnClickListener(this)
        go_home.setOnClickListener(this)


        LauchBanner?.apply {
            setAdapter(object :BannerImageAdapter<Int>(array){
                override fun onBindView(p0: BannerImageHolder?, p1: Int?, p2: Int, p3: Int) {
                    Glide.with(this@LaunchActivity).load(array[p2]).into(p0!!.imageView)
                }
            },false)
            isAutoLoop(false)

            LauchBanner?.addOnPageChangeListener(object :OnPageChangeListener{
                override fun onPageScrollStateChanged(p0: Int) {
                }

                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
                    if (p0 == array.size-1){
                        btn_networt.visibility = View.VISIBLE
                        go_home.visibility = View.VISIBLE
                    }else{
                        btn_networt.visibility = View.GONE
                        go_home.visibility = View.GONE
                    }
                }

                override fun onPageSelected(p0: Int) {
                }
            })
        }

    }

    override fun onClick(v: View?) {

        when(v?.id){

            R.id.go_home -> {
                val  ip = sharedPreferences.getString("ip","")
                val  dk = sharedPreferences.getString("dk","")
                if (TextUtils.isEmpty(ip) && TextUtils.isEmpty(dk)){
                    "网络设置".showToast()
                }else{
                    val  token = sharedPreferences.getString("token","")
                    if (TextUtils.isEmpty(token)){
                        startActivity(Intent(this,LoginActivity::class.java))
                        finish()
                    }else{
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    }
                }
            }

            R.id.btn_networt -> {
                IpActivity(this).apply {
                    showAtLocation(v,Gravity.BOTTOM,width/2,0)
                }
            }

        }

    }
}