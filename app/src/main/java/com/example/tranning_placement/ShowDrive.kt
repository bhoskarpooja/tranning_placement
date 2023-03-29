package com.example.tranning_placement

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ShowDrive : AppCompatActivity() {

    var ref: DatabaseReference? = null
    var list: ArrayList<My>? = null

    private var listener: DriveAdapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null
    var searchView: SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_drive)

        ref = FirebaseDatabase.getInstance().reference.child("placementdrive")
        recyclerView = findViewById(R.id.recyclerview)
        searchView = findViewById(R.id.searchview)

    }

    override fun onStart() {
        super.onStart()
        if (ref != null) {
            ref!!.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        list = ArrayList()
                        for (ds in snapshot.children) {
                            list!!.add(ds.getValue(My::class.java)!!)
                        }

                        setOnClickListner()
                        val adapter = DriveAdapter(list, listener)
                        recyclerView!!.adapter = adapter
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
            })
        }

        if (searchView != null) {
            searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(s: String): Boolean {
                    search(s)
                    return true
                }
            })
        }
    }

    private fun setOnClickListner() {

        listener = DriveAdapter.RecyclerViewClickListener { v, position ->
            val intent = Intent(applicationContext, DriveDetails::class.java)

            intent.putExtra("companyname", list!![position].companyname)
            intent.putExtra("description", list!![position].description)
            intent.putExtra("role", list!![position].role)
            intent.putExtra("location", list!![position].location)

            intent.putExtra("url", list!![position].imageurl)

            startActivity(intent)
        }
    }


    private fun search(s: String) {

        try {
            val mylist = ArrayList<My?>()
            for (`object` in list!!) {
                if (`object`!!.getCompanyname().toLowerCase().contains(s.toLowerCase())) {
                    mylist.add(`object`)
                }
            }
            val adapter = DriveAdapter(list, listener)
            recyclerView!!.adapter = adapter
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}