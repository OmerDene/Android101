package com.example.android101.view.KotlinLandMarkApp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android101.databinding.LandmarkBookActivityBinding
import com.example.android101.databinding.RecycleRowBinding

class KotlinLandmarkAdapter(private val landmarkList: ArrayList<Landmark>) : RecyclerView.Adapter<KotlinLandmarkAdapter.LandmarkHolder>() {

    class LandmarkHolder(val binding: RecycleRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        val binding = RecycleRowBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return LandmarkHolder(binding)
    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = landmarkList[position].name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, KotlinLandMarkDetailActiviy::class.java)
            intent.putExtra("landmark", landmarkList[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return landmarkList.size
    }


}