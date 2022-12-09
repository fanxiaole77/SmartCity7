package com.example.smartcity7.ui.activity.Log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.extesions.showToast
import com.example.smartcity7.network.LogBanner
import com.example.smartcity7.network.LogInfo
import com.example.smartcity7.network.LogList
import com.example.smartcity7.network.ServiceCreate
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import kotlinx.android.synthetic.main.activity_log.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.IDN

class LogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        supportActionBar?.hide()

        wl_cx.onActionViewExpanded() //显示搜索

        wl_cx.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                ServiceCreate.smartcityService.getLogInfo(query.toString()).enqueue(object:Callback<LogInfo>{
                    override fun onFailure(p0: Call<LogInfo>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<LogInfo>, p1: Response<LogInfo>) {
                       val body = p1.body()
                        if (body!= null){
                            if (body.code == 200){
                                startActivity(Intent(this@LogActivity,LogInfoActivity::class.java).apply {
                                    putExtra("query",query)
                                })
                                body.msg.showToast()
                            }else{
                                body.msg.showToast()
                            }
                        }
                    }
                })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        fg.setOnClickListener { finish() }
        fg.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)

        ServiceCreate.smartcityService.getLogBanner().enqueue(object :Callback<LogBanner>{
            override fun onFailure(p0: Call<LogBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LogBanner>, p1: Response<LogBanner>) {
               val body = p1.body()
                if (body != null){
                    log_banner.adapter = object :BannerImageAdapter<LogBanner.Data>(body.data){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: LogBanner.Data?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(this@LogActivity).load(ServiceCreate.url + p1!!.imgUrl).into(p0!!.imageView)
                        }
                    }
                }
            }
        })

        ServiceCreate.smartcityService.getLogList(null).enqueue(object :Callback<LogList>{
            override fun onFailure(p0: Call<LogList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LogList>, p1: Response<LogList>) {
              val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_service,body.data,UJ::class.java,12)
                    rv_log_list.layoutManager = GridLayoutManager(this@LogActivity,4)
                    rv_log_list.adapter = adapter

                }
            }
        })

        ServiceCreate.smartcityService.getLogList(null).enqueue(object :Callback<LogList>{
            override fun onFailure(p0: Call<LogList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<LogList>, p1: Response<LogList>) {
               val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_log,body.data,WL::class.java)
                    rv_log.layoutManager = LinearLayoutManager(this@LogActivity)
                    rv_log.adapter = adapter
                }
            }
        })

    }
}

class UJ(view: View):ItemAdapter.MyViewHolder(view){
    val image: ImageView = view.findViewById(R.id.service_image)
    val name: TextView = view.findViewById(R.id.service_text)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as LogList.Data).name
        Glide.with(image).load(ServiceCreate.url + (data[position] as LogList.Data).imgUrl).into(image)
        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,LogContentActivity::class.java).apply {
                putExtra("log_id",(data[position] as LogList.Data).id)
            })
        }
    }
}

class WL(view:View):ItemAdapter.MyViewHolder(view){
    val image: ImageView = view.findViewById(R.id.log_image)
    val name: TextView = view.findViewById(R.id.log_wl)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as LogList.Data).name
        Glide.with(image).load(ServiceCreate.url + (data[position] as LogList.Data).imgUrl).into(image)

    }
}