package com.example.today_menu.menuPick;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.today_menu.Food;
import com.example.today_menu.R;

import org.w3c.dom.Text;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    List<Food> mFoodList;


    public FoodAdapter(List<Food> foodList) {
        this.mFoodList=foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menuresult,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = mFoodList.get(position);
        holder.bind(food);
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView resultTv;
        TextView infoTv;
        Food mFood;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TextView resultTv = itemView.findViewById(R.id.result_tv);
            TextView infoTv = itemView.findViewById(R.id.info_tv);
        }

        public void bind(Food food) {
            resultTv.setText(food.getName());
            infoTv.setText("오늘은 맛있는 " + food.getName() + "을 드세요.");
            this.mFood = food;
        }

    }
}
