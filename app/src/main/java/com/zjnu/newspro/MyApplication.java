package com.zjnu.newspro;

import android.app.Application;
import android.content.Context;

import com.zjnu.newspro.model.Model;

/**
 * Created by Administrator on 2016/9/23.
 */
public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();


        // 初始化数据模型层类
        Model.getInstance().init(this);

        // 初始化全局上下文对象
        mContext = this;
    }

    // 获取全局上下文对象
    public static Context getGlobalApplication(){
        return mContext;
    }
}
