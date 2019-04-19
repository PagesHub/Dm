package com.yang.dm.mvp.presenter;


import com.yang.dm.mvp.contract.NewsPagerContract;
import com.yang.dm.mvp.model.BaseResponse;
import com.yang.dm.mvp.model.ReadClassifyChild;
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
 * Describe: 闲读分子类Presenter
 * Created by Yang on 2019/1/22.
 */
public class NewsPagerPresenter extends BasePresenter<NewsPagerContract.View> implements NewsPagerContract.Presenter {
    private DataManager mDataManager;
    private Disposable disposable;

    @Inject
    public NewsPagerPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void getReadClassifyChild(@NonNull List<ReadClassifyChild> data, @NonNull String child) {
        Observable<BaseResponse<ReadClassifyChild>> observable = mDataManager.getReadClassifyChild(child);
        disposable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).concatMap(new Function<BaseResponse<ReadClassifyChild>, ObservableSource<ReadClassifyChild>>() {
            @Override
            public ObservableSource<ReadClassifyChild> apply(BaseResponse<ReadClassifyChild> readClassifyChildBaseResponse) throws Exception {

                return Observable.fromIterable(readClassifyChildBaseResponse.getResults());
            }
        }).subscribe(new Consumer<ReadClassifyChild>() {

            @Override
            public void accept(ReadClassifyChild readClassifyChild) throws Exception {
                data.add(readClassifyChild);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                getView().showErrorMsg(throwable.getMessage());

            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                getView().getReadClassifyChild();
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
