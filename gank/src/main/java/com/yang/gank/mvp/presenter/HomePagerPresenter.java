package com.yang.gank.mvp.presenter;


import com.yang.gank.mvp.contract.HomePagerContract;
import com.yang.gank.mvp.model.BaseResponse;
import com.yang.gank.mvp.model.GanHuo;
import com.yang.gank.remote.DataManager;
import com.yang.sdk.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Describe:  干货分类 Presenter
 * Created by Yang on 2019/1/22.
 */
public class HomePagerPresenter extends BasePresenter<HomePagerContract.View> implements HomePagerContract.Presenter {
    private DataManager mDataManager;
    private Disposable disposable;

    @Inject
    public HomePagerPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getGanHuo(@NonNull String category, int page) {
        List<GanHuo> data = new ArrayList<>();
        Observable<BaseResponse<GanHuo>> observable = mDataManager.getGanHuo(category, page);
        disposable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).concatMap((Function<BaseResponse<GanHuo>, ObservableSource<GanHuo>>) ganHuoBaseResponse -> {
            getView().showLoading();
            return Observable.fromIterable(ganHuoBaseResponse.getResults());
        }).filter(ganHuo -> !ganHuo.getType().equals("福利") || !ganHuo.getType().equals("休息视频")).subscribe(data::add, throwable -> {
            getView().showErrorMsg(throwable.getMessage());
            getView().hideLoading();
        }, () -> {
            getView().setGanHuo(data);
            getView().hideLoading();
        });
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
