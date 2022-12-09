package com.example.smartcity7.ui.activity.pet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.network.Doctor
import com.example.smartcity7.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_pet_doctor.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_doctor)

        supportActionBar?.hide()


        val id = intent.getIntExtra("pet_id",0)
        ServiceCreate.smartcityService.getDoctor(id).enqueue(object :Callback<Doctor>{
            override fun onFailure(p0: Call<Doctor>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<Doctor>, p1: Response<Doctor>) {
                val body = p1.body()
                if (body!= null){
                    val adapter = ItemAdapter(R.layout.item_docotor,body.rows,KK::class.java)
                    rv_do.layoutManager = LinearLayoutManager(this@PetDoctorActivity)
                    rv_do.adapter = adapter
                }
            }

        })

    }
}

class KK(view: View):ItemAdapter.MyViewHolder(view){
    val image:ImageView = view.findViewById(R.id.do_image)
    val name:TextView = view.findViewById(R.id.do_name)
    val bianhao:TextView = view.findViewById(R.id.bianhao)
    val miaoshu:TextView = view.findViewById(R.id.miaoshu)
    val nianxian:TextView = view.findViewById(R.id.nianxian)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        Glide.with(image).load(ServiceCreate.url + (data[position] as Doctor.Row).avatar).into(image)
        name.text = (data[position] as Doctor.Row).name
        bianhao.text = (data[position] as Doctor.Row).practiceNo
        miaoshu.text = (data[position] as Doctor.Row).goodAt

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,WenActivity::class.java).apply {
                putExtra("do_id",(data[position] as Doctor.Row).id)
            })
        }

    }

}