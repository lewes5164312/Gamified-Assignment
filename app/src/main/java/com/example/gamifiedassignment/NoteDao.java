package com.example.gamifiedassignment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.gamifiedassignment.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("INSERT into note VALUES (1, 'Please replace this field with some notes \n " +
            "Maybe there is some key point you want to jot down? \n " +
            "Or want to make a note of something that you keep forgetting? \n " +
            "Press save when you are done! \n " +
            "Your work will still be here next time you open, thanks to the room library/sqlite database!')")
    void insertDefault();

    @Query("SELECT text FROM note WHERE id = 1")
    String getDBText();

    @Query("UPDATE note SET text = :updateText WHERE id = 1")
    void updateDB(String updateText);
}
