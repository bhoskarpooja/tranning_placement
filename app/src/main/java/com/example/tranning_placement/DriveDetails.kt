package com.example.tranning_placement

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.bumptech.glide.Glide

class DriveDetails : AppCompatActivity() {

    var cname: String? = null
    var description: String? = null
    var role: String? = null
    var location: String? = null

    var url: String? = null

    var sharedpreferences: SharedPreferences? = null

    var email:String?=null

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drive_details)

        sharedpreferences = getSharedPreferences("email", MODE_PRIVATE)

        email = sharedpreferences!!.getString("email","Default")

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

        Glide.with(this@DriveDetails).load(url).into(image)

        txtproname.setText("Company Name: " + cname)
        txtmaterial.setText("Description: " + description)
        txtaddress.setText("Department: " + role)
        txtarea.setText("Location: " + location)

        val btnnoti = findViewById<Button>(R.id.btnnoti)

        btnnoti.setOnClickListener()
        {
            val builder = NotificationCompat.Builder(this@DriveDetails, "My Notification")

            builder.setContentTitle("New Placement Drive")
            builder.setContentText("Company-"+cname + " "+"Department-"+role)
            builder.setSmallIcon(R.drawable.tplogo)
            builder.setAutoCancel(true)
            builder.setStyle(NotificationCompat.InboxStyle())

            val managerCompat = NotificationManagerCompat.from(this@DriveDetails)
            managerCompat.notify(1, builder.build())

            val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val r = RingtoneManager.getRingtone(applicationContext, notification)
            r.play()

            //mail

            val msg = "company Name-$cname\nDescriptin:$description\n Role-$role\nLocation-\n$location"
            val s = send(applicationContext, email, "email", msg)
            s.execute()
        }
    }
}
