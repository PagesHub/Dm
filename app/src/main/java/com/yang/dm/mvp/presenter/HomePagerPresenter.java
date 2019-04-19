package com.yang.dm.mvp.presenter;


import com.yang.dm.mvp.contract.HomePagerContract;
import com.yang.dm.mvp.model.BaseResponse;
import com.yang.dm.mvp.model.GanHuo;
import com.yang.dm.remote.DataManager;
import com.yang.sdk.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
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
        disposable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).concatMap(new Function<BaseResponse<GanHuo>, ObservableSource<GanHuo>>() {
            @Override
            public ObservableSource<GanHuo> apply(@NonNull BaseResponse<GanHuo> ganHuoBaseResponse) throws Exception {
                getView().showLoading();
                return Observable.fromIterable(ganHuoBaseResponse.getResults());
            }
        }).filter(new Predicate<GanHuo>() {
            @Override
            public boolean test(GanHuo ganHuo) throws Exception {
                return !ganHuo.getType().equals("福利") || !ganHuo.getType().equals("休息视频");
            }
        }).subscribe(new Consumer<GanHuo>() {
            @Override
            public void accept(GanHuo ganHuo) throws Exception {
                data.add(ganHuo);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                getView().showErrorMsg(throwable.getMessage());
                getView().hideLoading();
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                getView().setGanHuo(data);
                getView().hideLoading();
            }
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
