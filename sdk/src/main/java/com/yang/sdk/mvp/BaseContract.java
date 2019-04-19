package com.yang.sdk.mvp;

/**
 * Describe:  MVP 协约
 * Created by Yang on 2019/4/15.
 */
public interface BaseContract {
    /**
     * Describe:View 基类
     */
    interface BaseView {
        /**
         * show loading
         */
        void showLoading();

        /**
         * hide loading
         */
        void hideLoading();

        /**
         * show erroe
         */
        void showError();

        /**
         * show errorMsg
         *
         * @param errorMessage
         */
        void showErrorMsg(String errorMessage);
    }

    interface Presenter<V extends BaseView> {
        /**
         * 绑定view
         *
         * @param view
         */
        void attachView(V view);

        /**
         * 解除绑定
         */
        void detachView();
    }
}
