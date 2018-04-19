package com.videdesk.mobile.cocassistant.models.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.videdesk.mobile.cocassistant.config.Database;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.models.Nation;

import java.util.ArrayList;
import java.util.List;

public class NationsDA {

    private SQLiteDatabase db;

    public NationsDA(){}

    public NationsDA(Context context){
        Database database = new Database(context);
        this.db = database.open();
    }

    // Getting nation Count
    public int count() {
        String sql = "SELECT  * FROM " + Value.TABLE_NATIONS;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Creating a nation
     */
    public long add(Nation nation) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, nation.getNode());
        values.put(Value.COLUMN_CODE, nation.getCode());
        values.put(Value.COLUMN_TITLE, nation.getTitle());
        values.put(Value.COLUMN_DIAL, nation.getDial());

        // return inserted row id
        return db.insert(Value.TABLE_NATIONS, null, values);
    }

    /*
     * check if nation exists
     */
    public boolean exist(String nation_node){
        boolean it_exist = false;
        String sql = "SELECT  * FROM " + Value.TABLE_NATIONS + " WHERE " + Value.COLUMN_NODE + " = '" + nation_node + "'";

        Cursor c = db.rawQuery(sql, null);
        int count = c.getCount();
        c.close();
        if(count > 0){
            it_exist = true;
        }
        return it_exist;
    }

    /*
     * get single nation
     */
    public Nation find(String nation_node) {
        String sql = "SELECT  * FROM " + Value.TABLE_NATIONS + " WHERE " + Value.COLUMN_NODE + " = '" + nation_node + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        Nation nation = new Nation();
        nation.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
        nation.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
        nation.setTitle(c.getString(c.getColumnIndex(Value.COLUMN_TITLE)));
        nation.setDial(c.getString(c.getColumnIndex(Value.COLUMN_DIAL)));


        c.close();

        return nation;
    }

    /*
     * get single nation
     */
    public Nation get(String field, String value) {
        String sql = "SELECT  * FROM " + Value.TABLE_NATIONS + " WHERE " + field + " = '" + value + "'";

        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        Nation nation = new Nation();
        nation.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
        nation.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
        nation.setTitle(c.getString(c.getColumnIndex(Value.COLUMN_TITLE)));
        nation.setDial(c.getString(c.getColumnIndex(Value.COLUMN_DIAL)));


        c.close();

        return nation;
    }

    /*
     * getting all nations
     * */
    public List<Nation> all() {
        List<Nation> nations = new ArrayList<>();
        String sql = "SELECT  * FROM " + Value.TABLE_NATIONS;

        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Nation nation = new Nation();
                nation.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
                nation.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
                nation.setTitle(c.getString(c.getColumnIndex(Value.COLUMN_TITLE)));
                nation.setDial(c.getString(c.getColumnIndex(Value.COLUMN_DIAL)));

                // adding to nation list
                nations.add(nation);
            } while (c.moveToNext());
        }

        c.close();

        return nations;
    }

    /*
     * Updating an nation
     */
    public int update(Nation nation) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, nation.getNode());
        values.put(Value.COLUMN_CODE, nation.getCode());
        values.put(Value.COLUMN_TITLE, nation.getTitle());
        values.put(Value.COLUMN_DIAL, nation.getDial());

        // updating row
        return db.update(Value.TABLE_NATIONS, values, Value.COLUMN_NODE + " = ?",
                new String[] { String.valueOf(nation.getNode()) });
    }

    /*
     * Deleting an nation
     */
    public void delete(String nation_node) {
        db.delete(Value.TABLE_NATIONS, Value.COLUMN_NODE + " = ?",
                new String[] { String.valueOf(nation_node) });
    }

    /*
     * Refresh all local data
     */
    public void refresh(Nation nation){
        if(exist(nation.getNode())){
            update(nation);
        }else{
            add(nation);
        }
    }
}
