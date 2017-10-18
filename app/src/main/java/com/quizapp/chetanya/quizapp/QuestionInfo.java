package com.quizapp.chetanya.quizapp;

public class QuestionInfo {
    private String questions;
    private String answers;
    private int id;

    QuestionInfo(int id, String questions, String answers) {
        this.questions = questions;
        this.answers = answers;
        this.id = id;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
