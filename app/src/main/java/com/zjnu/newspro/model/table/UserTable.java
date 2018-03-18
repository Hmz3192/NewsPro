package com.zjnu.newspro.model.table;

/**
 * Created by Administrator on 2016/9/24.
 */
// 联系人表的建表语句
public class UserTable {
    public static final String TAB_NAME = "tab_user";

    public static final String COL_USER_ID = "user_id";
    public static final String COL_NAME = "user_name";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_WORK = "work";
    public static final String COL_AGE = "age";
    public static final String COL_SEX = "sex";
    public static final String COL_LOCATION = "location";
    public static final String COL_AVATAR = "avatar";
    public static final String COL_PHONE = "phone";
    public static final String COL_VIP = "vip";
    public static final String COL_INTRO = "introduction";




    public static final String CREATE_TAB = "create table "
            + TAB_NAME + " ("
            + COL_USER_ID + " text primary key,"
            + COL_NAME + " text,"
            + COL_PASSWORD + " text,"
            + COL_EMAIL + " text,"
            + COL_WORK + " text,"
            + COL_AGE + " text,"
            + COL_SEX + " text,"
            + COL_LOCATION + " text,"
            + COL_AVATAR + " text,"
            + COL_PHONE + " text,"
            + COL_VIP + " text,"
            + COL_INTRO + " text);";



}
