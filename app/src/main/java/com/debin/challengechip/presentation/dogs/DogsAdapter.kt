package com.debin.challengechip.presentation.dogs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.debin.challengechip.R
import com.debin.challengechip.framework.utils.getProgressDrawable
import com.debin.challengechip.framework.utils.loadImage
import kotlinx.android.synthetic.main.item_layout_dog.view.*

class DogsAdapter(private val dogs : ArrayList<String>) : RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    fun updateDogs(newDogs : List<String>) {
        dogs.clear()
        dogs.addAll(newDogs)
        notifyDataSetChanged()
    }

    inner class DogsViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val progressDrawable = getProgressDrawable(view.context)
        fun bindDog(dog : String) = with(itemView) {
            img_dog.loadImage(dog, progressDrawable)
            //Glide.with(context).load(dog).into(img_dog)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
       return DogsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_dog, parent, false))
    }

    override fun getItemCount(): Int = dogs.size

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
       val dog = dogs[position]
        holder.bindDog(dog)
    }
}