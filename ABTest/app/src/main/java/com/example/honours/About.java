package com.example.honours;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class About extends Activity {
    TextView totalcorrect, qA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        totalcorrect = findViewById(R.id.totalcorrect);
        qA = findViewById(R.id.qA);

        SharedPreferences sett = getApplicationContext().getSharedPreferences("questions", 0);
        int quests = sett.getInt("questions", 0);

        SharedPreferences corr = getApplicationContext().getSharedPreferences("correct", 0);
        int corrects = corr.getInt("correct", 0);

        totalcorrect.setText(String.valueOf(corrects));
        qA.setText(String.valueOf(quests));
    }

    //Switch to Main activity
    public void backToStart(View v) {
        Intent intent = new Intent(About.this, MainActivity.class);
        startActivity(intent);

    }
}
