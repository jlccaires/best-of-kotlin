package com.jlccaires.bestofkotlin.data.dto

data class GithubSearchResponse (
    val totalCount: Long,
    val incompleteResults: Boolean,
    val items: List<Item>
)