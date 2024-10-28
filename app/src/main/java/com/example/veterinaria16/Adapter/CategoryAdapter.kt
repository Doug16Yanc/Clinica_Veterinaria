package com.example.veterinaria16.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import clinica.veterinaria16.databinding.ViewholderCategoryBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.veterinaria16.Activity.DetailAnimalActivity
import com.example.veterinaria16.Domain.AnimalCategory

class CategoryAdapter(val items: MutableList<AnimalCategory>) :
    RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private var context: Context?=null

    class Viewholder(val binding: ViewholderCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.binding.titleTxt.text = items[position].Name

        Glide.with(holder.itemView.context)
            .load(items[position].Image)
            .apply { RequestOptions().transform(CenterCrop()) }
            .into(holder.binding.img)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailAnimalActivity::class.java)
            intent.putExtra("Object", items[position])
            context?.startActivity(intent)
        }
    }
}