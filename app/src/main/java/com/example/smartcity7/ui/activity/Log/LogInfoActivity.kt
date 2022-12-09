package com.example.smartcity7.ui.activity.Log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.extesions.showToast
import com.example.smartcity7.network.LogInfo
import com.example.smartcity7.network.MyVisList
import com.example.smartcity7.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_log_info.*
import kotlinx.android.synthetic.main.activity_sv.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_info)

        supportActionBar?.hide()

        val query = intent.getStringExtra("query")

        sear_wl.onActionViewExpanded()

        sear_wl.queryHint = query

        ServiceCreate.smartcityService.getLogInfo(query).enqueue(object :Callback<LogInfo>{
            override fun onFailure(p0: Call<LogInfo>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LogInfo>, p1: Response<LogInfo>) {
                val body = p1.body()
                if (body != null){
                    Glide.with(this@LogInfoActivity).load(ServiceCreate.url + body.data.company.imgUrl).into(wl_logo)
                    wl_name.text = body.data.company.name
                    wl_phone.text = body.data.company.phone
                    Log.d("body","${body.data.stepList}")
                    val adapter = ItemAdapter(R.layout.item_kd,body.data.stepList,LH::class.java)
                    rv_kd.layoutManager = LinearLayoutManager(this@LogInfoActivity)
                    rv_kd.adapter =adapter

                }
            }
        })

    }
}

class LH(view: View):ItemAdapter.MyViewHolder(view){
    val cs:TextView = view.findViewById(R.id.wl_cs)
    val sj:TextView = view.findViewById(R.id.wl_sj)
    val zt:TextView = view.findViewById(R.id.wl_zt)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        cs.text = (data[position] as LogInfo.Data.Step).addressAt
        sj.text = (data[position] as LogInfo.Data.Step).eventAt
        zt.text = (data[position] as LogInfo.Data.Step).stateName
    }
}