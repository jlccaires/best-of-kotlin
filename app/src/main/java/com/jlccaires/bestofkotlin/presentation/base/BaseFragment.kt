package com.jlccaires.bestofkotlin.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.jlccaires.norrisfacts.presentation.base.BaseContract

abstract class BaseFragment<V : BaseContract.View, T : BaseContract.Presenter<V>> : Fragment() {

    protected lateinit var mPresenter: T

    @LayoutRes
    protected abstract fun getLayout() : Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = init()

    abstract fun init()

    override fun onDestroyView() {
        mPresenter.dispose()
        super.onDestroyView()
    }
}