package com.quizapp.chetanya.quizapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AfterSubmitAdapter extends RecyclerView.Adapter<AfterSubmitAdapter.ViewHolder> {
    Context context;
    String questionArray[] = new String[10];
    String yourAnswerArray[] = new String[10];
    String correctArray[] = new String[10];

    public AfterSubmitAdapter(Context context, String[] questionArray, String[] yourAnswerArray, String[] correctArray) {
        this.context = context;
        this.questionArray = questionArray;
        this.yourAnswerArray = yourAnswerArray;
        this.correctArray = correctArray;
    }

    @Override
    public AfterSubmitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.aftersubmit_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(AfterSubmitAdapter.ViewHolder holder, int position) {
        holder.tvQuestion.setText(questionArray[position]);
        holder.tvYourAnswer.setText(yourAnswerArray[position]);
        holder.tvCorrectAnswer.setText(correctArray[position]);
    }

    @Override
    public int getItemCount() {
        return questionArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion;
        TextView tvYourAnswer;
        TextView tvCorrectAnswer;

        public ViewHolder(View itemView) {
            super(itemView);
            tvQuestion = (TextView) itemView.findViewById(R.id.tvQuestion);
            tvYourAnswer = (TextView) itemView.findViewById(R.id.tvYourAnswer);
            tvCorrectAnswer = (TextView) itemView.findViewById(R.id.tvCorrectAnswer);
        }
    }
}
