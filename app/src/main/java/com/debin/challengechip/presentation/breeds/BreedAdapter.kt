package com.debin.challengechip.presentation.breeds

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.debin.challengechip.R
import com.debin.challengechip.breeds.domain.DogBreed
import com.debin.challengechip.breeds.domain.Message
import com.debin.challengechip.breeds.domain.utils.OpenForTesting
import kotlinx.android.synthetic.main.item_layout_breed.view.*

class BreedAdapter(private val breeds : ArrayList<String>, private val clickListener: OnClickListener) : RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    fun updateBreed(newBreeds: List<String>) {
        breeds.clear()
        breeds.addAll(newBreeds)
        notifyDataSetChanged()
    }

    inner class BreedViewHolder(view : View) : RecyclerView.ViewHolder(view) {
      fun bindBreed(breed : String) = with(itemView) {
          breed_type.text = breed
      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        return BreedViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_breed, parent, false))
    }

    override fun getItemCount(): Int = breeds.size

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
       val breed = breeds[position]
        holder.bindBreed(breed)
        holder.itemView.setOnClickListener {
            clickListener.onClick(breed)
        }
    }

    class OnClickListener(val clickListener: (breed : String) -> Unit) {
        fun onClick(breed: String) = clickListener(breed)
    }
}