package com.example.smartcity7.ui.me

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.smartcity7.R
import com.example.smartcity7.network.ServiceCreate
import com.example.smartcity7.network.UserInfo
import kotlinx.android.synthetic.main.fragment_me.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_user.setOnClickListener {
            startActivity(Intent(activity,UserActivity::class.java))
        }

    }

    override fun onResume() {
        super.onResume()

        ServiceCreate.smartcityService.getUserInfo().enqueue(object :Callback<UserInfo>{
            override fun onFailure(p0: Call<UserInfo>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<UserInfo>, p1: Response<UserInfo>) {
                val body = p1.body()
                if (body != null){
                    if (body.code == 200){
                        tv_user.text = body.user.nickName
                        Glide.with(this@MeFragment.requireActivity()).load(ServiceCreate.url + "prod-api/"+body.user.avatar).into(avatar_image)
                    }
                }
            }

        })
    }


}