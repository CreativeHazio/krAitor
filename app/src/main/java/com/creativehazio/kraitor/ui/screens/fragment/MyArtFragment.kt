package com.creativehazio.kraitor.ui.screens.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.creativehazio.kraitor.R
import com.creativehazio.kraitor.databinding.FragmentMyArtBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MyArtFragment : Fragment() {

    private var _binding : FragmentMyArtBinding? = null
    private val binding get() = _binding!!

    private lateinit var firestore: FirebaseFirestore
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyArtBinding.inflate(inflater, container, false)
        val view = binding.root

        firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            createArtBtn.setOnClickListener {
                createArtBtn.isEnabled = false
                findNavController().navigate(MyArtFragmentDirections
                    .actionMyArtFragment2ToCreateArtGraph())
            }
        }

    }

    private fun getCurrentUserDetails(){
        firestore = FirebaseFirestore.getInstance()
        var firebaseUser = FirebaseAuth.getInstance().currentUser

        if (firebaseUser != null) {
            firestore.collection("users")
                .document(firebaseUser.uid)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        binding.apply {

                        }
                    }
                }
        }
    }

}