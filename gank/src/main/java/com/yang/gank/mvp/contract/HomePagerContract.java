package com.yang.gank.mvp.contract;

import com.yang.gank.mvp.model.GanHuo;
import com.yang.sdk.mvp.BaseContract;

import java.util.List;


/**
 * Describe:
 * Created by Yang on 2019/1/21.
 */
public interface HomePagerContract {
    interface View extends BaseContract.BaseView {

        void setGanHuo(List<GanHuo> data);
    }

    interface Presenter extends BaseContract.Presenter<HomePagerContract.View> {

        void getGanHuo(String category, int page);

    }
}
