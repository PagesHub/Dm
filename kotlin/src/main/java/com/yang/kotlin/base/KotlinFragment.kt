package com.yang.kotlin.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.yang.sdk.mvp.BaseFragment

/**
 * Describe: Kotlin Module基础Fragment
 * Created by Yang on 2019/6/21  14:42
 */
abstract class KotlinFragment<VM : KotlinViewModule> : BaseFragment() {

    protected lateinit var mViewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        startObserve()
        super.onViewCreated(view, savedInstanceState)

    }

    open fun startObserve() {}

    /**
     * 初始化ViewModule
     */
    private fun initVM() {
        providerVMClass()?.let {
            mViewModel = ViewModelProviders.of(this).get(it)
            lifecycle.addObserver(mViewModel)
        }
    }

    open fun providerVMClass(): Class<VM>? = null

    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }

}