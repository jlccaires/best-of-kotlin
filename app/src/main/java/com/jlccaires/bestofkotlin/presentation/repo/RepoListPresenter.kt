package com.jlccaires.bestofkotlin.presentation.repo

import com.jlccaires.bestofkotlin.data.GithubClient
import com.jlccaires.bestofkotlin.presentation.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers

class RepoListPresenter : BasePresenter<RepoListContract.View>(), RepoListContract.Presenter {

    private val mClient = GithubClient.get()
    private var mCurrentPage: Int = 0
    private var mSearch: String? = ""

    override fun listRepos(search: String?) {
        if (search != null && search.length < 2) return
        mCurrentPage = 0
        mSearch = search
        getView().clearRepos()
        moreRepos()
    }

    override fun moreRepos() {
        ++mCurrentPage
        getView().loading(true)
        val d = mClient.searchRepos(search = "${mSearch.orEmpty()} in:name language:kotlin", page = mCurrentPage)
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.items }
                .subscribe(
                        { response ->
                            if (response.isNotEmpty()) {
                                getView().addRepos(response)
                            }
                            getView().loading(false)
                        },
                        {
                            getView().showError()
                            getView().loading(false)
                        }
                )
        disposables.add(d)
    }

}
