package com.example.today_menu;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FoodAdapter extends AppCompatActivity {
    List<Food> mFoodList;

    public FoodAdapter(List<Food> foodList) {
        this.mFoodList = foodList;
    }


}
