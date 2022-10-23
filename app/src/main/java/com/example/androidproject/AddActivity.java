package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidproject.ui.login.LoginActivity;
import com.example.androidproject.ui.megaimage.MegaImageActivity;
import com.example.androidproject.ui.shops.ShopsActivity;

public class AddActivity extends AppCompatActivity {

    EditText name_input, type_input, price_input;
    Button add_button,exitbutton;
    MegaImageActivity megaImageActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = (EditText) findViewById(R.id.product_name);
        type_input = (EditText) findViewById(R.id.product_type);
        price_input = (EditText) findViewById(R.id.product_price);
        add_button = (Button) findViewById(R.id.add_button);
        exitbutton = (Button) findViewById(R.id.exitbutton);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteProductsDBHelper db = new SQLiteProductsDBHelper(AddActivity.this);
                db.addProduct(name_input.getText().toString().trim(), type_input.getText().toString().trim(), Integer.valueOf(price_input.getText().toString().trim()));

            }
        });
        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ShopsActivity.class);
                startActivity(intent);
            }
        });
    }
}