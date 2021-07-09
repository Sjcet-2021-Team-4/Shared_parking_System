package com.aumento.sharedparking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aumento.sharedparking.R;
import com.aumento.sharedparking.modelclass.ReviewListModelClass;

import java.util.List;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder> {

    private List<ReviewListModelClass> reviewList;
    private Context mCtx;

    public ReviewListAdapter(List<ReviewListModelClass> reviewList, Context mCtx) {
        this.reviewList = reviewList;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_review_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final ReviewListModelClass lists = reviewList.get(position);

        holder.review.setText(lists.getReview());
        holder.rating.setRating(Float.valueOf(lists.getRating()));

    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RatingBar rating;
        private TextView review;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rating = (RatingBar) itemView.findViewById(R.id.rating);
            review = (TextView) itemView.findViewById(R.id.review);

        }
    }
}
