package com.quizapp.chetanya.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainQuizActivity extends AppCompatActivity {
    DBHandler dbHandler;

    TextView textView;
    TextView tvTimer;

    RadioGroup radioGroup;
    RadioButton rbOption1;
    RadioButton rbOption2;
    RadioButton rbOption3;
    RadioButton rbOption4;
    RadioButton rbTemp;
    RadioButton rbTemp2;

    FloatingActionButton fabBack;
    FloatingActionButton fabNext;
    FloatingActionButton fabSubmit;
    FloatingActionButton fabShare;
    FloatingActionButton fabCanvas;

    String questionArray[] = new String[10];
    String yourAnswerArray[] = new String[10];
    String correctArray[] = new String[10];

    long startTime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    int timer = 0;
    private static int count = 1;
    int a[] = new int[100];
    int counter = 0;
    int arrCount = 0;

    Intent intent;
    int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_quiz_layout);

        intent = getIntent();
        position = intent.getIntExtra("position", 0);

        setAllViews();
        addQuestions();
        placeCursor();
        setTimer();
        setButtonFunction();
    }

    @Override
    public void onBackPressed() {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText("Are You Sure You Want to Exit")
                .setContentText("Note: answers will not be recorded")
                .setConfirmText("Yes")
                .setCancelText("No")
                .showCancelButton(true)
                .show();
        sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
            }
        });
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                finish();
            }
        });
    }

    private void getAllAnswers() {
        ArrayList<QuestionInfo> correctArrayList = new ArrayList<>();
        correctArrayList = dbHandler.getAllQuestions();
        //Toast.makeText(this, "" + correctArrayList.size(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < correctArrayList.size(); i++) {
            if (position == 0 && i < 10) {
                correctArray[i] = correctArrayList.get(i).getAnswers();
                questionArray[i] = correctArrayList.get(i).getQuestions();
            } else if (position == 1 && i < 20 && i >= 10) {
                correctArray[i - 10] = correctArrayList.get(i).getAnswers();
                questionArray[i - 10] = correctArrayList.get(i).getQuestions();
            } else if (position == 2 && i < 30 && i >= 20) {
                correctArray[i - 20] = correctArrayList.get(i).getAnswers();
                questionArray[i - 20] = correctArrayList.get(i).getQuestions();
            } else if (position == 3 && i < 40 && i >= 30) {
                correctArray[i - 30] = correctArrayList.get(i).getAnswers();
                questionArray[i - 30] = correctArrayList.get(i).getQuestions();
            } else if (position == 4 && i < 50 && i >= 40) {
                correctArray[i - 40] = correctArrayList.get(i).getAnswers();
                questionArray[i - 40] = correctArrayList.get(i).getQuestions();
            }
        }
    }

    private void setButtonFunction() {
        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < 10 && counter > 0) {
                    if (counter > 0) {
                        count--;
                    }
                    if (counter <= 0) {
                        fabBack.setEnabled(false);
                    } else {
                        //fabBack.setEnabled(true);
                        getPreviousQuestion();
                    }
                    if (counter == 9) {
                        fabNext.setEnabled(true);
                    }
                    getPreviousCheckedButton();
                    getAndSetID();
                    counter--;

                    //questionArray[count] = textView.getText().toString();
                    if (radioGroup.getCheckedRadioButtonId() == -1)
                        yourAnswerArray[count] = "Not Attempted";
                    else if (count <= 9) {
                        Toast.makeText(MainQuizActivity.this, "Reached", Toast.LENGTH_SHORT).show();
                        rbTemp2 = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                        yourAnswerArray[count] = rbTemp2.getText().toString();
                    }
                } else {
                    //counter = 9;
                    //finish();
                }
            }
        });

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < 9) {
                    if (counter >= 10) {
                        fabNext.setEnabled(false);
                        getPreviousQuestion();
                    } else {
                        //fabBack.setEnabled(true);
                        getNextQuestion();
                    }
                    if (counter == 1) {
                        fabBack.setEnabled(true);
                    }
                    if (a[count] == -1) {
                        getAndSetID();
                    }
                    getAndSetID();
                    count++;
                    if (a[count] != -1) {
                        getPreviousCheckedButton();
                    } else {
                        resetRadioButtons();
                    }
                    counter++;
                    //if (count <= 9) {
                    // questionArray[count - 1] = textView.getText().toString();


                    if (count <= 9) {
                        if (radioGroup.getCheckedRadioButtonId() == -1)
                            yourAnswerArray[count] = "Not Attempted";
                        else {
                            rbTemp2 = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                            yourAnswerArray[count] = rbTemp2.getText().toString();
                        }
                    }
                } else {
                    //
                }
            }
        });


        //yourAnswerArray[count] = rbTemp2.getText().toString();

        fabSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MainQuizActivity.this, SweetAlertDialog.WARNING_TYPE);
                sweetAlertDialog.setTitleText("Are You Sure You Want to Submit")
                        .setConfirmText("Yes")
                        .setCancelText("No")
                        .showCancelButton(true)
                        .show();
                sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                });
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(MainQuizActivity.this, AfterSubmitActivity.class);
                        intent.putExtra("questionArray", questionArray);
                        intent.putExtra("yourAnswerArray", yourAnswerArray);
                        intent.putExtra("correctAnswerArray", correctArray);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        fabShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = textView.getText().toString() + '\n'
                        + "A) " + rbOption1.getText().toString() + '\n'
                        + "B) " + rbOption2.getText().toString() + '\n'
                        + "C) " + rbOption3.getText().toString() + '\n'
                        + "D) " + rbOption4.getText().toString();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Quzic Question");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share Via"));
            }
        });

        fabCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainQuizActivity.this, Canvas.class);
                startActivity(intent);
            }
        });
    }

    private void setTimer() {
        new CountDownTimer(100000, 1000) {
            @Override
            public void onTick(long timeUntilFinish) {
                //tvTimer.setText(String.valueOf(timer));
                timer++;
                int secs = (int) timer;
                int mins = secs / 60;
                tvTimer.setText("" + mins + ":"
                        + String.format("%02d", secs));
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainQuizActivity.this, "Time Up", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    private void setAllViews() {
        dbHandler = new DBHandler(this);
        textView = (TextView) findViewById(R.id.tvQuizQuestion);
        tvTimer = (TextView) findViewById(R.id.Timer);
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);
        rbOption1 = (RadioButton) findViewById(R.id.rbOption1);
        rbOption2 = (RadioButton) findViewById(R.id.rbOption2);
        rbOption3 = (RadioButton) findViewById(R.id.rbOption3);
        rbOption4 = (RadioButton) findViewById(R.id.rbOption4);
        fabBack = (FloatingActionButton) findViewById(R.id.fabBack);
        fabNext = (FloatingActionButton) findViewById(R.id.fabNext);
        fabSubmit = (FloatingActionButton) findViewById(R.id.fabSubmit);
        fabShare = (FloatingActionButton) findViewById(R.id.fabShare);
        fabCanvas = (FloatingActionButton) findViewById(R.id.fabCanvas);

        for (int i = 0; i < 100; i++) {
            a[i] = -1;
        }
        for (int i = 0; i < yourAnswerArray.length; i++){
            yourAnswerArray[i] = "Not Attempted";
        }
    }

    private void placeCursor() {
        if (position == 0) {
            dbHandler.placeCursor(0);
        } else if (position == 1) {
            dbHandler.placeCursor(10);
        } else if (position == 2) {
            dbHandler.placeCursor(20);
        } else if (position == 3) {
            dbHandler.placeCursor(30);
        } else if (position == 4) {
            dbHandler.placeCursor(40);
        }
    }

    private void getNextQuestion() {
        QuestionInfo questionInfo = dbHandler.getNextQuestion();
        textView.setText("Q." + questionInfo.getId() + ") " + questionInfo.getQuestions());
    }

    private void getPreviousQuestion() {
        QuestionInfo questionInfo = dbHandler.getPrevousQuestion();
        textView.setText("Q." + questionInfo.getId() + ") " + questionInfo.getQuestions());
    }

    private void addQuestions() {
        ArrayList<QuestionInfo> questionInfoArrayList = new ArrayList<>();
        AllQues allQues = new AllQues();
        allQues.getAllQuestions(questionInfoArrayList);
        for (int i = 0; i < questionInfoArrayList.size(); i++) {
            int count = questionInfoArrayList.get(i).getId() + i;
            dbHandler.addQuestion(new QuestionInfo(count,
                    questionInfoArrayList.get(i).getQuestions(),
                    questionInfoArrayList.get(i).getAnswers()));
        }

        getAllAnswers();
    }

    private void getPreviousCheckedButton() {
        if (a[count] != -1) {
            rbTemp = (RadioButton) findViewById(a[count]);
            //Toast.makeText(MainQuizActivity.this, a[count] + " " + count + " ", Toast.LENGTH_SHORT).show();
            rbTemp.setChecked(true);
        } else {
            //Toast.makeText(MainQuizActivity.this, a[count] + " " + count + " ", Toast.LENGTH_SHORT).show();
        }
    }

    private void setRecord(int selectedId) {
        a[count] = selectedId;
    }

    private void resetRadioButtons() {
        radioGroup.clearCheck();
    }

    private void getAndSetID() {
        int selecteID;
        if (radioGroup.getCheckedRadioButtonId() > 0) {
            selecteID = radioGroup.getCheckedRadioButtonId();
            setRecord(selecteID);
        }
    }
}

