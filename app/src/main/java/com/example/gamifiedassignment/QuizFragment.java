package com.example.gamifiedassignment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {



private Context context;
public QuizFragment(){
    //Required Empty Constructor
}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);


        //Open QuizActivity from the button
        Button ButtonStartQuiz = (Button) view.findViewById(R.id.button_start_quiz);
        ButtonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
        return view;
    }


    private void startQuiz() {
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        intent.putExtra("some", "Quiz Started");
        startActivity(intent);
    }

}
