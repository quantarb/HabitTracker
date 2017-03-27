package com.example.johnnylee.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.johnnylee.habittracker.data.HabitContract;
import com.example.johnnylee.habittracker.data.HabitContract.HabitEntry;
import com.example.johnnylee.habittracker.data.HabitDbHelper;

import static com.example.johnnylee.habittracker.data.HabitDbHelper.LOG_TAG;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HabitDbHelper mDbHelper = new HabitDbHelper(this);
    }

    private void insertHabit(String name, Integer count){


        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(HabitEntry.COLUMN_HABIT_NAME, name);
        values.put(HabitEntry.COLUMN_HABIT_COUNT, count);


        long newRodId = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        Log.v(LOG_TAG, "New rod ID " + newRodId);
    }

    private Cursor displayHabit(){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_COUNT};

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order

        return cursor;
    }
}
