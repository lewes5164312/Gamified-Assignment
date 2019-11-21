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

    //get all
    @Query("SELECT * FROM note")
    List<Note> getAll();

    //set default text for note
    @Query("INSERT into note VALUES (1, 'Please replace this field with some notes \n " +
            "Maybe there is some key point you want to jot down? \n " +
            "Or want to make a note of something that you keep forgetting? \n " +
            "Press save when you are done! \n " +
            "Your work will still be here next time you open, thanks to the room library/sqlite database!')")
    void insertDefault();

    //set default highscore
    @Query("INSERT into note VALUES (2, '0')")
    void insertDefaultHighScore();

    //get text in note
    @Query("SELECT text FROM note WHERE id = 1")
    String getDBText();

    //get highscore
    @Query("SELECT text FROM note WHERE id = 2")
    String getDBHighscore();

    //update note text
    @Query("UPDATE note SET text = :updateText WHERE id = 1")
    void updateDB(String updateText);

    //update highscore
    @Query("UPDATE note SET text = :updateText WHERE id = 2")
    void updateDBHighscore(String updateText);
}
