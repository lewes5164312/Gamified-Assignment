package com.example.gamifiedassignment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public Button saveButton;
    public EditText notesText;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        final AppDatabase appDatabase = AppDatabase.getInstance(getContext());

        saveButton = view.findViewById(R.id.saveButton);
        notesText = view.findViewById(R.id.notesText);

        List<Note> dball = appDatabase.noteDao().getAll();

        if (dball.isEmpty())
        appDatabase.noteDao().insertDefault();

        notesText.setText(appDatabase.noteDao().getDBText());


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String notetext = "" + notesText.getText();
                appDatabase.noteDao().updateDB(notetext);
            }
        });

        return view;
    }

    }

