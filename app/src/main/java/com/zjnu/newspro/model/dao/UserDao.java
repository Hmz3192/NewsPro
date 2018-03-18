package com.zjnu.newspro.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zjnu.newspro.model.bean.ArticleUserPojo;
import com.zjnu.newspro.model.db.ArticleUserDBHelper;
import com.zjnu.newspro.model.table.UserTable;

/**
 * Created by Administrator on 2016/9/24.
 */
// 联系人表的操作类
public class UserDao {
    private ArticleUserDBHelper mHelper;

    public UserDao(ArticleUserDBHelper helper) {
        mHelper = helper;
    }


    // 获取联系人
    public ArticleUserPojo.User getUser(String name) {

        if (name == null) {
            return null;
        }

        // 获取数据库链接
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 执行查询语句
        String sql = "select * from " + UserTable.TAB_NAME + " where " + UserTable.COL_NAME + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{name});

        ArticleUserPojo.User userInfo = null;

        if (cursor.moveToNext()) {
            userInfo = new ArticleUserPojo.User();

            userInfo.setAge(Integer.valueOf(cursor.getString(cursor.getColumnIndex(UserTable.COL_AGE))));
            userInfo.setAvatar(cursor.getString(cursor.getColumnIndex(UserTable.COL_AVATAR)));
            userInfo.setEmail(cursor.getString(cursor.getColumnIndex(UserTable.COL_EMAIL)));
            userInfo.setIntroduction(cursor.getString(cursor.getColumnIndex(UserTable.COL_INTRO)));
            userInfo.setLocation(cursor.getString(cursor.getColumnIndex(UserTable.COL_LOCATION)));
            userInfo.setPassword(cursor.getString(cursor.getColumnIndex(UserTable.COL_PASSWORD)));
            userInfo.setPhone(cursor.getString(cursor.getColumnIndex(UserTable.COL_PHONE)));
            userInfo.setSex(Integer.valueOf(cursor.getString(cursor.getColumnIndex(UserTable.COL_SEX))));
            userInfo.setUserId(Long.valueOf(cursor.getString(cursor.getColumnIndex(UserTable.COL_USER_ID))));
            userInfo.setUserName(cursor.getString(cursor.getColumnIndex(UserTable.COL_NAME)));
            userInfo.setWork(cursor.getString(cursor.getColumnIndex(UserTable.COL_WORK)));
            userInfo.setVip(Integer.valueOf(cursor.getString(cursor.getColumnIndex(UserTable.COL_VIP))));
        }

        // 关闭资源
        cursor.close();

        // 返回数据
        return userInfo;
    }


    // 保存单个人
    public void saveUser(ArticleUserPojo.User user) {

        if (user == null) {
            return;
        }

        // 获取数据库链接
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 执行保存语句
        ContentValues values = new ContentValues();
        values.put(UserTable.COL_USER_ID, user.getUserId());
        values.put(UserTable.COL_AGE, user.getAge());
        values.put(UserTable.COL_AVATAR, user.getAvatar());
        values.put(UserTable.COL_EMAIL, user.getEmail());
        values.put(UserTable.COL_INTRO, user.getIntroduction());
        values.put(UserTable.COL_LOCATION, user.getLocation());
        values.put(UserTable.COL_NAME, user.getUserName());
        values.put(UserTable.COL_PASSWORD, user.getPassword());
        values.put(UserTable.COL_VIP, user.getVip());
        values.put(UserTable.COL_WORK, user.getWork());
        values.put(UserTable.COL_SEX, user.getSex());
        db.replace(UserTable.TAB_NAME, null, values);
    }
}
