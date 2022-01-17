package com.kdw.newsapp.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kdw.newsapp.R
import com.kdw.newsapp.databinding.ActivityMainBinding
import com.kdw.newsapp.util.NetworkCheck
import com.kdw.newsapp.util.StatusTransport
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        StatusTransport.makeStatusTransport(this@MainActivity)

        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_menu)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        bottomMenu.setupWithNavController(navController)

        if(NetworkCheck.isNetworkConnected(this)) {
            Toast.makeText(this, "네트워크 연결을 확인하세요", Toast.LENGTH_SHORT).show()
        }
    }

}