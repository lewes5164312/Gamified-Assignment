package com.example.gamifiedassignment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;


public class QuizFragment extends Fragment {

    private Context context;

    //Storing the highscore
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private int highscore;


    public QuizFragment(){
    //Required Empty Constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        textViewHighscore = view.findViewById(R.id.text_view_highscore);
        loadHighscore();

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
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == REQUEST_CODE_QUIZ){
            if (resultCode == RESULT_OK){
                //Store the score in a new integer
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0 );
                if (score > highscore){
                    updateHighscore(score);
                }
            }
        }
    }

    //Shows highscore
    private void loadHighscore() {
        SharedPreferences prefs = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("Highscore: " + highscore);
    }
    //Updates highscores
    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("Highscore: " + highscore);

        SharedPreferences prefs = this.getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }



}
