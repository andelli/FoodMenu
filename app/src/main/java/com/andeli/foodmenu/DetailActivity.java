package com.andeli.foodmenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.andeli.foodmenu.model.Food;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private Food food;
    private Map<String, String[]> restaurantData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Menambahkan tombol "Back" di action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Detail Makanan");
        }

        // Mendapatkan data makanan dari Intent
        food = (Food) getIntent().getSerializableExtra("food");

        ImageView foodImage = findViewById(R.id.food_image);
        TextView foodName = findViewById(R.id.food_name);
        TextView foodDesc = findViewById(R.id.food_desc);
        TextView foodPrice = findViewById(R.id.food_price);
        TextView restaurantName = findViewById(R.id.restaurant_name);
        TextView restaurantAddress = findViewById(R.id.restaurant_address);
        Button btnGetDirection = findViewById(R.id.btn_get_direction);
        Button btnShare = findViewById(R.id.btn_share);

        // Menampilkan informasi makanan
        if (food != null) {
            foodImage.setImageResource(food.getImageResId());
            foodName.setText(food.getName());
            foodDesc.setText(food.getDescription());
            foodPrice.setText(food.getPrice());

            // Menampilkan restoran yang sesuai
            setRestaurantData();
            String[] restaurantInfo = restaurantData.get(food.getName());
            if (restaurantInfo != null) {
                restaurantName.setText(restaurantInfo[0]);
                restaurantAddress.setText(restaurantInfo[1]);

                // Menangani klik tombol "Get Direction"
                btnGetDirection.setOnClickListener(v -> openGoogleMaps(restaurantInfo[1]));
            }
        }

        // Menangani klik tombol "Share"
        btnShare.setOnClickListener(v -> shareFoodInfo());
    }

    // Inisialisasi data restoran
    private void setRestaurantData() {
        restaurantData = new HashMap<>();
        restaurantData.put("Pizza", new String[]{"Pizza Hut", "Jl. Thamrin No. 1, Jakarta"});
        restaurantData.put("Burger", new String[]{"Burger King", "Jl. Sudirman No. 10, Jakarta"});
        restaurantData.put("Sushi", new String[]{"Sushi Hiro", "Jl. Asia Afrika No. 8, Jakarta"});
    }

    // Fungsi untuk berbagi informasi makanan
    private void shareFoodInfo() {
        if (food != null) {
            String shareText = "Cek makanan ini: " + food.getName() +
                    "\nDeskripsi: " + food.getDescription() +
                    "\nHarga: " + food.getPrice();

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"));
        }
    }

    // Fungsi untuk membuka Google Maps
    @SuppressLint("QueryPermissionsNeeded")
    private void openGoogleMaps(String address) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + Uri.encode(address));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        // Pastikan Google Maps terpasang di perangkat
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
