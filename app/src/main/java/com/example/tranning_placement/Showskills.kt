package com.example.tranning_placement

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Showskills : AppCompatActivity() {

    var ref: DatabaseReference? = null
    var list: ArrayList<Skills>? = null

    private var listener: SkillsAdapter.RecyclerViewClickListener? = null

    var recyclerView: RecyclerView? = null
    var searchView: SearchView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showskills)

        ref = FirebaseDatabase.getInstance().reference.child("skills")
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
                            list!!.add(ds.getValue(Skills::class.java)!!)
                        }

//                        setOnClickListner()
                        val adapter = SkillsAdapter(list, listener)
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

    

//    private fun setOnClickListner() {
//
//        listener = DriveAdapter.RecyclerViewClickListener { v, position ->
//            val intent = Intent(applicationContext, DriveDetails::class.java)
//
//            intent.putExtra("companyname", list!![position].companyname)
//            intent.putExtra("description", list!![position].description)
//            intent.putExtra("role", list!![position].role)
//            intent.putExtra("location", list!![position].location)
//
//            intent.putExtra("url", list!![position].imageurl)
//
//            startActivity(intent)
//        }
//    }


    private fun search(s: String) {

        try {
            val mylist = ArrayList<Skills?>()
            for (`object` in list!!) {
                if (`object`!!.getDepartment().toLowerCase().contains(s.toLowerCase())) {
                    mylist.add(`object`)
                }
            }
            val adapter = SkillsAdapter(list, listener)
            recyclerView!!.adapter = adapter
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}