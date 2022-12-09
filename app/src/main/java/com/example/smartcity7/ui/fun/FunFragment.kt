package com.example.smartcity7.ui.`fun`

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity7.MainActivity
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.network.HomeSerive
import com.example.smartcity7.network.ServiceCreate
import com.example.smartcity7.ui.activity.Log.LogActivity
import com.example.smartcity7.ui.activity.part.PartActivity
import com.example.smartcity7.ui.activity.pet.PetActivity
import kotlinx.android.synthetic.main.fragment_fun.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FunFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fun, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ServiceCreate.smartcityService.getHomeService(null).enqueue(object :Callback<HomeSerive>{
            override fun onFailure(p0: Call<HomeSerive>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeSerive>, p1: Response<HomeSerive>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_service,body.rows,GG::class.java)
                    rv_fun.layoutManager = GridLayoutManager(this@FunFragment.requireActivity(),5)
                    rv_fun.adapter = adapter
                }
            }

        })

        fun_sear.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                ServiceCreate.smartcityService.getHomeService(newText).enqueue(object :Callback<HomeSerive>{
                    override fun onFailure(p0: Call<HomeSerive>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<HomeSerive>, p1: Response<HomeSerive>) {
                       val body = p1.body()
                        if (body != null){
                            val adapter = ItemAdapter(R.layout.item_service,body.rows,GG::class.java)
                            rv_fun.layoutManager = GridLayoutManager(this@FunFragment.requireActivity(),5)
                            rv_fun.adapter = adapter
                        }
                    }

                })

               return false
            }

        })

    }

}
class GG(view: View): ItemAdapter.MyViewHolder(view){
    val image: ImageView = view.findViewById(R.id.service_image)
    val name: TextView = view.findViewById(R.id.service_text)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        Glide.with(image).load(ServiceCreate.url + (data[position] as HomeSerive.Row).imgUrl).into(image)
        name.text =  (data[position] as HomeSerive.Row).serviceName

        when((data[position] as HomeSerive.Row).serviceName){
            "宠物医院"->{
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,PetActivity::class.java))
                }
            }
            "城市地铁"-> {
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,PartActivity::class.java))
                }
            }
            "物流查询"->{
                itemView.setOnClickListener {
                    name.context.startActivity(Intent(name.context,LogActivity::class.java))
                }
            }
        }

    }
}