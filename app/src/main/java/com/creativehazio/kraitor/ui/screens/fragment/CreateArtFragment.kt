package com.creativehazio.kraitor.ui.screens.fragment

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.getSystemService
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creativehazio.kraitor.R
import com.creativehazio.kraitor.data.datasource.network.openaiapi.OpenAiRetrofitInstance
import com.creativehazio.kraitor.data.datasource.network.openaiapi.OpenApiService
import com.creativehazio.kraitor.data.model.Art
import com.creativehazio.kraitor.data.model.ArtResponse
import com.creativehazio.kraitor.data.model.ArtStyle
import com.creativehazio.kraitor.data.repository.ArtsRepository
import com.creativehazio.kraitor.databinding.FragmentCreateArtBinding
import com.creativehazio.kraitor.ui.adapter.ArtStyleListAdapter
import com.creativehazio.kraitor.ui.viewmodel.art.ArtViewModel
import com.creativehazio.kraitor.ui.viewmodel.art.ArtViewModelFactory
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import retrofit2.Retrofit

class CreateArtFragment : Fragment() {

    private var _binding : FragmentCreateArtBinding? = null
    private val binding get() = _binding!!

    private lateinit var artStyleListAdapter: ArtStyleListAdapter

    private lateinit var connectivityManager : ConnectivityManager

    private lateinit var viewModel: ArtViewModel
    private lateinit var viewModelFactory: ArtViewModelFactory
    private lateinit var artsRepository: ArtsRepository
    private lateinit var openApiService: OpenApiService
    private lateinit var retrofit: Retrofit

    private var numberOfArts : Int = 1
    private var artStyle : String = "."

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateArtBinding.inflate(inflater, container, false)
        val view = binding.root

        artStyleListAdapter = context?.let { ArtStyleListAdapter(it.applicationContext) }!!

        binding.apply {
            artStyleRecyclerview.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL,false)
            artStyleRecyclerview.adapter = artStyleListAdapter

            createArtTxt.startAnimation(AnimationUtils.loadAnimation(context, R.anim.text_slide_anim))
            selectOptionsText.startAnimation(AnimationUtils.loadAnimation(context, R.anim.text_slide_anim))
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrofit = OpenAiRetrofitInstance.getRetrofitInstance()
        openApiService = retrofit.create(OpenApiService::class.java)
        artsRepository = ArtsRepository(openApiService)
        viewModelFactory = ArtViewModelFactory(artsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ArtViewModel::class.java)


        getNumberOfArtPressed()
        getArtstylePressed()

        println(artStyle)

        binding.apply {
            createArtBtn.setOnClickListener {
                createArtBtn.isEnabled = false
                createArt()
            }

        }

    }

    private fun createArt(){
        connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {
            binding.apply {
                val prompt = artPromptEdt.text.toString() + " "+artStyle
                val artSize = artSizeSpinner.selectedItem.toString()

                if (validateInput(prompt)) {
                    val art = Art("CreativeHazio", numberOfArts, prompt, artSize)
                    println(art.toString())
                    viewModel.createArt(art)
                    observeData(art)
                }
            }
        } else {
            val snackbar = Snackbar
                .make(binding.createArtConstraintView, "Oops, Check your internet connection",
                    Snackbar.LENGTH_INDEFINITE)
            snackbar.setAction("Retry") {
                createArt()
            }
            snackbar.show()
        }
    }

    private fun validateInput(prompt: String) : Boolean {

        return if (prompt.isEmpty()){
            Toast.makeText(context, "Prompt can't be empty", Toast.LENGTH_SHORT).show()
            false
        } else if (prompt.length > 400){
            Toast.makeText(context, "Maximum prompt length is 400 characters", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }

    }

    private fun observeData(art: Art) {
        viewModel.createdArts.observe(viewLifecycleOwner) { response ->
            val artResponse : ArtResponse? = response.body()

            if (artResponse != null) {
                findNavController().navigate(CreateArtFragmentDirections
                    .actionCreateArtFragment3ToCreatedArtsFragment(artResponse))
            } else {
                val snackbar = Snackbar.make(binding.createArtConstraintView, "Unable to create Art"
                    , Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("Retry"){
                    createArt()
                }
                snackbar.show()

            }
        }
    }

    private fun getNumberOfArtPressed() {
        binding.apply {
            createOneImgBtn.setOnClickListener {
                viewModel.setArtSize(1)
                context?.resources?.let { it1 -> createOneImgBtn
                    .setBackgroundColor(it1.getColor(R.color.light_purple,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createTwoImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createThreeImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createFourImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
            }
            createTwoImgBtn.setOnClickListener {
                viewModel.setArtSize(2)
                context?.resources?.let { it1 -> createTwoImgBtn
                    .setBackgroundColor(it1.getColor(R.color.light_purple,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createOneImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createThreeImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createFourImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
            }
            createThreeImgBtn.setOnClickListener {
                viewModel.setArtSize(3)
                context?.resources?.let { it1 -> createThreeImgBtn
                    .setBackgroundColor(it1.getColor(R.color.light_purple,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createTwoImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createOneImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createFourImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
            }
            createFourImgBtn.setOnClickListener {
                viewModel.setArtSize(4)
                context?.resources?.let { it1 -> createFourImgBtn
                    .setBackgroundColor(it1.getColor(R.color.light_purple,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createTwoImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createThreeImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
                context?.resources?.let { it1 -> createOneImgBtn
                    .setBackgroundColor(it1.getColor(R.color.transparent,
                        requireContext().applicationContext.theme)) }
            }
        }

        viewModel.artSize.observe(viewLifecycleOwner) {
            numberOfArts = it
        }
    }

    private fun getArtstylePressed() {
        artStyleListAdapter.setListener(object : ArtStyleListAdapter.Listener{

            override fun onClick(
                artStyleList: List<ArtStyle>,
                position: Int) {

                viewModel.setArtStyle(artStyleList.get(position).preset)
            }

        })

        viewModel.artStyle.observe(viewLifecycleOwner){
            artStyle = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}