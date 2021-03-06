package com.example.GeoQuizzer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import static android.app.Activity.RESULT_OK;


public class QuizFragment extends Fragment {

    private Context context;

    //Storing the highscore
    private static final int REQUEST_CODE_QUIZ = 1;

    private TextView textViewHighscore;
    private TextView textViewProgress;
    private int highscore;
    private String highscoreString;
    private ProgressBar progressBar;
    final AppDatabase appDatabase = AppDatabase.getInstance(getContext());

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

        List<Note> dball = appDatabase.noteDao().getAll();
        //only insert the default note/highscore into table if there not existing in DB ie the first time it is run
        if (dball.isEmpty()) {
            appDatabase.noteDao().insertDefault();
            appDatabase.noteDao().insertDefaultHighScore();
        }

        //setting progress bar through highscore divided by number of questions, expressed as a percent
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);

        highscoreString = appDatabase.noteDao().getDBHighscore();

        QuizDbHelper dbHelper = new QuizDbHelper(getActivity());
        List<Question> questionList = dbHelper.getAllQuestions();
        int questionCountTotal = questionList.size();


        highscore = Integer.parseInt(highscoreString);

        int progressPercent = highscore*100/questionCountTotal;

        progressBar.setProgress(progressPercent);
        //setting progress textview
        textViewProgress = view.findViewById(R.id.text_view_progress);

        textViewProgress.setText("Progress: " + progressPercent + "% Complete");
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
                highscore = Integer.parseInt(highscoreString);
                if (score > highscore){
                    updateHighscore(score);
                }
            }
        }
    }

    //Shows highscore
    private void loadHighscore() {
highscoreString = appDatabase.noteDao().getDBHighscore();;
        textViewHighscore.setText("Highscore: " + highscoreString);
    }
    //Updates highscores
    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        highscoreString = String.valueOf(highscore);
        textViewHighscore.setText("Highscore: " + highscore);
        appDatabase.noteDao().updateDBHighscore(highscoreString);

        QuizDbHelper dbHelper = new QuizDbHelper(getActivity());
        List<Question> questionList = dbHelper.getAllQuestions();
        int questionCountTotal = questionList.size();

        int progressPercent = highscore*100/questionCountTotal;

        progressBar.setProgress(progressPercent);

        textViewProgress.setText("Progress: " + progressPercent + "% Complete");
    }

}
