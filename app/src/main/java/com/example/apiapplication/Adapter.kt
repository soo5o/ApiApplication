package com.example.apiapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.example.apiapplication.databinding.ItemPillBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class Adapter(val items:MutableList<Item?>):RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val binding = ItemPillBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bind(items[position])

    }
    override fun getItemCount(): Int {
        return items.size
    }
    inner class ViewHolder(val binding: ItemPillBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Item?){
            GlobalScope.launch(Dispatchers.Main) {
                try {
                    Log.d("Allyak", "url: ${item?.iTEMIMAGE}")
                    Glide.with(binding.root.context)
                        .load("http://fresh.haccp.or.kr/prdimg/2013/201305230193/201305230193-1.jpg")
                        .into(binding.pillImg)
                    Log.d("Allyak", "bitmap")
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.d("Allyak", "error1: $e")

                    if (e is GlideException) {
                        e.logRootCauses("Glide Load Failed")
                        Log.d("Allyak", "error2: $e")
                    }
                }
            }
            binding.pillName.text = item?.iTEMNAME
            binding.entpName.text = item?.eNTPNAME
        }
    }
}