package com.example.tranning_placement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class CompanyDashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_dashboard)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.company, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.adddrive-> {
                val intent = Intent(applicationContext,AddPlacementDrive::class.java)
                startActivity(intent)

            }
            R.id.logout -> {
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)

            }
        }
        return true
    }


}