package com.quizapp.chetanya.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<String> categoryNameList;
    int score;
    int position1;
    int scoreArr[];

    public CategoryAdapter(Context context, ArrayList<String> categoryNameList, int score, int position1, int[] scoreArr) {
        this.context = context;
        this.categoryNameList = categoryNameList;
        this.score = score;
        this.position1 = position1;
        this.scoreArr = scoreArr;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvCategory.setText(categoryNameList.get(position));
        //holder.tvScore.setText(scoreArr[position] + "");
    }

    @Override
    public int getItemCount() {
        return categoryNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        TextView tvScore;
        ImageView imCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCategory = (TextView) itemView.findViewById(R.id.tvcategory);
            tvScore = (TextView) itemView.findViewById(R.id.tvScore);
            imCategory = (ImageView) itemView.findViewById(R.id.imCategory);

            tvCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "position " + getAdapterPosition(), Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent(context, MainQuizActivity.class);
                    intent.putExtra("position", getAdapterPosition());
                    context.startActivity(intent);
                }
            });

            imCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "position " + getAdapterPosition(), Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent(context, MainQuizActivity.class);
                    intent.putExtra("position", getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }

    }
}
