package com.example.smartcity7.ui.activity.part

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.network.MetroList
import com.example.smartcity7.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_part.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 城市地铁
 */

class PartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_part)
        supportActionBar?.hide()

        ServiceCreate.smartcityService.getMetroList().enqueue(object :Callback<MetroList>{
            override fun onFailure(p0: Call<MetroList>, p1: Throwable) {
                Log.i("prat",p1.message)
            }

            override fun onResponse(p0: Call<MetroList>, p1: Response<MetroList>) {
                val body = p1.body()
                Log.d("part","{${body!!.data}}")
                if (body != null){
                    val adapter  =ItemAdapter(R.layout.item_part,body.data,YU::class.java)
                    rv_part.layoutManager  =LinearLayoutManager(this@PartActivity)
                    rv_part.adapter = adapter
                }
            }
        })

    }
}
class YU(view:View):ItemAdapter.MyViewHolder(view){

    val jihao:TextView = view.findViewById(R.id.jihao)
    val xiyizhan:TextView = view.findViewById(R.id.xiyizhan)
    val shichang:TextView = view.findViewById(R.id.shichang)

    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        jihao.text = (data[position] as MetroList.Data).lineName
        xiyizhan.text = (data[position] as MetroList.Data).nextStep.name
        shichang.text = (data[position] as MetroList.Data).reachTime.toString()

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,PartContnetActivity::class.java).apply {
                putExtra("part_id",(data[position] as MetroList.Data).lineId)
                putExtra("part_time",(data[position] as MetroList.Data).reachTime.toString())
            })
        }
    }

}