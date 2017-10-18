package com.quizapp.chetanya.quizapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yayandroid.parallaxrecyclerview.ParallaxImageView;
import com.yayandroid.parallaxrecyclerview.ParallaxViewHolder;
import java.util.ArrayList;

public class AppFeatureAdapter extends RecyclerView.Adapter<AppFeatureAdapter.ViewHolder> {
    Context context;
    ArrayList<Integer> imageArrayList = new ArrayList<>();
    ArrayList<String> nameArrayList = new ArrayList<>();
    OnItemClickListener mItemClickListener;

    public AppFeatureAdapter(Context context, ArrayList<Integer> imageArrayList, ArrayList<String> nameArrayList) {
        this.context = context;
        this.imageArrayList = imageArrayList;
        this.nameArrayList = nameArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_features_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.parallaxImageView.setImageResource(imageArrayList.get(position));
        holder.tvName.setText(nameArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return nameArrayList.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public class ViewHolder extends ParallaxViewHolder implements View.OnClickListener {
        ParallaxImageView parallaxImageView;
        TextView tvName;

        public ViewHolder(View view) {
            super(view);
            parallaxImageView = (ParallaxImageView) view.findViewById(R.id.pviId);
            tvName = (TextView) view.findViewById(R.id.tvName);
            view.setOnClickListener(this);
        }

        @Override
        public int getParallaxImageId() {
            return R.id.pviId;
        }

        public TextView getTextView() {
            return tvName;
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(view, getPosition());
            }
        }
    }
}
