package com.videdesk.mobile.cocassistant.models.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.videdesk.mobile.cocassistant.config.Database;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.models.User;

import java.util.ArrayList;
import java.util.List;

public class UsersDA {

    private SQLiteDatabase db;

    public UsersDA(){}

    public UsersDA(Context context){
        Database database = new Database(context);
        this.db = database.open();
    }

    // Getting user Count
    public int count() {
        String sql = "SELECT  * FROM " + Value.TABLE_USERS;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Creating a user
     */
    public long add(User user) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, user.getNode());
        values.put(Value.COLUMN_ROLE, user.getRole());
        values.put(Value.COLUMN_NAME, user.getName());
        values.put(Value.COLUMN_EMAIL, user.getEmail());
        values.put(Value.COLUMN_PHONE, user.getPhone());
        values.put(Value.COLUMN_CODE, user.getCode());
        values.put(Value.COLUMN_PASS, user.getPass());
        values.put(Value.COLUMN_PHOTO, user.getPhoto());
        values.put(Value.COLUMN_CREATED, user.getCreated());
        values.put(Value.COLUMN_UPDATED, user.getUpdated());
        values.put(Value.COLUMN_STATUS, user.getStatus());

        // return inserted row id
        return db.insert(Value.TABLE_USERS, null, values);
    }

    /*
     * check if user exists
     */
    public boolean exist(String user_node){
        boolean it_exist = false;
        String sql = "SELECT  * FROM " + Value.TABLE_USERS + " WHERE " + Value.COLUMN_NODE + " = '" + user_node + "'";

        Cursor c = db.rawQuery(sql, null);
        int count = c.getCount();
        c.close();
        if(count > 0){
            it_exist = true;
        }
        return it_exist;
    }

    /*
     * get single user
     */
    public User find(String user_node) {
        String sql = "SELECT  * FROM " + Value.TABLE_USERS + " WHERE " + Value.COLUMN_NODE + " = '" + user_node + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        User user = new User();
        user.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
        user.setRole(c.getString(c.getColumnIndex(Value.COLUMN_ROLE)));
        user.setName(c.getString(c.getColumnIndex(Value.COLUMN_NAME)));
        user.setEmail(c.getString(c.getColumnIndex(Value.COLUMN_EMAIL)));
        user.setPhone(c.getString(c.getColumnIndex(Value.COLUMN_PHONE)));
        user.setPass(c.getString(c.getColumnIndex(Value.COLUMN_PASS)));
        user.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
        user.setPhoto(c.getString(c.getColumnIndex(Value.COLUMN_PHOTO)));
        user.setCreated(c.getString(c.getColumnIndex(Value.COLUMN_CREATED)));
        user.setUpdated(c.getString(c.getColumnIndex(Value.COLUMN_UPDATED)));
        user.setStatus(c.getString(c.getColumnIndex(Value.COLUMN_STATUS)));


        c.close();

        return user;
    }

    /*
     * get single user
     */
    public User get(String field, String value) {
        String sql = "SELECT  * FROM " + Value.TABLE_USERS + " WHERE " + field + " = '" + value + "'";

        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        User user = new User();
        user.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
        user.setRole(c.getString(c.getColumnIndex(Value.COLUMN_ROLE)));
        user.setName(c.getString(c.getColumnIndex(Value.COLUMN_NAME)));
        user.setEmail(c.getString(c.getColumnIndex(Value.COLUMN_EMAIL)));
        user.setPhone(c.getString(c.getColumnIndex(Value.COLUMN_PHONE)));
        user.setPass(c.getString(c.getColumnIndex(Value.COLUMN_PASS)));
        user.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
        user.setPhoto(c.getString(c.getColumnIndex(Value.COLUMN_PHOTO)));
        user.setCreated(c.getString(c.getColumnIndex(Value.COLUMN_CREATED)));
        user.setUpdated(c.getString(c.getColumnIndex(Value.COLUMN_UPDATED)));
        user.setStatus(c.getString(c.getColumnIndex(Value.COLUMN_STATUS)));


        c.close();

        return user;
    }

    /*
     * getting all users
     * */
    public List<User> all() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT  * FROM " + Value.TABLE_USERS;

        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                User user = new User();
                user.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
                user.setRole(c.getString(c.getColumnIndex(Value.COLUMN_ROLE)));
                user.setName(c.getString(c.getColumnIndex(Value.COLUMN_NAME)));
                user.setEmail(c.getString(c.getColumnIndex(Value.COLUMN_EMAIL)));
                user.setPhone(c.getString(c.getColumnIndex(Value.COLUMN_PHONE)));
                user.setPass(c.getString(c.getColumnIndex(Value.COLUMN_PASS)));
                user.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
                user.setPhoto(c.getString(c.getColumnIndex(Value.COLUMN_PHOTO)));
                user.setCreated(c.getString(c.getColumnIndex(Value.COLUMN_CREATED)));
                user.setUpdated(c.getString(c.getColumnIndex(Value.COLUMN_UPDATED)));
                user.setStatus(c.getString(c.getColumnIndex(Value.COLUMN_STATUS)));

                // adding to user list
                users.add(user);
            } while (c.moveToNext());
        }

        c.close();

        return users;
    }

    /*
     * Updating an user
     */
    public int update(User user) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, user.getNode());
        values.put(Value.COLUMN_ROLE, user.getRole());
        values.put(Value.COLUMN_NAME, user.getName());
        values.put(Value.COLUMN_EMAIL, user.getEmail());
        values.put(Value.COLUMN_PHONE, user.getPhone());
        values.put(Value.COLUMN_CODE, user.getCode());
        values.put(Value.COLUMN_PASS, user.getPass());
        values.put(Value.COLUMN_PHOTO, user.getPhoto());
        values.put(Value.COLUMN_CREATED, user.getCreated());
        values.put(Value.COLUMN_UPDATED, user.getUpdated());
        values.put(Value.COLUMN_STATUS, user.getStatus());

        // updating row
        return db.update(Value.TABLE_USERS, values, Value.COLUMN_NODE + " = ?",
                new String[] { String.valueOf(user.getNode()) });
    }

    /*
     * Deleting an user
     */
    public void delete(String user_node) {
        db.delete(Value.TABLE_USERS, Value.COLUMN_NODE + " = ?",
                new String[] { String.valueOf(user_node) });
    }

    /*
     * Refresh all local data
     */
    public void refresh(User user){
        if(exist(user.getNode())){
            update(user);
        }else{
            add(user);
        }
    }
}
