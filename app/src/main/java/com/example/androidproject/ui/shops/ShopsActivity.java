package com.example.androidproject.ui.shops;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidproject.R;
import com.example.androidproject.databinding.ActivityShopsBinding;
import com.example.androidproject.ui.auchan.AuchanActivity;
import com.example.androidproject.ui.carrefour.CarrefourActivity;
import com.example.androidproject.ui.login.LoginActivity;
import com.example.androidproject.ui.megaimage.MegaImageActivity;
import com.example.androidproject.ui.register.RegisterActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ShopsActivity extends AppCompatActivity {
    private ActivityShopsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shops);

        binding = ActivityShopsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

    }

    public void getToMegaImage(View v) {
        Intent intent = new Intent(ShopsActivity.this, MegaImageActivity.class);
        startActivity(intent);
    }

    public void getToAuchan(View v) {
        Intent intent = new Intent(ShopsActivity.this, AuchanActivity.class);
        startActivity(intent);
    }

    public void getToCarrefour(View v) {
        Intent intent = new Intent(ShopsActivity.this, CarrefourActivity.class);
        startActivity(intent);
    }
}