package com.example.smartcity7.ui.me

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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.fragment_me.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UserActivity : AppCompatActivity() {

    lateinit var Avatar: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        supportActionBar?.hide()

        q.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
        q.setOnClickListener { finish() }

        Avatar = findViewById(R.id.up_avatar)

        /**
         * 显示所有东西
         */
        ServiceCreate.smartcityService.getUserInfo().enqueue(object :Callback<UserInfo>{
            override fun onFailure(p0: Call<UserInfo>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<UserInfo>, p1: Response<UserInfo>) {
                val body = p1.body()
                val id = body!!.user.phonenumber
                if (body!!.code == 200){

                    Glide.with(this@UserActivity).load(ServiceCreate.url +"prod-api/"+ body.user.avatar).into(up_avatar)

                    et_nick.setText(body.user.nickName)
                    et_phone.setText(if (id.length > 7){
                        id.replaceRange(3,id.length -4,"****")
                    }else{
                        id
                    })
                    if (body.user.sex == "1"){
                        wu_man.isChecked = true
                    }else{
                        man.isChecked = true
                    }
                }
            }

        })

        /**
         * 修改
         */

        var sex = "0"

        rg_sex.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.man){
                sex ="0"
            }else{
                sex = "1"
            }
        }

        save1.setOnClickListener {
            val avart = sharedPreferences.getString("avatar","")
            val nickname = et_nick.text.toString()
            val phone = et_phone.text.toString()
            ServiceCreate.smartcityService.putUser(User(nickname,phone,sex,avart)).enqueue(object :Callback<Message>{
                override fun onFailure(p0: Call<Message>, p1: Throwable) {
                }

                override fun onResponse(p0: Call<Message>, p1: Response<Message>) {
                   val body = p1.body()
                    if (body != null){
                        if (body.code == 200){
                            body.msg.showToast()
                        }else{
                            body.msg.showToast()
                        }
                    }
                }

            })
        }

        /**
         * 上传图片
         */
        xiugai.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            startActivityForResult(intent,1)
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
        else super.onActivityResult(requestCode, resultCode, data)
    }
    private fun uploadFile(path:String){
        val file = File(path)
        Avatar.setImageURI(Uri.fromFile(file))
        val requestBody = RequestBody.create(MediaType.parse("image/*"),file)
        val filePair = MultipartBody.Part.createFormData(
            "file",
            "text.jpg",
            requestBody
        )
        ServiceCreate.smartcityService.unload(filePair).enqueue(object :Callback<ImageUpload>{
            override fun onFailure(p0: Call<ImageUpload>, p1: Throwable) {
            }

            override fun onResponse(p0: Call<ImageUpload>, p1: Response<ImageUpload>) {
                val body = p1.body()
                if (body != null){
                    if (body.code == 200){
                        sharedPreferences.edit {
                            putString("avatar",body.fileName)
                        }
                        body.msg.showToast()
                    }else{
                        body.msg.showToast()
                    }
                }
            }

        })
    }

}

