package com.example.honours;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class Quiz extends Activity {
    Button choice1, choice2, choice3, choice4, ok, redo, goBack;

    int totalquestions;
    int totalcorrect;
    ProgressBar progressBar;
    int currentQuestion = 0;
    int currentposition = 0;
    TextView questionDisplay, noOfQ;
    int correct = 0;

    ArrayList<Question> questions = new ArrayList<>();

    ArrayList<Question> allquestions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        questionDisplay = findViewById(R.id.questionDisplay);
        choice1 = findViewById(R.id.choice1);
        choice2 = findViewById(R.id.choice2);
        choice3 = findViewById(R.id.choice3);
        choice4 = findViewById(R.id.choice4);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        noOfQ = findViewById(R.id.noOfQ);

        //Get selected category from category page in a bundle
        Bundle bundle = getIntent().getExtras();
        String category = bundle.getString("category");

        //Open database to access questions
        Database database = new Database(Quiz.this);

        //Get questions from database depending on category
        if (category.equals("addition")) {
            allquestions = database.getaddQuestions(1);
        } else if (category.equals("multiply")) {
            allquestions = database.getaddQuestions(2);
        }

        //Randomise questions
        for (int i = 0; i < allquestions.size(); i++) {
            int x = getRandom(allquestions.size());
            if (questions.contains(allquestions.get(x))) {
                i = i - 1;
            } else {
                questions.add(allquestions.get(x));
            }
        }

        noOfQ.setText(String.valueOf("Q" + currentposition));


        SharedPreferences sett = getApplicationContext().getSharedPreferences("questions", 0);
        totalquestions = sett.getInt("questions", 0);

        SharedPreferences corr = getApplicationContext().getSharedPreferences("correct", 0);
        totalcorrect = corr.getInt("correct", 0);

        //Start questions
        nextQuestion();

    }

    //Code for buttons and determining if user selected the correct answer
    public void answer(View v) {

        if (v.getId() == R.id.choice1) {

            int chose1 = Integer.parseInt(choice1.getText().toString());

            if (chose1 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {
                correct++;
                totalcorrect++;

            } else {
                String wrong = choice1.getText().toString();
                String right = Integer.toString(questions.get(currentQuestion - 1).getCorrectAnswer());
                showFeedback(wrong, right, questions.get(currentQuestion - 1).getUserQuestion());

            }
            nextQuestion();
        } else if (v.getId() == R.id.choice2) {

            int chose2 = Integer.parseInt(choice2.getText().toString());

            if (chose2 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {
                correct++;
                totalcorrect++;

            } else {
                String wrong = choice2.getText().toString();
                String right = Integer.toString(questions.get(currentQuestion - 1).getCorrectAnswer());
                showFeedback(wrong, right, questions.get(currentQuestion - 1).getUserQuestion());
            }
            nextQuestion();
        } else if (v.getId() == R.id.choice3) {

            int chose3 = Integer.parseInt(choice3.getText().toString());

            if (chose3 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {
                correct++;
                totalcorrect++;

            } else {
                String wrong = choice3.getText().toString();
                String right = Integer.toString(questions.get(currentQuestion - 1).getCorrectAnswer());
                showFeedback(wrong, right, questions.get(currentQuestion - 1).getUserQuestion());
            }
            nextQuestion();
        } else if (v.getId() == R.id.choice4) {

            int chose4 = Integer.parseInt(choice4.getText().toString());

            if (chose4 == (questions.get(currentQuestion - 1).getCorrectAnswer())) {
                correct++;
                totalcorrect++;

            } else {
                String wrong = choice4.getText().toString();
                String right = Integer.toString(questions.get(currentQuestion - 1).getCorrectAnswer());
                showFeedback(wrong, right, questions.get(currentQuestion - 1).getUserQuestion());
            }
            nextQuestion();

        }

    }

    //Function to move to next question
    private void nextQuestion() {
        totalquestions++;

        SharedPreferences qsett = getApplicationContext().getSharedPreferences("questions", 0);
        SharedPreferences.Editor qedd = qsett.edit();
        qedd.putInt("questions", (totalquestions - 1));
        qedd.apply();

        SharedPreferences corrsett = getApplicationContext().getSharedPreferences("correct", 0);
        SharedPreferences.Editor corredd = corrsett.edit();
        corredd.putInt("correct", totalcorrect);
        corredd.apply();

        currentposition++;

        noOfQ.setText(String.valueOf("Q" + currentposition));
        progressBar.setProgress(correct);


        //Randomising answers before displaying them in buttons if the current Q
        //is less than the number of questions and the number of Q correct is less than 10
        if (currentQuestion < questions.size() && correct < 10) {
            Question q = questions.get(currentQuestion);
            questionDisplay.setText(q.getUserQuestion());
            int[] answers = q.getAnswers(q);
            ArrayList num = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int x = getRandom(4);
                if (num.contains(x)) {
                    i = i - 1;
                } else {
                    num.add(x);
                }
            }
            //Setting the answers to buttons
            choice1.setText(Integer.toString(answers[(int) num.get(0)]));
            choice2.setText(Integer.toString(answers[(int) num.get(1)]));
            choice3.setText(Integer.toString(answers[(int) num.get(2)]));
            choice4.setText(Integer.toString(answers[(int) num.get(3)]));
            currentQuestion++;

            //If at the last question, clear the questions list and add the questions again randomised
        } else if (currentQuestion == questions.size() && correct < 10) {
            questions.clear();
            for (int i = 0; i < allquestions.size(); i++) {
                int x = getRandom(allquestions.size());
                if (questions.contains(allquestions.get(x))) {
                    i = i - 1;
                } else {
                    questions.add(allquestions.get(x));
                }
            }
            currentQuestion = 0;
            nextQuestion();

            //If correct is equal to 10, display the alert dialog and set coins to plus 10
        } else if (correct == 10) {
            SharedPreferences settings = getApplicationContext().getSharedPreferences("coins", 0);
            int coins = settings.getInt("coins", 0);
            int newcoins = coins + 10;

            SharedPreferences coinssett = getApplicationContext().getSharedPreferences("coins", 0);
            SharedPreferences.Editor coined = coinssett.edit();
            coined.putInt("coins", (newcoins));
            coined.apply();


            AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
            View view = getLayoutInflater().inflate(R.layout.finishedalert, null);
            builder.setView(view);
            AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            redo = view.findViewById(R.id.redo);
            goBack = view.findViewById(R.id.goBack);
            redo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();

                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }
            });
            goBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    backHome(v);

                }
            });

        }
    }

    //Random function
    private static int getRandom(int max) {
        int rnd = (int) (Math.random() * max);
        return rnd;
    }

    //Show feedback dialog for wrong question
    public void showFeedback(String wrong, String right, String questAsked) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Quiz.this);
        View view = getLayoutInflater().inflate(R.layout.feedbackalert, null);
        ok = view.findViewById(R.id.ok);


        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    //Go back to category page
    public void backHome(View v) {
        Intent intent = new Intent(Quiz.this, Category.class);
        startActivity(intent);
    }
}