package com.jlccaires.bestofkotlin.data

import com.jlccaires.bestofkotlin.data.dto.GithubSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepos(
        @Query("q") search: String?,
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): Observable<GithubSearchResponse>
}