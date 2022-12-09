package com.example.smartcity7.ui.activity.part

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.network.PartContent
import com.example.smartcity7.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_part_contnet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartContnetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part_contnet)

        supportActionBar?.hide()

        val id = intent.getIntExtra("part_id",0)
        val time = intent.getStringExtra("part_time")

        ServiceCreate.smartcityService.getPartContent(id).enqueue(object:Callback<PartContent>{
            override fun onFailure(p0: Call<PartContent>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<PartContent>, p1: Response<PartContent>) {
                val body = p1.body()
                if (body != null){
                   start.text = body.data.first
                   end.text = body.data.end
                   sysj.text = time
                   jgjz.text = body.data.stationsNumber.toString()
                   syjl.text = body.data.km.toString()

                    val adapter  =ItemAdapter(R.layout.item_partcontent,body.data.metroStepList,NH::class.java)
                    rv_content.layoutManager = LinearLayoutManager(this@PartContnetActivity).apply {
                        orientation = LinearLayoutManager.HORIZONTAL
                    }
                    rv_content.adapter = adapter

                }
            }
        })

    }
}

class NH(view: View):ItemAdapter.MyViewHolder(view){
    val name:TextView = view.findViewById(R.id.zhandian)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as PartContent.Data.MetroStep).name
    }
}