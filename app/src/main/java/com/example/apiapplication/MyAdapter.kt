package com.example.apiapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apiapplication.databinding.ItemPillBinding

class MyAdapter(val itemList:MutableList<PillInfo.Body.Item?>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val binding = ItemPillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.bind(itemList[position])

    }
    inner class MyViewHolder(val binding: ItemPillBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: PillInfo.Body.Item?){
            binding.pillName.text = "품명:${item?.iTEMNAME}"
        }
    }
}