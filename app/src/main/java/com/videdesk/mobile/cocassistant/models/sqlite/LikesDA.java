package com.videdesk.mobile.cocassistant.models.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.videdesk.mobile.cocassistant.config.Database;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.models.Like;

import java.util.ArrayList;
import java.util.List;

public class LikesDA {

    private SQLiteDatabase db;

    public LikesDA(){}

    public LikesDA(Context context){
        Database database = new Database(context);
        this.db = database.open();
    }

    // Getting Like Count
    public int count() {
        String sql = "SELECT  * FROM " + Value.TABLE_LIKES;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Creating a Like
     */
    public long add(Like like) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, like.getNode());
        values.put(Value.COLUMN_THEME_NODE, like.getTheme_node());
        values.put(Value.COLUMN_ITEM_NODE, like.getItem_node());

        // return inserted row id
        return db.insert(Value.TABLE_LIKES, null, values);
    }


    /*
     * check if like exists
     */
    public boolean exist(String node){
        boolean it_exist = false;
        String sql = "SELECT  * FROM " + Value.TABLE_LIKES + " WHERE " + Value.COLUMN_NODE + " = '" + node + "'";

        Cursor c = db.rawQuery(sql, null);

        int count = c.getCount();

        c.close();

        if(count > 0){
            it_exist = true;
        }

        return it_exist;
    }

    /*
     * getting all Likes
     * */
    public List<Like> all() {
        List<Like> likes = new ArrayList<Like>();
        String sql = "SELECT  * FROM " + Value.TABLE_LIKES;

        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Like like = new Like();
                like.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
                like.setTheme_node(c.getString(c.getColumnIndex(Value.COLUMN_THEME_NODE)));
                like.setItem_node(c.getString(c.getColumnIndex(Value.COLUMN_ITEM_NODE)));
                // adding to Like list
                likes.add(like);
            } while (c.moveToNext());
        }

        c.close();

        return likes;
    }

    /*
     * Deleting an Like
     */
    public void delete(String node) {
        db.delete(Value.TABLE_LIKES, Value.COLUMN_NODE + " = ?",
                new String[] { node });
    }

    /*
     * Refresh all local data
     */
    public void refresh(Like like){
        if(exist(like.getNode())){
            delete(like.getNode());
        }else{
            add(like);
        }
    }
}
