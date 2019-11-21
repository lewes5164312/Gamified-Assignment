package com.example.GeoQuizzer;

import androidx.room.PrimaryKey;
import androidx.room.Entity;

@Entity
public class Note {

    @PrimaryKey
    private Integer id;
    private String text;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Note(Integer id, String text) {
        this.id = id;
        this.text = text;
    }


}
