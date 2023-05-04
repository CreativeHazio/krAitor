package com.creativehazio.kraitor.data.model

data class ArtStyle(
    //TODO Add a String val for preset(From chatgpt) when a cardview is pressed to be stored in a liveData
    val resourceId: Int,
    val name : String,
    val preset : String
)
