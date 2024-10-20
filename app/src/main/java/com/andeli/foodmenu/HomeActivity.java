package com.andeli.foodmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.andeli.foodmenu.adapter.FoodAdapter;
import com.andeli.foodmenu.model.Food;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        foodList = new ArrayList<>();
        // Tambahkan data makanan ke dalam list
        foodList.add(new Food("Pizza", "Makanan khas Italia", "Estimasi Harga Terendah Rp 120.000", R.drawable.pizza));
        foodList.add(new Food("Burger", "Makanan cepat saji", "Estimasi Harga Terendah Rp 45.000", R.drawable.burger));
        foodList.add(new Food("Sushi", "Makanan Jepang", "Estimasi Harga Terendah Rp 55.000", R.drawable.sushi));

        ListView listView = findViewById(R.id.list_view);
        FoodAdapter adapter = new FoodAdapter(this, foodList);
        listView.setAdapter(adapter);

        // Klik item untuk melihat detail
        listView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
            intent.putExtra("food", foodList.get(position));
            startActivity(intent);
        });
    }
}
