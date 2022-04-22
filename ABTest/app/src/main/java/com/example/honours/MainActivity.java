package com.example.honours;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends Activity {
    ImageButton startButton;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Question question;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.playButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        mHandler = new Handler();
        runProgress();


    }


    public void runProgress() {

        Thread athread = new Thread(new Runnable() {
            @Override
            public void run() {
                initialiseData();

                mHandler.post(new Runnable() {
                    @Override
                    public void run() {


                    }

                });

            }
        });
        athread.start();
    }

    public void initialiseData() {
        final String PREFS_NAME = "MyPrefsFile";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("my_first_time", true)) {
            //the app is being launched for first time, do something
            Database database = new Database(MainActivity.this);

            //Initialise questions to be added to database
            Question question1 = new Question(1, "200 + 100", 300, 200, 400, 350, 1);
            Question question2 = new Question(2, "400 + 56", 456, 454, 466, 450, 1);
            Question question3 = new Question(3, "632 - 34", 598, 567, 589, 601, 1);
            Question question4 = new Question(4, "242 - 64", 178, 198, 180, 202, 1);
            Question question5 = new Question(5, "30 + 240", 270, 260, 300, 255, 1);
            Question question6 = new Question(6, "60-40", 20, 10, 30, 25, 1);
            Question question7 = new Question(7, "119 + 118", 237, 240, 236, 232, 1);
            Question question8 = new Question(8, "32 + 64", 96, 100, 98, 90, 1);
            Question question9 = new Question(9, "16 + 220", 236, 240, 238, 232, 1);
            Question question10 = new Question(10, "110 - 40", 70, 80, 60, 90, 1);
            Question question11 = new Question(11, "20 + 200", 220, 240, 300, 250, 1);
            Question question12 = new Question(12, "369 - 18", 351, 350, 348, 360, 1);
            Question question13 = new Question(13, "80 x 2", 160, 150, 135, 190, 2);
            Question question14 = new Question(14, "60 x 5", 300, 350, 250, 325, 2);
            Question question15 = new Question(15, "25 x 10", 250, 225, 260, 200, 2);
            Question question16 = new Question(16, "30 x 7", 210, 200, 190, 220, 2);

            //Add questions to database
            database.addQuestion(question1);
            database.addQuestion(question2);
            database.addQuestion(question3);
            database.addQuestion(question4);
            database.addQuestion(question5);
            database.addQuestion(question6);
            database.addQuestion(question7);
            database.addQuestion(question8);
            database.addQuestion(question9);
            database.addQuestion(question10);
            database.addQuestion(question11);
            database.addQuestion(question12);
            database.addQuestion(question13);
            database.addQuestion(question14);
            database.addQuestion(question15);
            database.addQuestion(question16);

            // first time task


            //Create and store numberofquestions SharedPreference
            SharedPreferences qsett = getApplicationContext().getSharedPreferences("questions", 0);
            SharedPreferences.Editor qedd = qsett.edit();
            qedd.putInt("questions", 0);

            qedd.apply();

            //Create and store correct answers SharedPreference
            SharedPreferences corrsett = getApplicationContext().getSharedPreferences("correct", 0);
            SharedPreferences.Editor corredd = corrsett.edit();
            corredd.putInt("correct", 0);

            corredd.apply();


            // record the fact that the app has been started at least once
            settings.edit().putBoolean("my_first_time", false).commit();
        }
    }

    //Switch to category activity
    public void play() {
        Intent intent = new Intent(MainActivity.this, Category.class);


        startActivity(intent);
    }

    //Switch to about activity and display page
    public void openHelp(View v) {
        Intent intent = new Intent(MainActivity.this, About.class);

        startActivity(intent);
    }

}