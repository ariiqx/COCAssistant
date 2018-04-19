package com.videdesk.mobile.cocassistant.models.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.videdesk.mobile.cocassistant.config.Database;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.models.Book;
import com.videdesk.mobile.cocassistant.models.Topic;

import java.util.ArrayList;
import java.util.List;

public class TopicsDA {

    private SQLiteDatabase db;

    public TopicsDA(){}

    public TopicsDA(Context context){
        Database database = new Database(context);
        this.db = database.open();
    }

    // Getting Topic Count
    public int count() {
        String sql = "SELECT  * FROM " + Value.TABLE_TOPICS;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Creating a Topic
     */
    public long add(Topic topic) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, topic.getNode());
        values.put(Value.COLUMN_TITLE, topic.getTitle());

        // return inserted row id
        return db.insert(Value.TABLE_TOPICS, null, values);
    }


    /*
     * check if topic exists
     */
    public boolean exist(String node){
        boolean it_exist = false;
        String sql = "SELECT  * FROM " + Value.TABLE_TOPICS + " WHERE " + Value.COLUMN_NODE + " = '" + node + "'";

        Cursor c = db.rawQuery(sql, null);

        int count = c.getCount();

        c.close();

        if(count > 0){
            it_exist = true;
        }

        return it_exist;
    }

    /*
     * getting all Topics
     * */
    public List<Topic> all() {
        List<Topic> topics = new ArrayList<Topic>();
        String sql = "SELECT  * FROM " + Value.TABLE_TOPICS;

        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
                topic.setTitle(c.getString(c.getColumnIndex(Value.COLUMN_TITLE)));
                // adding to Topic list
                topics.add(topic);
            } while (c.moveToNext());
        }

        c.close();

        return topics;
    }

    /*
     * Updating a topic
     */
    public int update(Topic topic) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, topic.getNode());
        values.put(Value.COLUMN_TITLE, topic.getTitle());

        // updating row
        return db.update(Value.TABLE_TOPICS, values, Value.COLUMN_NODE + " = ?",
                new String[] { topic.getNode() });
    }

    /*
     * Deleting an Topic
     */
    public void delete(String node) {
        db.delete(Value.TABLE_TOPICS, Value.COLUMN_NODE + " = ?",
                new String[] { node });
    }

    /*
     * Refresh all local data
     */
    public void refresh(Topic topic){
        if(exist(topic.getNode())){
            update(topic);
        }else{
            add(topic);
        }
    }
}
