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
import com.creativehazio.kraitor.data.model.TopArtist
import com.google.android.material.imageview.ShapeableImageView

class TopArtistListAdapter (
    private val context: Context
) : RecyclerView.Adapter<TopArtistViewHolder>() {

    private val topArtistList = listOf(
        TopArtist(R.drawable.test_img, "CreativeHazio", "3000"),
        TopArtist(R.drawable.test_img, "LordShubs", "1200"),
        TopArtist(R.drawable.test_img, "NerdGod", "200"),
        TopArtist(R.drawable.test_img, "Encoded", "9000"),
        TopArtist(R.drawable.test_img, "Star Gold", "150"),
        TopArtist(R.drawable.test_img, "Sir K", "5"),
        TopArtist(R.drawable.test_img, "HotIce", "3000")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopArtistViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.top_artists_cardview, parent, false)
        return TopArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopArtistViewHolder, position: Int) {
        val cardView = holder.topArtistCardView
        cardView.startAnimation(AnimationUtils.loadAnimation(context, R.anim.cardview_slide_anim))

        val topArtistImage = cardView.findViewById<ShapeableImageView>(R.id.top_artist_profile_image)
        val topArtistName = cardView.findViewById<TextView>(R.id.top_artist_profile_name)
        val topArtistLikesCount = cardView.findViewById<TextView>(R.id.top_artist_profile_likes_count)

        Glide.with(context).load(topArtistList.get(position).resourceId).into(topArtistImage)
        topArtistName.text = topArtistList.get(position).name
        topArtistLikesCount.text = "\uD83D\uDC99 ${topArtistList.get(position).likesCount}"
    }

    override fun getItemCount(): Int {
        return topArtistList.size
    }
}

class TopArtistViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val topArtistCardView: CardView = itemView.findViewById(R.id.top_artists_cardview)
}