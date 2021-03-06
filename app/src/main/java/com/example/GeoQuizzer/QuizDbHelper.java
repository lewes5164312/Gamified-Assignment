package com.example.GeoQuizzer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


//this references https://codinginflow.com/tutorials/android/quiz-app-with-sqlite/part-3-sqliteopenhelper
public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GeographyQuiz.db";
    private static final int DATABASE_VERSION = 1;

    //Holds reference to the database
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //Create initial database
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }


    //Fill the table with questions

    private void fillQuestionsTable() {
        Question q1 = new Question("What is the population of Australia?", "25,523,400", "4,942,420",
                "10,768,477", "11,420,163", 1);
        addQuestion(q1);
        Question q2 = new Question("What is the capital of New Zealand?", "Canberra", "Wellington",
                "Paris", "Rome",2);
        addQuestion(q2);
        Question q3 = new Question("What is the GDP of Luxembourg?", "324", "3,061",
                "71","199", 3);
        addQuestion(q3);
        Question q4 = new Question("What are the primary languages of Switzerland?", "German, French, Italian, Romansh",
                "Dutch, French, German","French","German", 1);
        addQuestion(q4);
        Question q5 = new Question("What is the capital of Belgium?", "Brussel Sprouts",
                "Brussels", "Bern","Berlin", 2);
        addQuestion(q5);
        Question q6 = new Question("What is the largest country in Oceania?", "New Zealand",
                "France", "Germany","Australia", 4);
        addQuestion(q6);
        Question q7 = new Question("What is the country that has the capital of Bern?", "Luxembourg",
                "Belgium", "Lady Gaga I was bern this way","Switzerland", 4);
        addQuestion(q7);
        Question q8 = new Question("Because of its remoteness, what was the last lands to be settled by humans?", "Australia",
                "New Zealand", "Wakanda","Belgium", 2);
        addQuestion(q8);
        Question q9 = new Question("What is population of France?", "67,022,000",
                "60,359,546", "10,768,477","25,523,400", 1);
        addQuestion(q9);
        Question q10 = new Question("What country is also know as the Hellenic Republic or the Hellas", "Hell",
                "Heaven", "Greece","Italy", 3);
        addQuestion(q10);
    }


    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }


    //Retrieving the questions

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }




}