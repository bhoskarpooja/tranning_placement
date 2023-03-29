package com.example.tranning_placement

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide

class PlacementDriveDetails : AppCompatActivity() {


    var cname: String? = null
    var description: String? = null
    var role: String? = null
    var location: String? = null

    var url: String? = null

    var sharedpreferences: SharedPreferences? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placement_drive_details)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "My Notification",
                "My Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)

        }

        val sharedpreferences = getSharedPreferences("My", MODE_PRIVATE)
        val email = sharedpreferences.getString("email", "Default")

        val txtproname = findViewById<TextView>(R.id.txtname)
        val txtmaterial = findViewById<TextView>(R.id.txtmaterial)
        val txtaddress = findViewById<TextView>(R.id.txtmanifacture)

        val txtarea = findViewById<TextView>(R.id.txtorigin)

        val image = findViewById<ImageView>(R.id.image1)

        val mSharedPreference = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val value = mSharedPreference.getString("user-email", "DEFAULT")

        val bundle = intent.extras

        cname = bundle?.getString("companyname")
        description = bundle?.getString("description")
        role = bundle?.getString("role")
        location = bundle?.getString("location")

        url = bundle?.getString("url")

        Glide.with(this@PlacementDriveDetails).load(url).into(image)

        txtproname.setText("Company Name: " + cname)
        txtmaterial.setText("Description: " + description)
        txtaddress.setText("Department: " + role)
        txtarea.setText("Location: " + location)

        val btnapply = findViewById<Button>(R.id.btnapply)

        btnapply.setOnClickListener()
        {
            val builder = NotificationCompat.Builder(this@PlacementDriveDetails, "My Notification")

            builder.setContentTitle("Application Submitted!")
            builder.setContentText("Company-"+cname)
            builder.setSmallIcon(R.drawable.tplogo)
            builder.setAutoCancel(true)
            builder.setStyle(NotificationCompat.InboxStyle())

            val managerCompat = NotificationManagerCompat.from(this@PlacementDriveDetails)
            managerCompat.notify(1, builder.build())

            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()

        }

    }
}