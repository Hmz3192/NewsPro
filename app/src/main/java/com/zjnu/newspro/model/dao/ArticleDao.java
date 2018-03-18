package com.zjnu.newspro.model.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zjnu.newspro.model.bean.ArticleUserPojo;
import com.zjnu.newspro.model.db.ArticleUserDBHelper;
import com.zjnu.newspro.model.table.ArticleTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24.
 */
// 联系人表的操作类
public class ArticleDao {
    private ArticleUserDBHelper mHelper;

    public ArticleDao(ArticleUserDBHelper helper) {
        mHelper = helper;
    }


    // 通过种类获取文章信息
    public List<ArticleUserPojo.Article> getArticlesByKind(String kind,String offset) {

        if (kind == null) {
            return null;
        }

        List<ArticleUserPojo.Article> articles = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        // 执行查询语句
        String sql = "select * from " + ArticleTable.TAB_NAME + " where " + ArticleTable.COL_KIND + " = ? ";
        Cursor cursor = db.rawQuery(sql, new String[]{kind});
        while (cursor.moveToNext()) {
            ArticleUserPojo.Article article = new ArticleUserPojo.Article();

            article.setUserId(Long.valueOf(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_USER))));
            article.setArticleAvatar(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_AVATAR)));

            article.setArticleCollection(Integer.valueOf(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_COLLECTION))));
            article.setArticleContent(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_CONTENT)));
            article.setArticleDown(Integer.valueOf(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_DOWN))));

            article.setArticleHints(Integer.valueOf(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_HINTS))));
            article.setArticleId(Long.valueOf(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_ARTICLE_ID))));
            article.setArticlePower("");

            article.setReleaseTime(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_TIME)));
            article.setArticleSource(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_SOURCE)));

            article.setArticleSummary(cursor.getString(cursor.getColumnIndex(ArticleTable.COL_SUMMARY)));

            articles.add(article);
        }
        int i = (articles.size() - (Integer.valueOf(offset) + 10));
        if (articles.size() > 0 && i >= 0) {
            Integer end = Integer.valueOf(offset) + 10;
            articles.subList(Integer.parseInt(offset), end);
        } else if (articles.size() > 0 && i < 0) {
            Integer end = articles.size() % 10;
            articles.subList(Integer.parseInt(offset), end);
        }
        // 返回查询的数据
        return articles;
    }

    // 保存单个文章
    public void saveArticle(ArticleUserPojo.Article article) {

        if (article == null) {
            return;
        }

        // 获取数据库链接
        SQLiteDatabase db = mHelper.getReadableDatabase();

        // 执行保存语句
        ContentValues values = new ContentValues();
        values.put(ArticleTable.COL_ARTICLE_ID, article.getArticleId());
        values.put(ArticleTable.COL_AVATAR, article.getArticleAvatar());
        values.put(ArticleTable.COL_COLLECTION, article.getArticleCollection());
        values.put(ArticleTable.COL_CONTENT, article.getArticleContent());
        values.put(ArticleTable.COL_DOWN, article.getArticleDown());
        values.put(ArticleTable.COL_HINTS, article.getArticleHints());
        values.put(ArticleTable.COL_KIND, article.getKindParentName());
        values.put(ArticleTable.COL_SOURCE, article.getArticleSource());
        values.put(ArticleTable.COL_SUMMARY, article.getArticleSummary());
        values.put(ArticleTable.COL_TIME, article.getReleaseTime());
        values.put(ArticleTable.COL_UP, article.getArticleUp());
        values.put(ArticleTable.COL_USER, article.getUserId());
        values.put(ArticleTable.COL_TITLE, article.getArticleTitle());

        db.replace(ArticleTable.TAB_NAME, null, values);
    }


    // 保存多个信息
    public void saveListArticles(List<ArticleUserPojo.Article> articles) {

        if (articles == null || articles.size() <= 0) {
            return;
        }

        for (ArticleUserPojo.Article contact : articles) {
            saveArticle(contact);
        }
    }
}
