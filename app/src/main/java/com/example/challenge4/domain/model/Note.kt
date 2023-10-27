package com.example.challenge4.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(

    var id: Int,
    var title: String? = null,
    var subTitle:String? = null,
    var description: String? = null,
    var date: String? = null,
    var color:String? = null
) : Parcelable
