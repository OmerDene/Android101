package com.example.android101.KotlinArtBook

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android101.databinding.RecycleRowBinding

class Artadapter(val artList :ArrayList<Art>) : RecyclerView.Adapter<Artadapter.ArtHolder>() {
    class ArtHolder(val binding : RecycleRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {
        val binding = RecycleRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)

    }

    override fun onBindViewHolder(holder: ArtHolder, position: Int) {
        holder.binding.recyclerViewTextView.text =artList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,KotlinArtBookDetail::class.java)
            intent.putExtra("info","old")
            intent.putExtra("id", artList.get(position).id)
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return artList.size

    }
}