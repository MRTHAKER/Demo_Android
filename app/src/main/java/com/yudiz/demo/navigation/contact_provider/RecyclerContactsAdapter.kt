package com.yudiz.demo.navigation.contact_provider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yudiz.demo.databinding.RowContactRvBinding
import com.yudiz.demo.navigation.contact_provider.models.ContactModel

class RecyclerContactsAdapter(val items: ArrayList<ContactModel>) :
    RecyclerView.Adapter<RecyclerContactsAdapter.RecyclerViewContactsHolder>() {

    lateinit var binding: RowContactRvBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewContactsHolder {
        binding = RowContactRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewContactsHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerViewContactsHolder, position: Int) {
        binding.tvContactName.text = items[position].name
        binding.tvContactEmail.text = items[position].email
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = items.size

    class RecyclerViewContactsHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}