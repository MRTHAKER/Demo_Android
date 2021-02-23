package com.yudiz.demo.services

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.yudiz.demo.R
import com.yudiz.demo.databinding.RowMediaPlayerRvBinding
import com.yudiz.demo.services.models.SongModel

class RecyclerMediaAdapter(private val items: MutableList<SongModel>) :
    RecyclerView.Adapter<RecyclerMediaAdapter.RecyclerViewMediaHolder>() {
    lateinit var binding: RowMediaPlayerRvBinding
    lateinit var listener: ItemClickListener
    lateinit var holder: RecyclerViewMediaHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewMediaHolder {
        binding =
            RowMediaPlayerRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewMediaHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerViewMediaHolder, position: Int) {
        this.holder = holder
        holder.name.text = items[position].songName
        holder.play.setOnClickListener {
            listener.onClick(it, holder, items[position].SongUri, items[position].songName)
            holder.play.visibility=View.GONE
            holder.pause.visibility=View.VISIBLE
        }
        holder.pause.setOnClickListener {
            listener.onClick(it, holder, items[position].SongUri, items[position].songName)
            holder.pause.visibility=View.GONE
            holder.play.visibility=View.VISIBLE
        }
        holder.stop.setOnClickListener {
            listener.onClick(it, holder, items[position].SongUri, items[position].songName)
            holder.play.visibility=View.VISIBLE
            holder.pause.visibility=View.VISIBLE
        }
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = items.size
    class RecyclerViewMediaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val play: ImageView = itemView.findViewById(R.id.iv_media_play)
        val pause: ImageView = itemView.findViewById(R.id.iv_media_pause)
        val stop: ImageView = itemView.findViewById(R.id.iv_media_stop)
        val name: MaterialTextView = itemView.findViewById(R.id.tv_media_name)
    }
}

interface ItemClickListener {
    fun onClick(view: View, holder: RecyclerMediaAdapter.RecyclerViewMediaHolder, song: Uri, songName: String)
}