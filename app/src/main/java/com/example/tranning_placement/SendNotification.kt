package com.example.tranning_placement

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SendNotification : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_notification)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "My Notification",
                "My Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)

        }

        val edcompanyname = findViewById<EditText>(R.id.edcomapanyname)
        val eddescription = findViewById<EditText>(R.id.eddescription)
        val edrole = findViewById<EditText>(R.id.edrole)
        val edlocation = findViewById<EditText>(R.id.edlocation)

    }
}