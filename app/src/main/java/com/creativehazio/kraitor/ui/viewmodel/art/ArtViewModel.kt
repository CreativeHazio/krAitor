package com.creativehazio.kraitor.ui.viewmodel.art

import androidx.lifecycle.*
import com.creativehazio.kraitor.data.model.Art
import com.creativehazio.kraitor.data.model.ArtResponse
import com.creativehazio.kraitor.data.repository.ArtsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ArtViewModel(
    private val artsRepository: ArtsRepository
) : ViewModel() {

    private val _artSize = MutableLiveData(1)
    val artSize : LiveData<Int> get() = _artSize

    private val _artStyle = MutableLiveData(".")
    val artStyle : LiveData<String> get() = _artStyle

    //TODO Implement isLoading to show loading animations
    private val _isLoading = MutableSharedFlow<Boolean>()
    val isLoading = _isLoading.asSharedFlow()

    private val _createArts : MutableLiveData<Response<ArtResponse>> = MutableLiveData()
    val createdArts : LiveData<Response<ArtResponse>>
        get() = _createArts

    fun createArt(art: Art) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = artsRepository.getGeneratedArts(art)
            withContext(Dispatchers.Main) {
                _createArts.value = response
            }
        }
    }

    fun setArtSize(size : Int) {
        _artSize.value = size
    }

    fun setArtStyle(artStyle: String) {
        _artStyle.value = artStyle
    }

    fun getCommunityArt() {

    }

    fun getMyArt() {

    }
}