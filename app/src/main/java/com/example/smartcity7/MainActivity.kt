package com.example.smartcity7

import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.smartcity7.extesions.sharedPreferences
import com.example.smartcity7.ui.Dj.DjFragment
import com.example.smartcity7.ui.`fun`.FunFragment
import com.example.smartcity7.ui.home.HOmeFragment
import com.example.smartcity7.ui.me.MeFragment
import com.example.smartcity7.ui.news.NewsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var bottom1:BottomNavigationView

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor = Color.TRANSPARENT

        setEvent()
        SmartCityApplication.TOKEN = sharedPreferences.getString("token","").toString()

        supportActionBar?.hide()

        bottom1 = findViewById(R.id.bottom)

        loadFragment(HOmeFragment())

        bottom.setOnNavigationItemSelectedListener {
            when(it?.itemId){
                R.id.nav_home -> {
                    loadFragment(HOmeFragment())
                }
                R.id.nav_fun -> {
                    loadFragment(FunFragment())
                }

                R.id.nav_dj -> {
                    loadFragment(DjFragment())
                }
                R.id.nav_news -> {
                    loadFragment(NewsFragment())
                }
                R.id.nav_me -> {
                    loadFragment(MeFragment())
                }

            }
            true
        }

    }

    private fun loadFragment(fm:Fragment){
        val aa = supportFragmentManager.beginTransaction()
        aa.replace(R.id.fm,fm)
        aa.commit()
    }
    private fun setEvent(){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
    }
}