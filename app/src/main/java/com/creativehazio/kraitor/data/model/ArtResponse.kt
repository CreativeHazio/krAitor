package com.creativehazio.kraitor.data.model

import android.os.Parcel
import android.os.Parcelable

data class ArtResponse(
    val created: Int,
    val data: List<Data>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        TODO("data")
    ) {
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {

    }

    companion object CREATOR : Parcelable.Creator<ArtResponse> {
        override fun createFromParcel(parcel: Parcel): ArtResponse {
            return ArtResponse(parcel)
        }

        override fun newArray(size: Int): Array<ArtResponse?> {
            return arrayOfNulls(size)
        }
    }
}
