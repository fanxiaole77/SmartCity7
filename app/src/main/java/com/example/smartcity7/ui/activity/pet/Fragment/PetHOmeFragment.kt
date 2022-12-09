package com.example.smartcity7.ui.activity.pet.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import kotlinx.android.synthetic.main.fragment_pet_h_ome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PetHOmeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pet_h_ome, container, false)
    }



}
