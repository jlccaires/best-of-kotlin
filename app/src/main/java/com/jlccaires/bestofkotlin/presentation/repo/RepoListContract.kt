package com.jlccaires.bestofkotlin.presentation.repo

import com.jlccaires.bestofkotlin.data.dto.Item
import com.jlccaires.norrisfacts.presentation.base.BaseContract

interface RepoListContract {

    interface View : BaseContract.View {
        fun addRepos(repos: List<Item>)
        fun clearRepos()
        fun loading(loading: Boolean)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun listRepos(search: String?)
        fun moreRepos()
    }
}