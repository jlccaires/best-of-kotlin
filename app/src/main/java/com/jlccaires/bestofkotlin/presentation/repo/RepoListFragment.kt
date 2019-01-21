package com.jlccaires.bestofkotlin.presentation.repo

import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jlccaires.bestofkotlin.R
import com.jlccaires.bestofkotlin.data.dto.Item
import com.jlccaires.bestofkotlin.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_repo_list.*

class RepoListFragment : BaseFragment<RepoListContract.View, RepoListContract.Presenter>(), RepoListContract.View {

    private lateinit var mAdapter: RepoListAdapter

    override fun getLayout() = R.layout.fragment_repo_list

    override fun init() {
        setHasOptionsMenu(true)
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent, R.color.colorPrimaryDark)
        swipeRefreshLayout.setOnRefreshListener { mPresenter.listRepos(null) }

        mAdapter = RepoListAdapter()
        recyclerRepo.adapter = mAdapter
        recyclerRepo.addOnScrollListener(object : EndlessRecyclerViewScrollListener(recyclerRepo.layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                mPresenter.moreRepos()
            }

        })

        mPresenter = RepoListPresenter()
        mPresenter.attachView(this)
        mPresenter.listRepos(null)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        var searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            val action = fun(q: String?): Boolean {
                mPresenter.listRepos(if (q!!.isEmpty()) null else q)
                return false
            }

            override fun onQueryTextSubmit(query: String?) = action(query)
            override fun onQueryTextChange(newText: String?) = action(newText)
        })
    }

    override fun addRepos(repos: List<Item>) {
        mAdapter.addAll(repos)
    }

    override fun showError() {
        if (view != null) {
            Snackbar.make(view!!, R.string.error_message, Snackbar.LENGTH_SHORT)
                    .setAction(R.string.ok) {}
                    .show()
        }
    }

    override fun clearRepos() {
        mAdapter.clear()
    }

    override fun loading(loading: Boolean) {
        swipeRefreshLayout.isRefreshing = loading
    }

}
