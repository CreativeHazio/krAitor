package com.creativehazio.kraitor.ui.screens.fragment

import android.app.PendingIntent
import android.content.ClipData
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.creativehazio.kraitor.databinding.FragmentCreatedArtsBinding
import com.creativehazio.kraitor.ui.adapter.CreatedArtListAdapter
import java.io.File

class CreatedArtsFragment : Fragment() {

    private var _binding : FragmentCreatedArtsBinding? = null
    private val binding get() = _binding!!

    private lateinit var createdArtListAdapter: CreatedArtListAdapter
    private val navigationArgs : CreatedArtsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreatedArtsBinding.inflate(inflater, container, false)
        val view = binding.root

        createdArtListAdapter = context?.let { CreatedArtListAdapter(it.applicationContext) }!!

        binding.apply {
            createdArtsRecyclerView.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            createdArtsRecyclerView.adapter = createdArtListAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artResponse = navigationArgs.artResponse
        createdArtListAdapter.setCreatedArtList(artResponse.data)

        binding.apply {
            saveArtButton.setOnClickListener {
                //TODO Save arts to phone storage and also Cloud storage
            }
            shareArtButton.setOnClickListener {
                //TODO Implicit intent to action to share to other image apps such as whatsapp,instagram

                // Get the image that the user wants to share
//                val images = artResponse.data
//
//                val imageUris = mutableListOf<Uri>()
//                for (imagesString in images){
//                    val imageStringToUri = Uri.parse(imagesString.url)
//                    imageUris.add(imageStringToUri)
//                }
//
//                // Create an intent to share the image
//                val shareIntent = Intent(Intent.ACTION_SEND_MULTIPLE).apply {
//                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//                    putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(imageUris))
//                    type = "image/*"
//                }
//
//                // Launch the share dialog
//                startActivity(Intent.createChooser(shareIntent, "Share image using"))

            }
        }
    }


}











