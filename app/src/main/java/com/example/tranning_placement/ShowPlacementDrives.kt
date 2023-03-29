package com.example.tranning_placement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ShowPlacementDrives : AppCompatActivity() {

     var database: FirebaseDatabase? = null
     var mDatabaseRef: DatabaseReference? = null

    var recyclerView: RecyclerView? = null
    var searchView: SearchView? = null

    var adapter:StudentAdapter?=null
     var list: ArrayList<My>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_placement_drives)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

        list = ArrayList()

        val pref = getSharedPreferences("My", MODE_PRIVATE)

        val name = pref.getString("role", "No name defined")

        Toast.makeText(applicationContext,name.toString(), Toast.LENGTH_LONG).show()
        database = FirebaseDatabase.getInstance()

       mDatabaseRef = FirebaseDatabase.getInstance().getReference("placementdrive")

        val query: Query = mDatabaseRef!!.orderByChild("role").equalTo(name)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                print(dataSnapshot)

                for (data in dataSnapshot.children) {
                    println(data)

                    val models: My? = data.getValue(My::class.java)
                    println(models)
                    if (models != null) {
                        list!!.add(models)
                    }

                }

                adapter = StudentAdapter(list,applicationContext)
                recyclerView.adapter = adapter

            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }

}