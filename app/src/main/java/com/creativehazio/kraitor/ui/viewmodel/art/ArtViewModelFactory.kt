package com.creativehazio.kraitor.ui.viewmodel.art

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.creativehazio.kraitor.data.repository.ArtsRepository

class ArtViewModelFactory(
    private val artsRepository: ArtsRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtViewModel::class.java)) {
            return ArtViewModel(artsRepository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown View Model Class")
    }
}