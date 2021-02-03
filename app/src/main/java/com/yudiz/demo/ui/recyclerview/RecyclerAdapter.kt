package com.yudiz.demo.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yudiz.demo.databinding.RowItemRvBinding
import com.yudiz.demo.ui.recyclerview.models.Animal

class RecyclerAdapter(val items: List<Animal>) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    lateinit var binding: RowItemRvBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        binding = RowItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        binding.rvTv.text = items[position].name
        binding.rvIv.setImageDrawable(items[position].image)
    }

    override fun getItemCount(): Int = items.size
    class RecyclerViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}