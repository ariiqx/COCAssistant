package com.videdesk.mobile.cocassistant.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, Value.DATABASE_NAME, null, Value.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(Value.SQL_PERSON);
        db.execSQL(Value.SQL_LIKE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + Value.TABLE_PEOPLE);
        db.execSQL("DROP TABLE IF EXISTS " + Value.TABLE_LIKES);

        // create new tables
        onCreate(db);
    }

    public SQLiteDatabase open(){
        return this.getWritableDatabase();
    }

    public void close(){
        SQLiteDatabase db = open();
        if (db != null && db.isOpen())
            db.close();
    }
}
