package com.example.tranning_placement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.ViewHolder> {

    ArrayList<Skills> mList;
    private static SkillsAdapter.RecyclerViewClickListener listener = null;

    public SkillsAdapter(ArrayList<Skills> mList, SkillsAdapter.RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SkillsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.skills,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsAdapter.ViewHolder holder, int position) {

        Skills vacancy1 = mList.get(position);

        holder.studname.setText("Name- "+vacancy1.getStudentname());
        holder.studdegree.setText("Degree- "+vacancy1.getDegree());
        holder.studpro.setText("Languages - "+vacancy1.getLanguages());
        holder.studyear.setText("Experiance- "+vacancy1.getExperiance());
        holder.studdept.setText("Department- "+vacancy1.getDepartment());

        Glide.with(holder.imageurl.getContext()).load(vacancy1.getImageurl()).into(holder.imageurl);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView studname,studdegree,studpro,studyear,studdept;

        ImageView imageurl;
        CardView layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            studname = itemView.findViewById(R.id.nametext);
            studdegree = itemView.findViewById(R.id.coursetext);
            studpro = itemView.findViewById(R.id.emailtext);
            studyear = itemView.findViewById(R.id.edarea);

            studdept = itemView.findViewById(R.id.btnapply);

            imageurl=itemView.findViewById(R.id.img1);


        }
    }
}
