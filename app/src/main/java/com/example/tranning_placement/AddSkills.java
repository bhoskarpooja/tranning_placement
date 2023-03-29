package com.example.tranning_placement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddSkills extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mref;
    FirebaseStorage firebaseStorage;

    EditText stdname, stdemail, exp,degree,dept,lang;
    Button uploaddrive;
    private static final int code = 1;
    Uri imageurl = null;
    ImageButton imagebtn;
    String email;

    SharedPreferences sharedpreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skills);

        sharedpreferences = getSharedPreferences("My", Context.MODE_PRIVATE);
        email = sharedpreferences.getString("email","Default");

        uploaddrive = findViewById(R.id.uploaddrive);

        imagebtn = findViewById(R.id.imgbtn);

        stdname= findViewById(R.id.edstdname);
        stdemail = findViewById(R.id.edstdemail);
        exp = findViewById(R.id.edexp);

        degree = findViewById(R.id.eddegree);
        dept = findViewById(R.id.eddept);
        lang = findViewById(R.id.edlang);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mref = firebaseDatabase.getReference().child("skills");
        firebaseStorage = FirebaseStorage.getInstance();

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, code);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == code && resultCode == RESULT_OK) {

            imageurl= data.getData();
            imagebtn.setImageURI(imageurl);
        }

        uploaddrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String studentname = stdname.getText().toString().trim();
                String studentexp = exp.getText().toString().trim();
                String studentdegree = degree.getText().toString().trim();
                String studentdept = dept.getText().toString().trim();
                String studentlang = lang.getText().toString().trim();

                StorageReference filepath = firebaseStorage.getReference().child("imagepost").child(imageurl.getLastPathSegment());
                filepath.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> downloadurl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {


                                String t = task.getResult().toString();
                                DatabaseReference newpost = mref.push();

                                newpost.child("studentname").setValue(studentname);
                                newpost.child("experiance").setValue(studentexp);
                                newpost.child("degree").setValue(studentdegree);
                                newpost.child("department").setValue(studentdept);
                                newpost.child("languages").setValue(studentlang );


                                newpost.child("imageurl").setValue(task.getResult().toString());
                                Toast.makeText(getApplicationContext(), "Skills Added", Toast.LENGTH_SHORT).show();

//                                sendnotify();
                                sharedata();
                            }

                            private void sharedata() {
                                sharedpreferences = getSharedPreferences("My",getApplicationContext().MODE_PRIVATE);

                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("role", studentdept);
                                editor.commit();
                            }

//                            private void sendnotify() {
//
//                                String msg = "company Name-" + cname + "\nDescriptin:" + description + "\n Role-" + role + "\nLocation-\n" + location;
//                                send s = new send(getApplicationContext(), email, "mail", msg);
//                                s.execute();
//
//                            }
                        });
                    }
                });

            }

        });

    }
}