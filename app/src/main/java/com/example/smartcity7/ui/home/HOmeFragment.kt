package com.example.smartcity7.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity7.MainActivity
import com.example.smartcity7.R
import com.example.smartcity7.extesions.ItemAdapter
import com.example.smartcity7.network.*
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import com.youth.banner.listener.OnBannerListener
import kotlinx.android.synthetic.main.fragment_h_ome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HOmeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_h_ome, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Sv.setOnClickListener {
            startActivity(Intent(activity,SvActivity::class.java))
        }


        ServiceCreate.smartcityService.getHomeBanner().enqueue(object :Callback<HomeBanner>{
            override fun onFailure(p0: Call<HomeBanner>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeBanner>, p1: Response<HomeBanner>) {
               val body = p1.body()
                if (body != null){
                    home_banner.adapter = object :BannerImageAdapter<HomeBanner.Row>(body.rows){
                        override fun onBindView(
                            p0: BannerImageHolder?,
                            p1: HomeBanner.Row?,
                            p2: Int,
                            p3: Int
                        ) {
                            Glide.with(this@HOmeFragment.requireActivity()).load(ServiceCreate.url + p1!!.advImg).into(p0!!.imageView)
                        }

                    }

                    home_banner.setOnBannerListener(object :OnBannerListener<HomeBanner.Row>{
                        override fun OnBannerClick(p0: HomeBanner.Row?, p1: Int) {
                            startActivity(Intent(activity,NewsContentActivity::class.java).apply {
                                putExtra("news_id",p0!!.targetId)
                            })
                        }
                    })
                }
            }

        })

        ServiceCreate.smartcityService.getHomeService(null).enqueue(object :Callback<HomeSerive>{
            override fun onFailure(p0: Call<HomeSerive>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<HomeSerive>, p1: Response<HomeSerive>) {
                val body = p1.body()
                if (body != null){
                    val list = body.rows.sortedWith(compareBy { it!!.sort }).reversed()
                    val adapter = ItemAdapter(R.layout.item_service,list,AA::class.java,10,
                        listOf(activity))
                    rv_service.layoutManager = GridLayoutManager(this@HOmeFragment.requireActivity(),5)
                    rv_service.adapter = adapter
                }
            }

        })

        ServiceCreate.smartcityService.getHot("Y").enqueue(object :Callback<NewsList>{
            override fun onFailure(p0: Call<NewsList>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsList>, p1: Response<NewsList>) {
                val body = p1.body()
                if (body != null){
                    val adapter = ItemAdapter(R.layout.item_hot,body.rows,BB::class.java,2)
                    rv_hot.layoutManager = GridLayoutManager(this@HOmeFragment.requireActivity(),2)
                    rv_hot.adapter = adapter
                }
            }

        })

        ServiceCreate.smartcityService.getNewsType().enqueue(object:Callback<NewsType>{
            override fun onFailure(p0: Call<NewsType>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<NewsType>, p1: Response<NewsType>) {
               val body = p1.body()
                if (body != null){
                    val adapter = PagerAdapter(this@HOmeFragment.requireFragmentManager(),body.data)
                    v1.adapter = adapter
                    t1.setupWithViewPager(v1)
                }
            }

        })

    }


}

class AA(view: View):ItemAdapter.MyViewHolder(view){
    val image:ImageView = view.findViewById(R.id.service_image)
    val name:TextView = view.findViewById(R.id.service_text)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        Glide.with(image).load(ServiceCreate.url + (data[position] as HomeSerive.Row).imgUrl).into(image)
        name.text =  (data[position] as HomeSerive.Row).serviceName
        if (position == 9){
            Glide.with(image).load(ServiceCreate.url + (data[position] as HomeSerive.Row).imgUrl).into(image)
            name.text =  "更多服务"

            val aa =list[0] as MainActivity
            itemView.setOnClickListener {
                aa.bottom1.selectedItemId = R.id.nav_fun
            }
        }

    }
}

class BB(view: View):ItemAdapter.MyViewHolder(view){
    val image:ImageView = view.findViewById(R.id.image_hot)
    val name:TextView = view.findViewById(R.id.text_hot)
    override fun binViewHolder(data: List<Any?>, position: Int, list: List<Any?>) {
        Glide.with(image).load(ServiceCreate.url + (data[position] as NewsList.Row).cover).into(image)
        name.text =  (data[position] as NewsList.Row).title
    }
}