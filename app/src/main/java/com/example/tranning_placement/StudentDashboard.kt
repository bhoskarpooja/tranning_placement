package com.example.tranning_placement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class StudentDashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.student, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.showdrives-> {
                val intent = Intent(applicationContext,ShowPlacementDrives::class.java)
                startActivity(intent)

            }
            R.id.addskill-> {
                val intent = Intent(applicationContext,AddSkills::class.java)
                startActivity(intent)

            }
            R.id.showskills->{
                val intent = Intent(applicationContext,Showskills::class.java)
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