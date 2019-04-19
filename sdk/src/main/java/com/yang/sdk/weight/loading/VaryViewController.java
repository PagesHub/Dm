package com.yang.sdk.weight.loading;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yang.sdk.R;


/**
 * Describe:  加载状态View控制器
 * Created by Yang on 2019/4/15.
 */
public class VaryViewController {
    private IVaryViewHelper helper;
    private LoadingDialog loadingDialog;

    public VaryViewController(View view, Context context) {
        this(new VaryViewHelper(view), context);
    }

    public VaryViewController(IVaryViewHelper helper, Context context) {
        this.helper = helper;
        loadingDialog = new LoadingDialog(context);

    }

    public void showNetworkError(int drawableId, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_layout_message);
        ImageView imageView = layout.findViewById(R.id.message_icon);
        if (drawableId != 0)
            imageView.setImageResource(drawableId);

        if (null != onClickListener) {
            layout.findViewById(R.id.message_icon).setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showError(String errorMsg, int drawableId, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_layout_message);
        TextView textView = layout.findViewById(R.id.message_info);
        if (!errorMsg.isEmpty()) textView.setText(errorMsg);
        ImageView imageView = layout.findViewById(R.id.message_icon);
        if (drawableId != 0)
            imageView.setImageResource(drawableId);

        if (null != onClickListener) {
            layout.findViewById(R.id.message_icon).setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showEmpty(String emptyMsg, int drawableId, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.common_layout_message);
        TextView textView = layout.findViewById(R.id.message_info);
        if (!emptyMsg.isEmpty()) textView.setText(emptyMsg);
        ImageView imageView = layout.findViewById(R.id.message_icon);
        if (drawableId != 0)
            imageView.setImageResource(drawableId);

        if (null != onClickListener) {
            layout.findViewById(R.id.message_icon).setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    public void showLoading(boolean show) {
        if (show) loadingDialog.showLoading();
        else loadingDialog.dismiss();
    }

    public void restore() {
        helper.restoreView();
    }
}
