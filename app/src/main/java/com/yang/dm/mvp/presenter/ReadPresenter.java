package com.yang.dm.mvp.presenter;


import com.yang.dm.mvp.contract.ReadContract;
import com.yang.dm.mvp.model.BaseResponse;
import com.yang.dm.mvp.model.Read;
import com.yang.dm.remote.DataManager;
import com.yang.sdk.mvp.BasePresenter;

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
import io.reactivex.schedulers.Schedulers;

/**
 * Describe: 闲读详情Presenter
 * Created by Yang on 2019/1/29.
 */
public class ReadPresenter extends BasePresenter<ReadContract.View> implements ReadContract.Presenter {

    private DataManager mDataManager;
    private Disposable disposable;

    @Inject
    public ReadPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getReadInfo(List<Read> data, @NonNull String id, int page) {
        Observable<BaseResponse<Read>> observable = mDataManager.getReadInfo(id, page);
        disposable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).concatMap(new Function<BaseResponse<Read>, ObservableSource<Read>>() {
            @Override
            public ObservableSource<Read> apply(BaseResponse<Read> readBaseResponse) throws Exception {
                getView().showLoading();
                return Observable.fromIterable(readBaseResponse.getResults());
            }
        }).subscribe(new Consumer<Read>() {
            @Override
            public void accept(Read read) throws Exception {
                data.add(read);
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
                getView().setReadInfo();
                getView().hideLoading();
            }
        });
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null)
            disposable.dispose();
    }
}
