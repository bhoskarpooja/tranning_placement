package com.example.tranning_placement

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    lateinit var auth: FirebaseAuth

    val dropDownList = arrayOf("Select User Type", "Company", "Student", "Admin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edemail = findViewById<EditText>(R.id.edemail)
        val edpassword = findViewById<EditText>(R.id.edpassword)
        val btnlogin = findViewById<Button>(R.id.btnlogin)
        val txtforgot = findViewById<TextView>(R.id.txtforgot)
        val txtregister = findViewById<TextView>(R.id.txtregister)


        val statusFillter = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dropDownList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)

        statusFillter.adapter = adapter

        statusFillter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                if (statusFillter.selectedItemPosition == 1) {
                    Toast.makeText(
                        applicationContext,
                        statusFillter.selectedItem.toString(),
                        Toast.LENGTH_LONG
                    ).show()

                } else if (statusFillter.selectedItemPosition == 2) {
                    Toast.makeText(
                        applicationContext,
                        statusFillter.selectedItem.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(applicationContext, StudentLogin::class.java)

                    startActivity(intent)

                } else if (statusFillter.selectedItemPosition == 3) {
                    Toast.makeText(
                        applicationContext,
                        statusFillter.selectedItem.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(applicationContext, AdminLogin::class.java)

                    startActivity(intent)

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        auth = FirebaseAuth.getInstance()


        btnlogin.setOnClickListener {
            if (edemail.text.isEmpty()) {
                edemail.setError("Enter Email Id")
                return@setOnClickListener
            } else if (edpassword.text.isEmpty()) {
                edpassword.setError("Enter Password")
                return@setOnClickListener
            }


            auth.signInWithEmailAndPassword(edemail.text.toString(),edpassword.text.toString())
                .addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(applicationContext,"Login Succesfull", Toast.LENGTH_LONG).show()
                        val intent = Intent(applicationContext, CompanyDashboard::class.java)
                        startActivity(intent)
                    }
                    else
                    {
                        Toast.makeText(applicationContext,"Failed to login", Toast.LENGTH_LONG).show()
                    }
                }
        }


        txtforgot.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Forgot Password")
            val view = layoutInflater.inflate(R.layout.dailog_forgot, null)
            val username = view.findViewById<EditText>(R.id.ed_forgot)
            builder.setView(view)
            builder.setPositiveButton("Reset", DialogInterface.OnClickListener { _, _ ->
                forgotpassword(username)
            })
            builder.setNegativeButton("Close", DialogInterface.OnClickListener { _, _ -> })
            builder.show()
        }
        txtregister.setOnClickListener {
            val intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)
        }

    }


    private fun forgotpassword(username: EditText) {

        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener {
                if(it.isSuccessful)
                {
                    Toast.makeText(applicationContext,"Email Sent", Toast.LENGTH_LONG).show()
                }
            }

    }
}