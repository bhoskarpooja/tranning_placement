package com.example.tranning_placement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DriveAdapter extends RecyclerView.Adapter<DriveAdapter.ViewHolder> {

    ArrayList<My> mList;
    private static DriveAdapter.RecyclerViewClickListener listener = null;

    public DriveAdapter(ArrayList<My> mList, DriveAdapter.RecyclerViewClickListener listener) {
        this.mList = mList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public DriveAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ouritem, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DriveAdapter.ViewHolder holder, int position) {

        My vacancy1 = mList.get(position);

        holder.txtbname.setText("Company Name " + vacancy1.getCompanyname());
        holder.txtaddress.setText("Address " + vacancy1.getLocation());
        holder.txttype.setText("Description " + vacancy1.getDescription());
        holder.txtitem4.setText("Role " + vacancy1.getRole());

        Glide.with(holder.img1.getContext()).load(vacancy1.getImageurl()).into(holder.img1);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtbname, txtitem4, txtitem5,txtaddress, txttype;
        ImageView img1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtbname = itemView.findViewById(R.id.nametext);
            txtaddress = itemView.findViewById(R.id.coursetext);
            txttype = itemView.findViewById(R.id.emailtext);
            txtitem4 = itemView.findViewById(R.id.text);
            txtitem5 = itemView.findViewById(R.id.text1);

            img1 = itemView.findViewById(R.id.img1);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            listener.onClick(v,getAdapterPosition());
        }
    }
}