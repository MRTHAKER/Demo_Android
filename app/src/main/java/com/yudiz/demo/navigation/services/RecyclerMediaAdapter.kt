package com.yudiz.demo.navigation.services

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yudiz.demo.databinding.RowMediaPlayerRvBinding
import com.yudiz.demo.navigation.services.models.SongModel

class RecyclerMediaAdapter(private val items: MutableList<SongModel>) :
    RecyclerView.Adapter<RecyclerMediaAdapter.RecyclerViewMediaHolder>() {
    lateinit var binding: RowMediaPlayerRvBinding
    lateinit var listener:ItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewMediaHolder {
        binding =
            RowMediaPlayerRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewMediaHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerViewMediaHolder, position: Int) {
        binding.tvMediaName.text = items[position].songName
        binding.ivMediaPlay.setImageDrawable(items[position].playIcon)
        binding.ivMediaPause.setImageDrawable(items[position].pauseIcon)
        binding.ivMediaStop.setImageDrawable(items[position].stopIcon)
        binding.ivMediaPlay.setOnClickListener{
            listener.onClick(it,position,items[position].SongUri)
        }
        binding.ivMediaPause.setOnClickListener { listener.onClick(it,position,items[position].SongUri) }
        binding.ivMediaStop.setOnClickListener { listener.onClick(it,position,items[position].SongUri) }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = items.size
    class RecyclerViewMediaHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
interface ItemClickListener {
    fun onClick(view: View, position: Int,song:Uri)
}