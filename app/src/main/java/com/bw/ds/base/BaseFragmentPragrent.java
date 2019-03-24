package com.bw.ds.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @Auther: 12547
 * @Date: 2019/3/20 20:53:59
 * @Description:
 */
public abstract class BaseFragmentPragrent<T> {
          public  abstract void Bana();
          public abstract void PageDate();

    //结局内存泄漏
    private Reference<T> mviewModel;
    //与P层关联 处理内存泄露
    public  void  attachview (T view){
        mviewModel =new WeakReference<T>(view);
    }
    // P层解除关联
    public  void  detachview(){
        if (mviewModel.get()!=null){
            mviewModel.clear();
            mviewModel=null;
        }
    }

}
