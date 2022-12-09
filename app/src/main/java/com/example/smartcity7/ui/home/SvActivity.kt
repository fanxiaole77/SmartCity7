package com.example.smartcity7.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.network.NewsList
import com.example.smartcity7.network.ServiceCreate
import kotlinx.android.synthetic.main.activity_sv.*
import kotlinx.android.synthetic.main.fragment_new_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SvActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sv)

        supportActionBar?.hide()

        sear.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                ServiceCreate.smartcityService.getNewsList(newText,null).enqueue(object :Callback<NewsList>{
                    override fun onFailure(p0: Call<NewsList>, p1: Throwable) {
                    }

                    override fun onResponse(p0: Call<NewsList>, p1: Response<NewsList>) {
                       val body = p1.body()
                        if (body != null){
                            val adapter = ItemAdapter(R.layout.item_new_list,body.rows,CC::class.java)
                            rv_sv.layoutManager = LinearLayoutManager(this@SvActivity)
                            rv_sv.adapter =adapter
                        }
                    }

                })

               return false
            }

        })

    }
}