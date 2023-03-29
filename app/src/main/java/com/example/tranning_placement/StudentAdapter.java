package com.example.tranning_placement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class StudentAdapter extends  RecyclerView.Adapter<StudentAdapter.ViewHolder>{

    ArrayList<My> mList;
    Context context;

    public StudentAdapter(ArrayList<My> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new StudentAdapter.ViewHolder(v);      }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {

        My vacancy1 = mList.get(position);
        holder.nametext.setText("Company Name- "+vacancy1.getCompanyname());
        holder.coursetxt.setText("Location- "+vacancy1.getLocation());
        holder.emailtext.setText("Description -"+vacancy1.getDescription());
        holder.edarea.setText("Department -"+vacancy1.getRole());

        Glide.with(holder.imageurl.getContext()).load(vacancy1.getImageurl()).into(holder.imageurl);


        holder.btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "Applied",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView nametext,coursetxt,emailtext,edarea;
         Button btnapply;
         ImageView imageurl;
         CardView layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nametext = itemView.findViewById(R.id.nametext);
            coursetxt = itemView.findViewById(R.id.coursetext);
            emailtext = itemView.findViewById(R.id.emailtext);
            edarea = itemView.findViewById(R.id.edarea);

            btnapply = itemView.findViewById(R.id.btnapply);

            imageurl=itemView.findViewById(R.id.imgbtn);


        }
    }

}
