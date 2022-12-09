package com.example.smartcity7.ui.activity

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import com.example.smartcity7.R
import com.example.smartcity7.extesions.edit
import com.example.smartcity7.extesions.sharedPreferences

class IpActivity(context: Context) :PopupWindow(context) {

    init {
        val views =  LayoutInflater.from(context).inflate(R.layout.activity_ip,null,false)
        val ip = views.findViewById<EditText>(R.id.et_ip)
        val dk = views.findViewById<EditText>(R.id.et_dk)
        val Save = views.findViewById<Button>(R.id.save)

        this?.apply {
            isFocusable = true
            isClippingEnabled = true
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height=700
            contentView = views
            ColorDrawable(Color.WHITE)
        }

        Save.setOnClickListener {
            val ip = ip.text.toString()
            val dk = dk.text.toString()
            sharedPreferences.edit {
                putString("ip",ip)
                putString("dk",dk)
            }


        }
    }


}