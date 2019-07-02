package com.yang.kotlin.base

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.yang.sdk.mvp.BaseActivity

/**
 * Describe: Kotlin Module基础Activity
 * Created by Yang on 2019/6/21  14:40
 */
abstract class KotlinActivity<VM : KotlinViewModule> : BaseActivity() {

    protected lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        startObserve()
        super.onCreate(savedInstanceState)
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
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }

}