package com.quizapp.chetanya.quizapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class AfterSubmitActivity extends Activity{
    String questionArray[] =  new String[10];
    String yourAnswerArray[] = new String[10];
    String correctArray[]= new String[10];
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_layout);
        intent = getIntent();
        questionArray = intent.getStringArrayExtra("questionArray");
        yourAnswerArray = intent.getStringArrayExtra("yourAnswerArray");
        correctArray = intent.getStringArrayExtra("correctAnswerArray");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        AfterSubmitAdapter afterSubmitAdapter = new AfterSubmitAdapter(this, questionArray, yourAnswerArray, correctArray);
        recyclerView.setAdapter(afterSubmitAdapter);
    }
}