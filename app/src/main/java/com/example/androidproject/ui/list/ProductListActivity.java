package com.example.androidproject.ui.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidproject.AddActivity;
import com.example.androidproject.R;
import com.example.androidproject.ui.megaimage.CustomAdapterMegaImage;
import com.example.androidproject.ui.megaimage.MegaImageActivity;
import com.example.androidproject.ui.shops.ShopsActivity;

import java.util.ArrayList;
import java.util.Map;

public class ProductListActivity extends AppCompatActivity {

    Button exit_button;
    RecyclerView recyclerView;
    ArrayList<String> product_id, product_name, product_type, product_price;
    CustomAdapterProductList customAdapterProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_type = new ArrayList<>();
        product_price = new ArrayList<>();

        storeData();

        customAdapterProductList = new CustomAdapterProductList(ProductListActivity.this, product_id, product_name, product_type, product_price);
        recyclerView.setAdapter(customAdapterProductList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductListActivity.this));

        exit_button = (Button) findViewById(R.id.exit_button);
        exit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("DebugTag", "Value: " + product_id);
                Intent intent = new Intent(ProductListActivity.this, ShopsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void storeData() {
        SharedPreferences sp = ProductListActivity.this.getSharedPreferences("ProductsPrefs", Context.MODE_PRIVATE);
        product_id.add(sp.getString("p_id", ""));
        product_name.add(sp.getString("p_name", ""));
        product_type.add(sp.getString("p_type", ""));
        product_price.add(sp.getString("p_price", ""));
    }
}