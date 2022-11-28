package com.example.android101;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android101.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class LandmarkAdapter extends RecyclerView.Adapter<LandmarkAdapter.LanmarkHolder>{
    ArrayList<Landmark> landmarkArrayList;

    public LandmarkAdapter(ArrayList<Landmark> landmarkArrayList) {
        this.landmarkArrayList = landmarkArrayList;
    }

    @NonNull
    @Override
    public LanmarkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new LanmarkHolder(recyclerRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull LanmarkHolder holder, int position) {
        holder.binding.recyclerViewTextview.setText(landmarkArrayList.get(position).name);
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("landmark" ,landmarkArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return landmarkArrayList.size();
    }

    public class LanmarkHolder extends RecyclerView.ViewHolder {
       private RecyclerRowBinding binding;

        public LanmarkHolder(RecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
