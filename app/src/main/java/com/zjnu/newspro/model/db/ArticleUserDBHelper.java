package com.zjnu.newspro.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zjnu.newspro.model.table.ArticleTable;
import com.zjnu.newspro.model.table.UserTable;

/**
 * Created by Administrator on 2016/9/24.
 */
public class ArticleUserDBHelper extends SQLiteOpenHelper {

    public ArticleUserDBHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建用户的表
        db.execSQL(UserTable.CREATE_TAB);

        // 创建文章信息的表
        db.execSQL(ArticleTable.CREATE_TAB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
