package com.example.contactlist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*
private const val TAG2 ="ContactAdapter"
class ContactAdapter(val context: Context, val contact: List<ContactResult>, val onClickListener: OnClickListener): RecyclerView.Adapter<ContactAdapter.ViewHolder>(){


    interface OnClickListener{
        fun onClick(Position : Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent ,false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contact[position];
       holder.itemView.setOnClickListener{
           Log.i(TAG2,"clicked on position ${position+1}")
           onClickListener.onClick(position)
       }
        holder.bind(contact)
    }

    override fun getItemCount() = contact.size
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(contact: ContactResult) {
            itemView.tvName.text = contact.name

        }

    }
}
