package com.quizapp.chetanya.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class Canvas extends AppCompatActivity {


    private CanvasView customCanvas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.canvas_layout);


        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

    }


    public void clearCanvas(View v) {

        customCanvas.clearCanvas();

    }


}