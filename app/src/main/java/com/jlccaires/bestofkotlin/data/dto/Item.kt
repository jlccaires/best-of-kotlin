package com.jlccaires.bestofkotlin.data.dto

import com.google.gson.annotations.SerializedName

data class Item(
        val id: Long,
        val name: String,
        @SerializedName("stargazers_count")
        val stargazersCount: Long,
        @SerializedName("forks_count")
        val forksCount: Long,
        val owner: Owner
)