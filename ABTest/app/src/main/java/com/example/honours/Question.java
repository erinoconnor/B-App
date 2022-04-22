package com.example.honours;

import java.util.ArrayList;

public class Question {

    private final int QuestionID;
    private final String question;
    private final int answer;
    private final int wrongAns1;
    private final int wrongAns2;
    private final int wrongAns3;
    private final int category;


    public Question(int questID, String quest, int correct, int wrong1, int wrong2, int wrong3, int category) {

        this.QuestionID = questID;
        this.question = quest;
        this.answer = correct;
        this.wrongAns1 = wrong1;
        this.wrongAns2 = wrong2;
        this.wrongAns3 = wrong3;
        this.category = category;

    }


    public int getQuestionId() {
        return this.QuestionID;
    }


    public String getUserQuestion() {
        return this.question;
    }

    public int getCorrectAnswer() {
        return this.answer;
    }

    public int getWrongAnswer1() {
        return this.wrongAns1;
    }

    public int getWrongAnswer2() {
        return this.wrongAns2;
    }

    public int getWrongAnswer3() {
        return this.wrongAns3;
    }

    public int getCategory() {
        return this.category;
    }

    public int[] getAnswers(Question q) {
        int[] answers = {q.getCorrectAnswer(), q.getWrongAnswer1(), q.getWrongAnswer2(), q.getWrongAnswer3()};
        return answers;
    }


}