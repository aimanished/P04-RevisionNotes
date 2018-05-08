package com.myapplicationdev.android.p04_revisionnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    //TODO Define the Database properties
    private static final String DATABASE_NAME = "ps4.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NOTE = "ps";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_Stars = "stars";
    private static final String COLUMN_Content = "content";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_NOTE
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_Content + " TEXT,"
                + COLUMN_Stars + " INTEGER )";
        db.execSQL(createTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    public void insertNote(String noteContent, int stars) {
        //TODO insert the data into the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_Content, noteContent);
        values.put(COLUMN_Stars, stars);
        db.insert(TABLE_NOTE, null, values);
        db.close();
    }

    public ArrayList<Note> getStarsColumn() {
        // Create an ArrayList that holds String objects
        ArrayList<Note> tasks = new ArrayList<Note>();
        // Select all the tasks' description
        String selectQuery = "SELECT " + COLUMN_Stars
                + " FROM " + TABLE_NOTE;

        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        // Run the SQL query and get back the Cursor object
        Cursor cursor = db.rawQuery(selectQuery, null);

        // moveToFirst() moves to first row
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row
            //  and returns true; moveToNext() returns false
            //  when no more next row to move to
            do {
                // Add the task content to the ArrayList object
                //  0 in getString(0) return the data in the first
                //  column in the Cursor object. getString(1)
                //  return second column data and so on.
                //  Use getInt(0) if data is an int
                tasks.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return tasks;
    }

    public ArrayList<Note> getAllNote(){
        ArrayList<Note> tasks = new ArrayList<Note>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_Stars + ", " +
                COLUMN_Content + " FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                int id= cursor.getInt(0);
                String description = cursor.getString(1);
                int stars = cursor.getInt(2);
                Note obj = new Note(id,description,stars);
                tasks.add(obj);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;

    }

    public void insertData(String description,int stars){
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_Content, description);
        // Store the column name as key and the date as value
        values.put(COLUMN_Stars, stars);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_NOTE, null, values);
        // Close the database connection
        db.close();
    }
}
