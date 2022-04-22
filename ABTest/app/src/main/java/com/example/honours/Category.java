package com.example.honours;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class Category extends Activity {
    Button addition, multiplyButt;
    ImageButton returnHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

        multiplyButt = findViewById(R.id.multiplyButt);
        addition = findViewById(R.id.addition);
        returnHome = findViewById(R.id.backToStart);


        //Listener for selecting adding/subtracting game
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAddition();
            }
        });


    }

    //Switch to Main activity
    public void returntoMain(View v) {
        Intent intent = new Intent(Category.this, MainActivity.class);
        startActivity(intent);
    }

    //Play multiplication quiz and send the category type in intent
    public void playMultiplication(View v) {

        String multiply = "multiply";
        Intent intent = new Intent(Category.this, Quiz.class);
        intent.putExtra("category", multiply);
        startActivity(intent);
    }

    //Play addition quiz and send the category type in intent
    public void playAddition() {

        String addition = "addition";
        Intent intent = new Intent(Category.this, Quiz.class);
        intent.putExtra("category", addition);
        startActivity(intent);
    }
}