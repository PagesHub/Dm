package com.yang.sdk.utils.rxUtils;

import java.util.HashMap;
import java.util.Objects;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * Describe:RxBus用于组件之间的通讯
 * Created by Yang on 2018/12/19.
 */
public class RxBus {
    private HashMap<String, CompositeDisposable> mSubscriptionMap;
    private static volatile RxBus mRxBus;
    private final FlowableProcessor<Object> mSubject;

    /**
     * 单例模式
     */
    public static RxBus getInstanceBus() {
        if (mRxBus == null) {
            synchronized (RxBus.class) {
                if (mRxBus == null) {
                    mRxBus = new RxBus();
                }
            }
        }
        return mRxBus;
    }

    public RxBus() {
        //PublishSubject.create().toSerialized();
        mSubject = PublishProcessor.create().toSerialized();
    }

    /**
     * 发送事件
     *
     * @param code 标志码
     * @param o    传递的数据
     */
    public void post(int code, Object o) {
        mSubject.onNext(new RxBusMessage(code, o));
    }

    /**
     * 返回指定类型的带背压的Flowable实例
     *
     * @param type
     * @param <T>
     * @return
     */
    public <T> Flowable<T> getObservable(Class<T> type) {
        //return mSubject.toFlowable(BackpressureStrategy.BUFFER).ofType(type);
        return mSubject.ofType(type);
    }

    /**
     * 订阅事件
     *
     * @param type  事件的类型
     * @param next  事件的处理程序
     * @param error 事件的异常处理
     * @param <T>
     * @return
     */
    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next, Consumer<Throwable> error) {
        return getObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next, error);
    }

    public <T> Disposable doSubscribe(Class<T> type, Consumer<T> next) {
        return getObservable(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next);
    }

    /**
     * 是否已有观察者订阅
     *
     * @return
     */
    public boolean hasObservers() {
        return mSubject.hasSubscribers();
    }

    /**
     * 保存订阅后的disposable
     *
     * @param o
     * @param disposable
     */
    public void addSubscription(Object o, Disposable disposable) {
        if (mSubscriptionMap == null) {
            mSubscriptionMap = new HashMap<>();
        }
        String key = o.getClass().getName();
        if (mSubscriptionMap.get(key) != null) {
            Objects.requireNonNull(mSubscriptionMap.get(key)).add(disposable);
        } else {
            //一次性容器,可以持有多个并提供 添加和移除。
            CompositeDisposable disposables = new CompositeDisposable();
            disposables.add(disposable);
            mSubscriptionMap.put(key, disposables);
        }
    }

    /**
     * 取消订阅
     *
     * @param o
     */
    public void unSubscribe(Object o) {
        if (mSubscriptionMap == null) {
            return;
        }
        String key = o.getClass().getName();
        if (!mSubscriptionMap.containsKey(key)) {
            return;
        }
        if (mSubscriptionMap.get(key) != null) {
            Objects.requireNonNull(mSubscriptionMap.get(key)).dispose();
        }

        mSubscriptionMap.remove(key);
    }
}
