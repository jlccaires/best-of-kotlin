package com.jlccaires.bestofkotlin.data.dto

import com.google.gson.annotations.SerializedName

data class Owner(
        val login: String,
        val id: Long,
        @SerializedName("avatar_url")
        val avatarURL: String,
        val gravatarID: String
)