package com.example.GeoQuizzer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


//based on INFS3634 Week 8 class
@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {

        if(instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "noteDb")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}