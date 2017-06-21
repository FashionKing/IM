package com.example.administrator.im.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.im.model.dao.ContactTable;
import com.example.administrator.im.model.dao.InviteTab;

/**
 * Created by Administrator on 2017/6/5.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name) {
        super(context, name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建联系人表
        db.execSQL(ContactTable.CREATE_TAB);
        //创建邀请信息表
        db.execSQL(InviteTab.CRAET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
