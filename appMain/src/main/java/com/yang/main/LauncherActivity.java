package com.yang.main;


import androidx.appcompat.widget.AppCompatTextView;

import com.yang.main.base.MBaseActivity;
import com.zhangyue.we.x2c.ano.Xml;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;


@Xml(layouts = "activity_launcher")
public class LauncherActivity extends MBaseActivity {

    @BindView(R2.id.txv_number)
    AppCompatTextView txvNumber;
    private Disposable disposable;

    @Override
    protected int bindLayout() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void initView() {
        //start：起始数值,count：发射数量,initialDelay：延迟执行时间,period：发射周期时间,unit：时间单位 //倒计时完毕置为可点击状态
        disposable = Flowable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> txvNumber.setText(String.valueOf(3- aLong))).doOnComplete(() -> {
                    if (false)
                        readyGoThenKill(MainActivity.class);
                    else
                        readyGoThenKill(MainActivity.class);

                }).subscribe();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
