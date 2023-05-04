package com.creativehazio.kraitor.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.creativehazio.kraitor.R
import com.creativehazio.kraitor.data.model.ArtStyle
import com.google.android.material.card.MaterialCardView

class ArtStyleListAdapter(
    private val context: Context
    ) : RecyclerView.Adapter<ArtStyleViewHolder>() {


    private val artStyleList = listOf(
        ArtStyle(R.drawable.test_img, "Realistic", context.getString(R.string.realistic_prompt)),
        ArtStyle(R.drawable.test_img, "Photography", context.getString(R.string.photography_prompt)),
        ArtStyle(R.drawable.test_img, "Abstract", context.getString(R.string.abstract_prompt)),
        ArtStyle(R.drawable.test_img, "Architecture", context.getString(R.string.architecture_prompt)),
        ArtStyle(R.drawable.test_img, "Midjourney", context.getString(R.string.midjourney_prompt)),
        ArtStyle(R.drawable.test_img, "3D Illustrations", context.getString(R.string.three_dimension_prompt)),
        ArtStyle(R.drawable.test_img, "CyberPunk", context.getString(R.string.cyberpunk_prompt)),
        ArtStyle(R.drawable.test_img, "Digital Art", context.getString(R.string.digital_prompt)),
        ArtStyle(R.drawable.test_img, "Cartoony", context.getString(R.string.cartoon_prompt)),
        ArtStyle(R.drawable.test_img, "Anime", context.getString(R.string.anime_prompt))
    )


    private var selectedCardView: MaterialCardView? = null
    private lateinit var _listener : Listener

    interface Listener {
        fun onClick(artStyleList: List<ArtStyle>, position : Int)
    }

    fun setListener(listener: Listener) {
        _listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtStyleViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.art_style_cardview, parent, false)
        return ArtStyleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtStyleViewHolder, position: Int) {
        val cardView = holder.artStyleCardView

        cardView.setBackgroundResource(R.drawable.art_style_cardview_state)

        val artStyleImage = cardView.findViewById<ImageView>(R.id.art_style_img)
        val artStyleText = cardView.findViewById<TextView>(R.id.art_style_name)

        Glide.with(context).load(artStyleList.get(position).resourceId).into(artStyleImage)
        artStyleText.text = artStyleList.get(position).name

        cardView.setOnClickListener {
            _listener.onClick(artStyleList, position)
            selectedCardView?.strokeWidth = 0
            cardView.strokeWidth = 2;
            cardView.strokeColor = context.resources.getColor(R.color.light_yellow);
            selectedCardView = cardView;
        }
    }

    override fun getItemCount(): Int {
        return artStyleList.size
    }
}

class ArtStyleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val artStyleCardView: MaterialCardView = itemView.findViewById(R.id.art_style_cardview)
}