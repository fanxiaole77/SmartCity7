package com.example.smartcity7.ui.activity.pet

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.smartcity7.R
import com.example.smartcity7.extesions.edit
import com.example.smartcity7.extesions.sharedPreferences
import com.example.smartcity7.extesions.showToast
import com.example.smartcity7.network.*
import kotlinx.android.synthetic.main.activity_wen.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class WenActivity : AppCompatActivity() {

    lateinit var tupian:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wen)


        tupian =  findViewById(R.id.pet_image)

        supportActionBar?.hide()

        shangchuan.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent,1)
        }


        val id = intent.getIntExtra("do_id",0)
        ServiceCreate.smartcityService.getDoctor1(id).enqueue(object :Callback<Doctor>{
            override fun onFailure(p0: Call<Doctor>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<Doctor>, p1: Response<Doctor>) {
                val body = p1.body()
                if (body != null){
                    for (a in body.rows){
                        if (a.id == id){
                            Glide.with(this@WenActivity).load(ServiceCreate.url + a.avatar).into(do_avatar)
                            do_name.text = a.name
                            do_bianhao.text = a.practiceNo
                            do_zhicheng.text = a.jobName
                            miaoshu1.text = a.goodAt
                        }
                    }
                }
            }
        })
        tijiao.setOnClickListener {
            val content = et_miaoshu.text.toString()
            val tupian = sharedPreferences.getString("tupian","")
            ServiceCreate.smartcityService.postIn(In(id,tupian,content)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                    val body = p1.body()
                    if (body!= null){
                        if (body.code ==200){
                            body.msg.showToast()
                        }else{
                            body.msg.showToast()
                        }
                    }
                }
            })
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1){
            data?.data?.let { uri ->
                contentResolver.query(uri,null,null,null,null)?.use {
                    if (it.moveToFirst()){
                        val picturePath = it.getString(it.getColumnIndex(MediaStore.MediaColumns.DATA))
                        uploadFile(picturePath)
                    }
                }
            }
        }
    }
    private fun uploadFile(path:String){
        val file = File(path)
        tupian.setImageURI(Uri.fromFile(file))
        val requestBody = RequestBody.create(MediaType.parse("image/*"),file)
        val filePart = MultipartBody.Part.createFormData(
            "file",
            "text.jpg",
            requestBody
        )

        ServiceCreate.smartcityService.unload(filePart).enqueue(object :Callback<ImageUpload>{
            override fun onFailure(p0: Call<ImageUpload>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<ImageUpload>, p1: Response<ImageUpload>) {
               val body = p1.body()
                if (body != null){
                    if (body.code ==200){
                        body.msg.showToast()
                        sharedPreferences.edit {
                            putString("tupian",body.fileName)
                        }
                    }else{
                        body.msg.showToast()
                    }
                }
            }

        })

    }

}