package com.example.julia.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Julia on 26.09.2015.
 */
public class StatsDatabase {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_SUBJECT = "subject";
    public static final String KEY_SETS = "sets";
    public static final String KEY_RATING = "rating";

    public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_SUBJECT, KEY_SETS, KEY_RATING};
    public static final String DATABASE_NAME = "MyDb";
    public static final String DATABASE_TABLE = "mainTable";
    public static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE_SQL =
            "create table " + DATABASE_TABLE
                    + " (" + KEY_ROWID + " integer primary key autoincrement, "
                    + KEY_SUBJECT + " string not null, "
                    + KEY_SETS + " string not null, "
                    + KEY_RATING + " string not null"
                    + ");";

    private Context context;
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;


    public StatsDatabase(Context c) {
        this.context = c;
        myDBHelper = new DatabaseHelper(context);
    }


    public StatsDatabase open() {
        db = myDBHelper.getWritableDatabase();
        return this;
    }


    public void close() {
        myDBHelper.close();
    }


    //inserts a new data row into database
    public long insertRow(String subject, String sets, String rating)
    {
		ContentValues newValues = new ContentValues();
        newValues.put(KEY_SUBJECT, subject);
        newValues.put(KEY_SETS, sets);
        newValues.put(KEY_RATING, rating);
        return db.insert(DATABASE_TABLE, null, newValues);
    }

    public int deleteRow(int rowId)
    {
        String where = KEY_ROWID + "=" + rowId;
        return db.delete(DATABASE_TABLE, where, null);
    }

    public void deleteAll()
    {
        Cursor cursor = getData();
        long rowId = cursor.getColumnIndexOrThrow(KEY_ROWID);
        if (cursor.moveToFirst())
        {
            do {
                deleteRow(cursor.getInt((int) rowId));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    // get all data from database
    public Cursor getData()
    {
        String where = null;
        Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS,
                where, null, null, null, null, null);
        if (c != null)
        {
            c.moveToFirst();
        }
        return c;
    }



    // Insert new data in existing row
    public int updateRow(String subject, String set, String rating)
    {
        String where = KEY_SUBJECT + "=" + "'" + subject + "'" + "AND " + KEY_SETS + "=" + "'" + set + "'";
        ContentValues newValues = new ContentValues();
        newValues.put(KEY_SUBJECT, subject);
        newValues.put(KEY_SETS, set);
        newValues.put(KEY_RATING, rating);
        return db.update(DATABASE_TABLE, newValues, where, null);
    }

    public boolean rowExists(String subjects, String sets)
    {
        String where = KEY_SUBJECT + "=" + "'" + subjects + "'" + "AND " + KEY_SETS + "=" + "'" + sets + "'";
        long count = DatabaseUtils.queryNumEntries(db, DATABASE_TABLE, where);
        if(count > 0)
        {
            return true;
        }
        else
        {
             return false;
        }
    }


    private static class DatabaseHelper extends SQLiteOpenHelper
    {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DATABASE_CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


    }
}


