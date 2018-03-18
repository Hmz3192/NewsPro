package com.zjnu.newspro.model.table;

/**
 * Created by Administrator on 2016/9/24.
 */
// 联系人表的建表语句
public class ArticleTable {
    public static final String TAB_NAME = "tab_article";

    public static final String COL_ARTICLE_ID = "article_id";
    public static final String COL_CONTENT = "article_content";
    public static final String COL_TITLE = "article_title";
    public static final String COL_KIND = "kind_parent_name";
    public static final String COL_USER = "user_id";
    public static final String COL_SUMMARY = "article_summary";
    public static final String COL_SOURCE = "article_source";
    public static final String COL_HINTS = "article_hints";
    public static final String COL_TIME = "release_time";
    public static final String COL_COLLECTION = "article_collection";
    public static final String COL_UP = "article_up";
    public static final String COL_DOWN = "article_down";
    public static final String COL_AVATAR = "article_avatar";




    public static final String CREATE_TAB = "create table "
            + TAB_NAME + " ("
            + COL_ARTICLE_ID + " text primary key,"
            + COL_CONTENT + " text,"
            + COL_TITLE + " text,"
            + COL_KIND + " text,"
            + COL_USER + " text,"
            + COL_SUMMARY + " text,"
            + COL_SOURCE + " text,"
            + COL_HINTS + " text,"
            + COL_TIME + " text,"
            + COL_COLLECTION + " text,"
            + COL_UP + " text,"
            + COL_AVATAR + " text,"
            + COL_DOWN + " text);";



}
