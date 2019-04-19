package com.yang.dm.mvp.presenter;


import com.yang.dm.mvp.contract.NewsContract;
import com.yang.dm.mvp.model.BaseResponse;
import com.yang.dm.mvp.model.ReadClassify;
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
 * Describe:  闲读分类Presenter
 * Created by Yang on 2019/1/25.
 */
public class NewsPresenter extends BasePresenter<NewsContract.View> implements NewsContract.Presenter {
    private DataManager mDataManager;
    private Disposable disposable;

    @Inject
    public NewsPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getReadClassify(@NonNull List<ReadClassify> date) {
        Observable<BaseResponse<ReadClassify>> observable = mDataManager.getReadClassify();
        disposable = observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).concatMap(new Function<BaseResponse<ReadClassify>, ObservableSource<ReadClassify>>() {
            @Override
            public ObservableSource<ReadClassify> apply(BaseResponse<ReadClassify> readClassifyBaseResponse) throws Exception {
                getView().showLoading();
                return Observable.fromIterable(readClassifyBaseResponse.getResults());
            }
        }).subscribe(new Consumer<ReadClassify>() {

            @Override
            public void accept(ReadClassify readClassify) throws Exception {
                date.add(readClassify);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                getView().hideLoading();
                getView().showErrorMsg(throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                getView().setReadClassify();
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
