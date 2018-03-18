package com.zjnu.newspro.model.db;

import android.content.Context;

import com.zjnu.newspro.model.dao.ArticleDao;
import com.zjnu.newspro.model.dao.UserDao;

/**
 * Created by Administrator on 2016/9/24.
 */
// 联系人和邀请信息表的操作类的管理类
public class ArticleUserDBManager {

    private final ArticleUserDBHelper dbHelper;
    private final ArticleDao articleDao;
    private final UserDao userDao;

    public ArticleUserDBManager(Context context, String name) {
        // 创建数据库
        dbHelper = new ArticleUserDBHelper(context, name);

        // 创建该数据库中两张表的操作类
        articleDao = new ArticleDao(dbHelper);
        userDao = new UserDao(dbHelper);
    }

    public  ArticleDao getArticleDao(){
        return articleDao;
    }
    public UserDao getUserDao(){
        return userDao;
    }

    // 关闭数据库的方法
    public void close() {
        dbHelper.close();
    }
}
