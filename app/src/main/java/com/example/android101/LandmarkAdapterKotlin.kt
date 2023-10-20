package com.example.android101

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android101.databinding.RecyclerRowLandmarkKotlinBinding

class LandmarkAdapterKotlin(val landmarkList : ArrayList<LandMarkFirstKotlin>) : RecyclerView.Adapter<LandmarkAdapterKotlin.LandmarkHolder>() {
    class LandmarkHolder(val binding :RecyclerRowLandmarkKotlinBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        // layout ile baglama işlemi
        val binding = RecyclerRowLandmarkKotlinBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LandmarkHolder(binding)
    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        // layout baglandıktan sonra ne olacak
        holder.binding.recyclerViewLandmarkRowTextView.text = landmarkList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,LandMarkDetailsActivityFirstKotlin::class.java)
            intent.putExtra("landmark",landmarkList.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        // kac tane olusturulacak
        return landmarkList.size
    }
}