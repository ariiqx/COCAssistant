package com.videdesk.mobile.cocassistant.models.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.videdesk.mobile.cocassistant.config.Database;
import com.videdesk.mobile.cocassistant.config.Value;
import com.videdesk.mobile.cocassistant.models.Person;

import java.util.ArrayList;
import java.util.List;

public class PeopleDA {

    private SQLiteDatabase db;

    public PeopleDA(){}

    public PeopleDA(Context context){
        Database database = new Database(context);
        this.db = database.open();
    }

    // Getting person Count
    public int count() {
        String sql = "SELECT  * FROM " + Value.TABLE_PEOPLE;
        Cursor cursor = db.rawQuery(sql, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    /*
     * Creating a person
     */
    public long add(Person person) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, person.getNode());
        values.put(Value.COLUMN_USER_NODE, person.getUser_node());
        values.put(Value.COLUMN_NATION_NODE, person.getNation_node());
        values.put(Value.COLUMN_REGION_NODE, person.getRegion_node());
        values.put(Value.COLUMN_LOCATION, person.getLocation());
        values.put(Value.COLUMN_CHURCH_NODE, person.getChurch_node());
        values.put(Value.COLUMN_GENDER, person.getGender());
        values.put(Value.COLUMN_CAREER, person.getCareer());

        // return inserted row id
        return db.insert(Value.TABLE_PEOPLE, null, values);
    }

    /*
     * check if person exists
     */
    public boolean exist(String person_node){
        boolean it_exist = false;
        String sql = "SELECT  * FROM " + Value.TABLE_PEOPLE + " WHERE " + Value.COLUMN_NODE + " = '" + person_node + "'";

        Cursor c = db.rawQuery(sql, null);
        int count = c.getCount();
        c.close();
        if(count > 0){
            it_exist = true;
        }
        return it_exist;
    }

    /*
     * get single person
     */
    public Person find(String person_node) {
        String sql = "SELECT  * FROM " + Value.TABLE_PEOPLE + " WHERE " + Value.COLUMN_NODE + " = '" + person_node + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        Person person = new Person();
        person.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
        person.setUser_node(c.getString(c.getColumnIndex(Value.COLUMN_USER_NODE)));
        person.setNation_node(c.getString(c.getColumnIndex(Value.COLUMN_NATION_NODE)));
        person.setRegion_node(c.getString(c.getColumnIndex(Value.COLUMN_REGION_NODE)));
        person.setLocation(c.getString(c.getColumnIndex(Value.COLUMN_LOCATION)));
        person.setChurch_node(c.getString(c.getColumnIndex(Value.COLUMN_CHURCH_NODE)));
        person.setGender(c.getString(c.getColumnIndex(Value.COLUMN_GENDER)));
        person.setCareer(c.getString(c.getColumnIndex(Value.COLUMN_CAREER)));

        c.close();

        return person;
    }

    /*
     * get single person
     */
    public Person get(String field, String value) {
        String sql = "SELECT  * FROM " + Value.TABLE_PEOPLE + " WHERE " + field + " = '" + value + "'";

        Cursor c = db.rawQuery(sql, null);

        if (c != null)
            c.moveToFirst();

        Person person = new Person();
        person.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
        person.setUser_node(c.getString(c.getColumnIndex(Value.COLUMN_USER_NODE)));
        person.setNation_node(c.getString(c.getColumnIndex(Value.COLUMN_NATION_NODE)));
        person.setRegion_node(c.getString(c.getColumnIndex(Value.COLUMN_REGION_NODE)));
        person.setLocation(c.getString(c.getColumnIndex(Value.COLUMN_LOCATION)));
        person.setChurch_node(c.getString(c.getColumnIndex(Value.COLUMN_CHURCH_NODE)));
        person.setGender(c.getString(c.getColumnIndex(Value.COLUMN_GENDER)));
        person.setCareer(c.getString(c.getColumnIndex(Value.COLUMN_CAREER)));

        c.close();

        return person;
    }

    /*
     * getting all people
     * */
    public List<Person> all() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT  * FROM " + Value.TABLE_PEOPLE;

        Cursor c = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Person person = new Person();
                person.setNode(c.getString(c.getColumnIndex(Value.COLUMN_NODE)));
                person.setUser_node(c.getString(c.getColumnIndex(Value.COLUMN_USER_NODE)));
                person.setNation_node(c.getString(c.getColumnIndex(Value.COLUMN_NATION_NODE)));
                person.setRegion_node(c.getString(c.getColumnIndex(Value.COLUMN_REGION_NODE)));
                person.setLocation(c.getString(c.getColumnIndex(Value.COLUMN_LOCATION)));
                person.setChurch_node(c.getString(c.getColumnIndex(Value.COLUMN_CHURCH_NODE)));
                person.setGender(c.getString(c.getColumnIndex(Value.COLUMN_GENDER)));
                person.setCareer(c.getString(c.getColumnIndex(Value.COLUMN_CAREER)));

                // adding to person list
                people.add(person);
            } while (c.moveToNext());
        }

        c.close();

        return people;
    }

    /*
     * Updating an person
     */
    public int update(Person person) {
        ContentValues values = new ContentValues();
        values.put(Value.COLUMN_NODE, person.getNode());
        values.put(Value.COLUMN_USER_NODE, person.getUser_node());
        values.put(Value.COLUMN_NATION_NODE, person.getNation_node());
        values.put(Value.COLUMN_REGION_NODE, person.getRegion_node());
        values.put(Value.COLUMN_LOCATION, person.getLocation());
        values.put(Value.COLUMN_CHURCH_NODE, person.getChurch_node());
        values.put(Value.COLUMN_GENDER, person.getGender());
        values.put(Value.COLUMN_CAREER, person.getCareer());

        // updating row
        return db.update(Value.TABLE_PEOPLE, values, Value.COLUMN_NODE + " = ?",
                new String[] { String.valueOf(person.getNode()) });
    }

    /*
     * Deleting an person
     */
    public void delete(String person_node) {
        db.delete(Value.TABLE_PEOPLE, Value.COLUMN_NODE + " = ?",
                new String[] { String.valueOf(person_node) });
    }

    /*
     * Refresh all local data
     */
    public void refresh(Person person){
        if(exist(person.getNode())){
            update(person);
        }else{
            add(person);
        }
    }
}
