package com.example.smartcity7.extesions

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import com.example.smartcity7.SmartCityApplication
import javax.xml.datatype.Duration

fun CharSequence.showToast(duration:Int = Toast.LENGTH_SHORT){
    Toast.makeText(SmartCityApplication.context,this,duration).show()
}

fun SharedPreferences.edit(action:SharedPreferences.Editor.() -> Unit){
    val editor = edit()
    action(editor)
    editor.apply()
}

@SuppressLint("WrongConstant")
val sharedPreferences:SharedPreferences = SmartCityApplication.context.getSharedPreferences(
    "sharedPreferences",
    Context.MODE_APPEND
)