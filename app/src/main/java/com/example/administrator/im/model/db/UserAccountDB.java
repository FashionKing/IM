package com.example.administrator.im.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.im.model.dao.UserAccountTable;

/**
 * Created by Administrator on 2017/6/4.
 */

public class UserAccountDB extends SQLiteOpenHelper {
    public UserAccountDB(Context context) {
        super(context, "account.db", null, 1);
    }
//数据库创建时调用
    @Override
    public void onCreate(SQLiteDatabase db) {

        //执行创建数据库表的语句
        db.execSQL(UserAccountTable.CREATE_TAB);

    }
//数据库更新时调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
