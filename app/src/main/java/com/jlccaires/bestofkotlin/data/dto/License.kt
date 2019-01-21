package com.jlccaires.bestofkotlin.data.dto

data class License(
        val key: Key,
        val name: Name,
        val spdxID: String,
        val url: String?,
        val nodeID: String?
)
