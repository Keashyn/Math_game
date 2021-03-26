package com.example.math_game;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SimpleDatabase extends SQLiteOpenHelper {
    private final static int VERSION = 1;

    private static final String DB_NAME = "Leaderboards";
    private static final String TABLE_NAME = "ScoreList";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SCORE ="score";
    private static final String COLUMN_NAME = "name";


    SimpleDatabase(Context context) {
        super(context, DB_NAME , null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " +TABLE_NAME
                +"(" +
                COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SCORE+ " VARCHAR, "+
                COLUMN_NAME+" TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS ScoreList";
        db.execSQL(sql);
        onCreate(db);
    }

    boolean addScore(String name, int score){
        //Adding a score into the database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_SCORE,score);
        contentValues.put(COLUMN_NAME,name);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    Cursor getPerson(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM ScoreList WHERE id="+id+";";
        return db.rawQuery(sql, null);
    }

    int getScoreCount() {
        //Get the total number of score in the database
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    ArrayList<Integer> getAllScores() {
        //Getting all the score
        ArrayList<Integer> scoreList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                scoreList.add(cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return scoreList;
    }
    ArrayList<String> getNameasWell() {
        //Getting all the score
        ArrayList<String> Name = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+COLUMN_ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Name.add(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return Name;
    }


}