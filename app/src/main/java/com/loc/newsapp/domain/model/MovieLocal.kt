package com.loc.newsapp.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class MovieLocal(
    val thumbnail: String?,
    val source: String,
    val title: String,
    @PrimaryKey val id: String,
):Parcelable
