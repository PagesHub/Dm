package com.yang.wandroid.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * @author        Yang
 * Description    BaseViewModel
 * CreateDate     2019/5/17 10:55
 */
open class BaseViewModel : ViewModel(), LifecycleObserver {

    private val mException: MutableLiveData<Exception> = MutableLiveData()

    private fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block() }
    }

    fun launch(tryBlock: suspend CoroutineScope.() -> Unit) {
        launchOnUI {
            tryCatch(tryBlock, {}, {}, true)
        }
    }

    private suspend fun tryCatch(
            tryBlock: suspend CoroutineScope.() -> Unit,
            catchBlock: suspend CoroutineScope.(Throwable) -> Unit,
            finallyBlock: suspend CoroutineScope.() -> Unit,
            handleCancellationExceptionManually: Boolean = false) {
        coroutineScope {
            try {
                tryBlock()
            } catch (e: Exception) {
                if (e !is CancellationException || handleCancellationExceptionManually) {
                    mException.value = e
                    catchBlock(e)
                } else {
                    throw e
                }
            } finally {
                finallyBlock()
            }
        }
    }

    suspend fun executeResponse(response: WanResponse<Any>, successBlock: suspend CoroutineScope.() -> Unit,
                                errorBlock: suspend CoroutineScope.() -> Unit) {
        coroutineScope {
            if (response.errorCode == -1) errorBlock()
            else successBlock()
        }
    }

}