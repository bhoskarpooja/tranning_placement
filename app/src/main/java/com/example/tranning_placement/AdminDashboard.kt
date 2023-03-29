package com.example.tranning_placement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem

class AdminDashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.admin, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.checkdrives-> {
                val intent = Intent(applicationContext,ShowDrive::class.java)
                startActivity(intent)

            }

//            R.id.sendnoti -> {
//                val intent = Intent(applicationContext,SendNotification::class.java)
//                startActivity(intent)
//
//            }

            R.id.logout -> {
                val intent = Intent(applicationContext,AdminLogin::class.java)
                startActivity(intent)

            }

        }
        return true
    }
}