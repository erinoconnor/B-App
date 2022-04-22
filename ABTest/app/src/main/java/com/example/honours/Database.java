package com.example.honours;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String COLUMN_QUESTION = "QUESTION";
    public static final String QUESTIONS_TABLE = "QUESTIONS_TABLE";
    public static final String COLUMN_ANSWER = "ANSWER";
    public static final String COLUMN_WRONGANS_1 = "WRONGANS1";
    public static final String COLUMN_WRONGANS_2 = "WRONGANS2";
    public static final String COLUMN_WRONGANS_3 = "WRONGANS3";
    public static final String COLUMN_CATEGORY = "CATEGORY";


    public Database(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    //Called first time database accessed. Code in here to create database
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + QUESTIONS_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_QUESTION + " TEXT, " + COLUMN_ANSWER + " INT, " + COLUMN_WRONGANS_1 + " INT, " + COLUMN_WRONGANS_2 + " INT, " + COLUMN_WRONGANS_3 + " INT,  " + COLUMN_CATEGORY + " INTEGER)";
        db.execSQL(createTableStatement);

    }

    //called if database version number changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //Add question to database
    public boolean addQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUESTION, question.getUserQuestion());
        cv.put(COLUMN_ANSWER, question.getCorrectAnswer());
        cv.put(COLUMN_WRONGANS_1, question.getWrongAnswer1());
        cv.put(COLUMN_WRONGANS_2, question.getWrongAnswer2());
        cv.put(COLUMN_WRONGANS_3, question.getWrongAnswer3());
        cv.put(COLUMN_CATEGORY, question.getCategory());

        long insert = db.insert(QUESTIONS_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            db.close();
            return true;

        }

    }

    //Get questions from database
    public ArrayList<Question> getaddQuestions(int query) {

        ArrayList<Question> questions = new ArrayList<>();
        String queryString = "SELECT * FROM " + QUESTIONS_TABLE + " WHERE " + COLUMN_CATEGORY + " = '" + query + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            //loop through result set and create new question objects and insert into arraylist
            do {
                int questionID = cursor.getInt(0);
                String quest = cursor.getString(1);
                int answer = cursor.getInt(2);
                int wrongans1 = cursor.getInt(3);
                int wrongans2 = cursor.getInt(4);
                int wrongans3 = cursor.getInt(5);
                int category = cursor.getInt(6);

                Question question = new Question(questionID, quest, answer, wrongans1, wrongans2, wrongans3, category);
                questions.add(question);
            } while (cursor.moveToNext());
        } else {
            //failed, dont add anything to list
        }
        //close cursor and db
        cursor.close();
        db.close();
        return questions;
    }


}

