package com.quizapp.chetanya.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public final static int DB_VERSION = 1;
    public final static String DB_NAME = "Quiz App";
    public final static String TABLE_NAME = "QuesTable";
    public final static String KEY_QUES_ID = "QID";
    public final static String KEY_QUESTIONS = "Qusetions";
    public final static String KEY_ANSWER = "Answer";
    Cursor cursor;

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
                + " ( " + KEY_QUES_ID + " INTEGER(10) PRIMARY KEY,"
                + KEY_QUESTIONS + " TEXT, "
                + KEY_ANSWER + " TEXT " + " ) ";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public void addQuestion(QuestionInfo questionInfo){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_QUES_ID, questionInfo.getId());
        values.put(KEY_QUESTIONS, questionInfo.getQuestions());
        values.put(KEY_ANSWER, questionInfo.getAnswers());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<QuestionInfo> getAllQuestions(){
        ArrayList<QuestionInfo> questionInfoArrayList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor =db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                QuestionInfo questionInfo = new QuestionInfo(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2));

                questionInfoArrayList.add(questionInfo);
            }while (cursor.moveToNext());
        }

        return questionInfoArrayList;
    }

    public  void placeCursor(int position){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        cursor.moveToPosition(position);
    }

    public  QuestionInfo getNextQuestion(){
        cursor.moveToNext();
        QuestionInfo questionInfo = new QuestionInfo(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));
        return questionInfo;
    }

    public QuestionInfo getPrevousQuestion(){
        cursor.moveToPrevious();
        QuestionInfo questionInfo = new QuestionInfo(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));
        return questionInfo;
    }

    public void getAllAnswers(QuestionInfo questionInfo){

    }
}
