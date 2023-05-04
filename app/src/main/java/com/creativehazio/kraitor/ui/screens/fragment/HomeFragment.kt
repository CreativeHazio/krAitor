package com.creativehazio.kraitor.ui.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creativehazio.kraitor.R
import com.creativehazio.kraitor.databinding.FragmentHomeBinding
import com.creativehazio.kraitor.ui.adapter.ArtListAdapter
import com.creativehazio.kraitor.ui.adapter.TopArtistListAdapter
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var topArtistListAdapter: TopArtistListAdapter
    private lateinit var artListAdapter : ArtListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        topArtistListAdapter = context?.let { TopArtistListAdapter(it.applicationContext) }!!
        artListAdapter = context?.let { ArtListAdapter(it.applicationContext) }!!

        binding.apply {
            topArtistsRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false)
            topArtistsRecyclerview.adapter = topArtistListAdapter

            topArtRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
                false)
            topArtRecyclerview.adapter = artListAdapter

            createNewArtButton.setOnClickListener {
                it.findNavController().navigate(R.id.action_homeFragment2_to_create_art_graph2)
            }

            createArtTxt.startAnimation(AnimationUtils.loadAnimation(context, R.anim.text_slide_anim))
            joinOthersTxt.startAnimation(AnimationUtils.loadAnimation(context, R.anim.text_slide_anim))
        }

        return view
    }

}