package com.example.smartcity7.ui.activity.pet

import android.app.Fragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.network.Inquiry
import com.example.smartcity7.network.MyVisList
import com.example.smartcity7.network.PetType
import com.example.smartcity7.network.ServiceCreate
import com.example.smartcity7.ui.activity.pet.Fragment.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_pet.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet)

        supportActionBar?.hide()

        ServiceCreate.smartcityService.getPetType().enqueue(object : Callback<PetType> {
            override fun onFailure(p0: Call<PetType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<PetType>, p1: Response<PetType>) {
                val body = p1.body()
                if (body != null) {

                    val adapter = ItemAdapter(R.layout.item_service, body.data, HH::class.java, 10)
                    rv_pet.layoutManager = GridLayoutManager(this@PetActivity, 5)
                    rv_pet.adapter = adapter
                }
            }

        })



        ServiceCreate.smartcityService.getInquiry().enqueue(object : Callback<Inquiry> {
            override fun onFailure(p0: Call<Inquiry>, p1: Throwable) {
                Log.i("abab","${p1.message}")
            }

            override fun onResponse(p0: Call<Inquiry>, p1: Response<Inquiry>) {
                val body = p1.body()
                if (body != null) {
                    Log.d("abab","${body.rows}")
                    val adapter = ItemAdapter(R.layout.item_vis, body.rows, JJ::class.java)
                    rv_int.layoutManager = LinearLayoutManager(this@PetActivity)
                    rv_int.adapter = adapter
                }
            }

        })


    }

    override fun onResume() {
        super.onResume()
        ServiceCreate.smartcityService.getMyVisList().enqueue(object : Callback<MyVisList> {
            override fun onFailure(p0: Call<MyVisList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<MyVisList>, p1: Response<MyVisList>) {
                val body = p1.body()
                if (body != null) {
                    Log.d("ava","${body.rows}")
                    val adapter = ItemAdapter(R.layout.item_vis, body.rows, II::class.java)
                    rv_vis.layoutManager = LinearLayoutManager(this@PetActivity)
                    rv_vis.adapter = adapter
                }
            }

        })
    }

}

class HH(view: View):ItemAdapter.MyViewHolder(view){
    val name: TextView = view.findViewById(R.id.service_text)
    val image: ImageView = view.findViewById(R.id.service_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as PetType.Data).name
        Glide.with(image).load(ServiceCreate.url + (data[position] as PetType.Data).imgUrl).into(image)

        itemView.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,PetDoctorActivity::class.java).apply {
                putExtra("pet_id",(data[position] as PetType.Data).id)
            })
        }

    }
}

class II(view: View):ItemAdapter.MyViewHolder(view){
    val name: TextView = view.findViewById(R.id.vis_text)
    val content: TextView = view.findViewById(R.id.vis_content)
    val image: ImageView = view.findViewById(R.id.vis_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as MyVisList.Row).doctor?.name
        content.text = (data[position] as MyVisList.Row).question
        Glide.with(image).load(ServiceCreate.url + (data[position] as MyVisList.Row).doctor?.avatar).into(image)
    }
}

class JJ(view: View):ItemAdapter.MyViewHolder(view){
    val name: TextView = view.findViewById(R.id.vis_text)
    val content: TextView = view.findViewById(R.id.vis_content)
    val image: ImageView = view.findViewById(R.id.vis_image)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        name.text = (data[position] as Inquiry.Row).doctor.name
        content.text = (data[position] as Inquiry.Row).question
        Glide.with(image).load(ServiceCreate.url + (data[position] as Inquiry.Row).doctor.avatar).into(image)
    }
}