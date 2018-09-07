package com.example.dchikhaoui.monmap.adaptater

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dchikhaoui.monmap.Model.Bike
import com.example.dchikhaoui.monmap.databinding.ItemBikeListBinding

class BikeListAdaptater() : RecyclerView.Adapter<BikeListAdaptater.ViewHolder>() {
    val items: ArrayList<Bike> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemBikeListBinding.inflate(inflater)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    fun addData(bike: Bike) {
        items.add(bike)
    }

    inner class ViewHolder(val binding: ItemBikeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Bike) {
            binding.bike = item
            binding.executePendingBindings()
        }
    }

}




