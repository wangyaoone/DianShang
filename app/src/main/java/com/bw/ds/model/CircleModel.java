package com.bw.ds.model;

import android.util.Log;

import com.bw.ds.api.Api;
import com.bw.ds.api.ApiService;
import com.bw.ds.bean.CircleBean;
import com.bw.ds.util.UtilDate;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: 12547
 * @Date: 2019/3/26 11:48:55
 * @Description:
 */
public class CircleModel {

    //订阅管理器
    CompositeDisposable compositeDisposable =new CompositeDisposable();
    //接口回调
     public interface HDate{
          void getdate(List<CircleBean.ResultBean> result);
          void a(CompositeDisposable compositeDisposable);
     }
     private HDate hDate;
     public void sethDate(HDate hDate) {
         this.hDate = hDate;
     }

    public void circleDate(int page) {
        ApiService getserviser = UtilDate.getUtilDate().getserviser(Api.Url, ApiService.class);
        //通过retrofit创建被观察者Flowable
        Flowable<CircleBean> flowable = getserviser.getcircle(page, 20);
        //指定发送事件的线程，io线程
        DisposableSubscriber<CircleBean> circleBeanFlowable = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//接收事件在主线程
                .subscribeWith(new DisposableSubscriber<CircleBean>() {
                    @Override
                    public void onNext(CircleBean circleBean) {
                        //成功的方法
                   List<CircleBean.ResultBean> result = circleBean.getResult();
                      /*  Log.i("aaa",result+"");*/
                        hDate.getdate(result);
                        hDate.a(compositeDisposable);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //添加订阅者到订阅管理器里面
        compositeDisposable.add(circleBeanFlowable);
    }

}

