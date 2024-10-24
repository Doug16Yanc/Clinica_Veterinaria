package com.example.veterinaria16.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import clinica.veterinaria16.databinding.ViewholderTopVeterinariesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.veterinaria16.Activity.DetailActivity
import com.example.veterinaria16.Domain.Veterinary

class TopVeterinariesAdapter(val items : MutableList<Veterinary>) : RecyclerView.Adapter<TopVeterinariesAdapter.Viewholder>() {
    private var context : Context?=null

    class Viewholder(val binding : ViewholderTopVeterinariesBinding):
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context=parent.context
        val binding = ViewholderTopVeterinariesBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.name.text = items[position].Name
        holder.binding.rating.text = items[position].Rating.toString()
        holder.binding.experience.text = this.items[position].Experience.toString() + " anos"

        Glide.with(holder.itemView.context)
            .load(items[position].Image)
            .apply { RequestOptions().transform(CenterCrop()) }
            .into(holder.binding.imageView)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("Object", items[position])
            context?.startActivity(intent)
        }
    }
}