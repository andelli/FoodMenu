package com.andeli.foodmenu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andeli.foodmenu.R;
import com.andeli.foodmenu.model.Food;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Food> foodList;

    public FoodAdapter(Context context, ArrayList<Food> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);
        }

        Food food = foodList.get(position);

        ImageView foodImage = convertView.findViewById(R.id.food_image);
        TextView foodName = convertView.findViewById(R.id.food_name);
        TextView foodDesc = convertView.findViewById(R.id.food_desc);

        foodImage.setImageResource(food.getImageResId());
        foodName.setText(food.getName());
        foodDesc.setText(food.getDescription());

        return convertView;
    }
}
