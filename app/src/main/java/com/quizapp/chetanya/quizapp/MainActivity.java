package com.quizapp.chetanya.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.yayandroid.parallaxrecyclerview.ParallaxRecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ParallaxRecyclerView parallaxRecyclerView;
    ArrayList<Integer> imageArrayList = new ArrayList<>();
    ArrayList<String> nameArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_features_layout);

        addAdapterImages();
        setRecyclerView();

        AppFeatureAdapter appFeatureAdapter = new AppFeatureAdapter(this, imageArrayList, nameArrayList);
        parallaxRecyclerView.setAdapter(appFeatureAdapter);
        appFeatureAdapter.setOnItemClickListener(new AppFeatureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                } else if (position == 2) {
                    finish();
                }
            }
        });
    }

    private void setRecyclerView() {
        parallaxRecyclerView = (ParallaxRecyclerView) findViewById(R.id.prv);
        parallaxRecyclerView.setHasFixedSize(true);
        parallaxRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addAdapterImages() {
        imageArrayList.add(R.drawable.quiz2);
        imageArrayList.add(R.drawable.aboutus);
        imageArrayList.add(R.drawable.exit);
        nameArrayList.add("Quiz");
        nameArrayList.add("About Us");
        nameArrayList.add("Exit");
    }
}
