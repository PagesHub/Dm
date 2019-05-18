package com.yang.wandroid.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.yang.sdk.mvp.BaseFragment

/**
 * @author        Yang
 * Description    玩Android kotlin BaseFragment
 * CreateDate     2019/5/17 10:55
 */
abstract class WABaseFragment<VM : BaseViewModel> : BaseFragment() {
    protected lateinit var mViewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        startObserve()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
            lifecycle.addObserver(mViewModel)

        }
    }

    open fun startObserve() {}

    open fun providerVMClass(): Class<VM>? = null


    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }
}