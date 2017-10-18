package com.quizapp.chetanya.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {
    ArrayList<String> categoryNameArrayList = new ArrayList<>();
    Intent intent;
    int score = 0;
    int position;
    int scoreArr[] = new int[10];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_layout);

        /*intent = getIntent();
        score = intent.getIntExtra("score", 0);
        position = intent.getIntExtra("position", 0);*/
        addNames();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        /*for (int i = 0; i < scoreArr.length; i++)
            scoreArr[i] = 0;*/

        scoreArr[position] = score;
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, categoryNameArrayList, score, position, scoreArr);
        recyclerView.setAdapter(categoryAdapter);
    }

    public void addNames() {
        categoryNameArrayList.add("Music Artists");
        categoryNameArrayList.add("Instruments");
        categoryNameArrayList.add("Guess the Lyrics");
        categoryNameArrayList.add("Styles and Forms");
        categoryNameArrayList.add("Crazy Metals and Heavy metals");
    }
}
