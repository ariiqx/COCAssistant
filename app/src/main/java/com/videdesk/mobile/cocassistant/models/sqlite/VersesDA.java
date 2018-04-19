package com.videdesk.mobile.cocassistant.models.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.videdesk.mobile.cocassistant.config.Database;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.models.Verse;

import java.util.ArrayList;
import java.util.List;

public class VersesDA {

    private SQLiteDatabase db;

    public VersesDA(){}

    public VersesDA(Context context){
        Database database = new Database(context);
        this.db = database.open();
    }

    // Getting verse Count
    public int count() {
        String sql = "SELECT  * FROM " + Value.TABLE_VERSES;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Creating a verse
     */
    public long add(Verse verse) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, verse.getNode());
        values.put(Value.COLUMN_CODE, verse.getCode());
        values.put(Value.COLUMN_BIBLE_NODE, verse.getBible_node());
        values.put(Value.COLUMN_BOOK_NODE, verse.getBook_node());
        values.put(Value.COLUMN_CHAPTER_NODE, verse.getChapter_node());
        values.put(Value.COLUMN_TITLE, verse.getTitle());
        values.put(Value.COLUMN_DETAILS, verse.getDetails());

        // return inserted row id
        return db.insert(Value.TABLE_VERSES, null, values);
    }

    /*
     * get single verse
     */
    public Verse find(String verse_node) {
        String sql = "SELECT  * FROM " + Value.TABLE_VERSES + " WHERE "
                + Value.COLUMN_NODE + " = " + verse_node;

        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        Verse verse = new Verse();
        verse.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
        verse.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
        verse.setBible_node(c.getString(c.getColumnIndex(Value.COLUMN_BIBLE_NODE)));
        verse.setBook_node(c.getString(c.getColumnIndex(Value.COLUMN_BOOK_NODE)));
        verse.setChapter_node(c.getString(c.getColumnIndex(Value.COLUMN_CHAPTER_NODE)));
        verse.setTitle(c.getString(c.getColumnIndex(Value.COLUMN_TITLE)));
        verse.setDetails(c.getString(c.getColumnIndex(Value.COLUMN_DETAILS)));

        return verse;
    }
    /*
     * get last verse
     */
    public Verse last() {
        String sql = "SELECT  * FROM " + Value.TABLE_VERSES + " ORDER BY " + Value.COLUMN_NODE + " DESC LIMIT 1";

        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        Verse verse = new Verse();
        verse.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
        verse.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
        verse.setBible_node(c.getString(c.getColumnIndex(Value.COLUMN_BIBLE_NODE)));
        verse.setBook_node(c.getString(c.getColumnIndex(Value.COLUMN_BOOK_NODE)));
        verse.setChapter_node(c.getString(c.getColumnIndex(Value.COLUMN_CHAPTER_NODE)));
        verse.setTitle(c.getString(c.getColumnIndex(Value.COLUMN_TITLE)));
        verse.setDetails(c.getString(c.getColumnIndex(Value.COLUMN_DETAILS)));

        c.close();

        return verse;
    }

    /*
     * check if album exists
     */
    public boolean exist(String verse_node){
        boolean it_exist = false;
        String sql = "SELECT  * FROM " + Value.TABLE_VERSES + " WHERE " + Value.COLUMN_NODE + " = '" + verse_node + "'";

        Cursor c = db.rawQuery(sql, null);

        int count = c.getCount();

        c.close();

        if(count > 0){
            it_exist = true;
        }

        return it_exist;
    }

    /*
     * getting all verses by verse
     * */
    public List<Verse> fetch(String bible_node, String book_node, String chap_node) {
        List<Verse> verses = new ArrayList<>();
        String sql = "SELECT  * FROM " + Value.TABLE_VERSES + " WHERE "
                + Value.COLUMN_BIBLE_NODE + " = '" + bible_node + "' AND "
                + Value.COLUMN_BOOK_NODE + " = '" + book_node + "' AND "
                + Value.COLUMN_CHAPTER_NODE + " = '" + chap_node + "' ORDER BY " + Value.COLUMN_CODE + " ASC";

        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Verse verse = new Verse();
                verse.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
                verse.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
                verse.setBible_node(c.getString(c.getColumnIndex(Value.COLUMN_BIBLE_NODE)));
                verse.setBook_node(c.getString(c.getColumnIndex(Value.COLUMN_BOOK_NODE)));
                verse.setChapter_node(c.getString(c.getColumnIndex(Value.COLUMN_CHAPTER_NODE)));
                verse.setTitle(c.getString(c.getColumnIndex(Value.COLUMN_TITLE)));
                verse.setDetails(c.getString(c.getColumnIndex(Value.COLUMN_DETAILS)));

                // adding to verse list
                verses.add(verse);
            } while (c.moveToNext());
        }

        c.close();

        return verses;
    }


    /*
     * getting all verses
     * */
    public List<Verse> all() {
        List<Verse> verses = new ArrayList<Verse>();
        String sql = "SELECT  * FROM " + Value.TABLE_VERSES;

        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Verse verse = new Verse();
                verse.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
                verse.setCode(c.getString(c.getColumnIndex(Value.COLUMN_CODE)));
                verse.setBible_node(c.getString(c.getColumnIndex(Value.COLUMN_BIBLE_NODE)));
                verse.setBook_node(c.getString(c.getColumnIndex(Value.COLUMN_BOOK_NODE)));
                verse.setChapter_node(c.getString(c.getColumnIndex(Value.COLUMN_CHAPTER_NODE)));
                verse.setTitle(c.getString(c.getColumnIndex(Value.COLUMN_TITLE)));
                verse.setDetails(c.getString(c.getColumnIndex(Value.COLUMN_DETAILS)));

                // adding to verse list
                verses.add(verse);
            } while (c.moveToNext());
        }

        c.close();

        return verses;
    }

    /*
     * Updating an verse
     */
    public int update(Verse verse) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_CODE, verse.getCode());
        values.put(Value.COLUMN_BIBLE_NODE, verse.getBible_node());
        values.put(Value.COLUMN_BOOK_NODE, verse.getBook_node());
        values.put(Value.COLUMN_CHAPTER_NODE, verse.getChapter_node());
        values.put(Value.COLUMN_TITLE, verse.getTitle());
        values.put(Value.COLUMN_DETAILS, verse.getDetails());

        // updating row
        return db.update(Value.TABLE_VERSES, values, Value.COLUMN_NODE + " = ?",
                new String[] { verse.getNode() });
    }

    /*
     * Deleting an verse
     */
    public void delete(String verse_node) {
        db.delete(Value.TABLE_VERSES, Value.COLUMN_NODE + " = ?",
                new String[] {verse_node });
    }

    /*
     * Refresh all local data
     */
    public void refresh(Verse verse){
        if(exist(verse.getNode())){
            update(verse);
        }else{
            add(verse);
        }
    }
}
