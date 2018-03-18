package com.zjnu.newspro.model;

import android.content.Context;

import com.zjnu.newspro.model.bean.ArticleUserPojo;
import com.zjnu.newspro.model.db.ArticleUserDBManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/9/23.
 */
// 数据模型层全局类
public class Model {
    private Context mContext;
    private ExecutorService executors = Executors.newCachedThreadPool();
    private ArticleUserDBManager dbManager;
    // 创建对象
    private static Model model = new Model();
    private ArticleUserPojo.User GlobalUser;
    // 私有化构造
    private Model() {

    }

    // 获取单例对象
    public static Model getInstance(){

        return model;
    }

    // 初始化的方法
    public void init(Context context){
        mContext = context;
    }

    // 用户登录成功后的处理方法
    public void loginSuccess(ArticleUserPojo.User account) {

        // 校验
        if(account == null) {
            return;
        }

        if(dbManager != null) {
            dbManager.close();
        }

        dbManager = new ArticleUserDBManager(mContext, "db");
        dbManager.getUserDao().saveUser(account);
        GlobalUser = account;

    }

    public ArticleUserDBManager getDbManager(){
        return dbManager;
    }


    // 获取全局线程池对象
    public ExecutorService getGlobalThreadPool(){
        return executors;
    }


    public ArticleUserPojo.User getGlobalUser() {
        return GlobalUser;
    }
}
