package com.example.today_menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryFoodAdapter extends RecyclerView.Adapter<HistoryFoodAdapter.ViewHolder> {

    List<FoodHistory> mFoodHistoryList;
    OnHistoryClickListener mOnHistoryClickListener;

    public HistoryFoodAdapter(List<FoodHistory> foodHistoryList) {
        this.mFoodHistoryList = foodHistoryList;
    }

    public void setOnHistoryClickListener(OnHistoryClickListener listener) {
        this.mOnHistoryClickListener = listener;
    }


    @NonNull
    @Override
    public HistoryFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryFoodAdapter.ViewHolder holder, int position) {
        FoodHistory foodHistory = mFoodHistoryList.get(position);
        holder.bind(foodHistory);
    }

    @Override
    public int getItemCount() {
        return mFoodHistoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView foodNmTv;
        TextView dateTv;
        ImageView deleteBtn;
        FoodHistory mFoodHistory;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNmTv = itemView.findViewById(R.id.foodname_tv);
            dateTv = itemView.findViewById(R.id.date_tv);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnHistoryClickListener.onClick(mFoodHistory);
                }
            });
        }

        public void bind(FoodHistory foodHistory) {
            foodNmTv.setText(foodHistory.getFoodName());
            dateTv.setText(foodHistory.getDate());
            this.mFoodHistory = foodHistory;
        }
    }

    interface OnHistoryClickListener {
        void onClick(FoodHistory foodHistory);
    }
}
