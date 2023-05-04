package com.creativehazio.kraitor.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.creativehazio.kraitor.R
import com.creativehazio.kraitor.data.model.Data

class CreatedArtListAdapter(
    private val context: Context
) : RecyclerView.Adapter<CreatedArtViewHolder>() {

    private var createdArtList = ArrayList<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreatedArtViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.created_art_cardview, parent, false)
        return CreatedArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: CreatedArtViewHolder, position: Int) {

        val cardView = holder.createdArtCardView

        val createdArtImage = cardView.findViewById<ImageView>(R.id.created_art_img)

        val options = RequestOptions().timeout(30000)
        //TODO Add placeholder and error placeholder
        Glide.with(context).load(createdArtList.get(position).url).apply(options).into(createdArtImage)
    }

    override fun getItemCount(): Int {
        return createdArtList.size
    }

    fun setCreatedArtList(newlyCreatedArtList : List<Data>) {
        createdArtList.clear()
        createdArtList.addAll(newlyCreatedArtList)
        notifyDataSetChanged()
    }
}

class CreatedArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val createdArtCardView: CardView = itemView.findViewById(R.id.created_art_cardview)
}