package com.yang.sdk.weight.loading;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.yang.sdk.R;


/**
 * Describe:loadingDialog
 * Created by Yang on 2019/4/15.
 */
public class LoadingDialog {



    private AnimationDrawable animationDrawable;
    private Context mContext;
    private int mResId = -1;
    private Dialog mDialog;

    public LoadingDialog(Context context) {
        this.mContext = context;
        initView();
    }

    public void initView() {
        mDialog = new Dialog(mContext, R.style.style_loadingDialog);

        //点击空白处Dialog不消失
        mDialog.setCanceledOnTouchOutside(false);
        //返回键监听拦截
        mDialog.setOnKeyListener(mListener);

        View mDialogContentView = LayoutInflater.from(mContext).inflate(R.layout.common_layout_loading, null);
        //自定义加载中动画
        if (mResId != -1) {
            mDialogContentView.findViewById(R.id.imv_loading).setBackgroundResource(mResId);
            animationDrawable = (AnimationDrawable) mDialogContentView.findViewById(R.id.imv_loading).getBackground();
        }
        mDialog.setContentView(mDialogContentView);
    }

    Dialog.OnKeyListener mListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK;
        }
    };

    public void showLoading() {
        mDialog.show();
    }

    public void dismiss() {
        mDialog.dismiss();
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }
}
