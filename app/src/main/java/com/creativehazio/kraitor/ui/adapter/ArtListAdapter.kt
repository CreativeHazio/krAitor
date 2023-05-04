package com.creativehazio.kraitor.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creativehazio.kraitor.R
import com.creativehazio.kraitor.data.model.ArtAndPrompt

class ArtListAdapter(
    private val context: Context
) : RecyclerView.Adapter<ArtViewHolder>() {

    private val artList = listOf(
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
        ArtAndPrompt(R.drawable.test_img, context.getString(R.string.test_prompt)),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.art_cardview, parent, false)
        return ArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {

        val cardView = holder.artCardView
        cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.cardview_rotate_anim))

        val artImage = cardView.findViewById<ImageView>(R.id.art_img)
        val artPrompt = cardView.findViewById<TextView>(R.id.art_prompt)

        Glide.with(context).load(artList.get(position).resourceId).into(artImage)
        artPrompt.text = artList.get(position).prompt
    }

    override fun getItemCount(): Int {
        return artList.size
    }
}

class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val artCardView = itemView.findViewById<CardView>(R.id.art_cardview)
}
