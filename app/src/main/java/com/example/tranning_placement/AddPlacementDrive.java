package com.example.tranning_placement;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
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

import java.io.IOException;
import java.util.ArrayList;

public class AddPlacementDrive extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference mref;
    FirebaseStorage firebaseStorage;
    ImageButton imageButton;

    EditText edcompanyname, edrole, eddecription, edlocation;
    Button uploaddrive;
    private static final int code = 1;
    Uri imageurl = null;

    String email;

    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_placement_drive);

        sharedpreferences = getSharedPreferences("My", Context.MODE_PRIVATE);
        email = sharedpreferences.getString("email","Default");


        imageButton = findViewById(R.id.imagebutton);

        edcompanyname = findViewById(R.id.edcomapanyname);
        eddecription = findViewById(R.id.eddescription);
        edrole = findViewById(R.id.edrole);
        edlocation = findViewById(R.id.edlocation);

        uploaddrive = findViewById(R.id.uploaddrive);


        firebaseDatabase = FirebaseDatabase.getInstance();
        mref = firebaseDatabase.getReference().child("placementdrive");
        firebaseStorage = FirebaseStorage.getInstance();

        imageButton.setOnClickListener(new View.OnClickListener() {
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

            imageurl = data.getData();
            imageButton.setImageURI(imageurl);
        }

        uploaddrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cname = edcompanyname.getText().toString().trim();
                String location = edlocation.getText().toString().trim();
                String description = eddecription.getText().toString().trim();
                String role = edrole.getText().toString().trim();


                StorageReference filepath = firebaseStorage.getReference().child("imagepost").child(imageurl.getLastPathSegment());
                filepath.putFile(imageurl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> downloadurl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {

                                String t = task.getResult().toString();
                                DatabaseReference newpost = mref.push();

                                newpost.child("companyname").setValue(cname);
                                newpost.child("description").setValue(description);
                                newpost.child("role").setValue(role);
                                newpost.child("location").setValue(location);


                                newpost.child("imageurl").setValue(task.getResult().toString());
                                Toast.makeText(getApplicationContext(), "Drive Uploaded", Toast.LENGTH_SHORT).show();

                                sendnotify();
                            }

                            private void sendnotify() {

                                String msg = "company Name-" + cname + "\nDescriptin:" + description + "\n Role-" + role + "\nLocation-\n" + location;
                                send s = new send(getApplicationContext(), email, "mail", msg);
                                s.execute();

                            }
                        });
                    }
                });
            }

        });

    }
}
