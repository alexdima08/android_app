package com.example.androidproject.ui.auchan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.androidproject.AddActivity;
import com.example.androidproject.R;
import com.example.androidproject.SQLiteProductsDBHelper;
import com.example.androidproject.ui.list.ProductListActivity;
import com.example.androidproject.ui.megaimage.CustomAdapterMegaImage;
import com.example.androidproject.ui.megaimage.MegaImageActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AuchanActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button, list_button;
    SQLiteProductsDBHelper db;
    ArrayList<String> product_id, product_name, product_type, product_price;
    CustomAdapterAuchan customAdapterAuchan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auchan);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        list_button = (FloatingActionButton) findViewById(R.id.displaylist);
        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuchanActivity.this, ProductListActivity.class);
                startActivity(intent);
            }
        });

        add_button = (FloatingActionButton) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AuchanActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        db = new SQLiteProductsDBHelper(AuchanActivity.this);
        product_id = new ArrayList<>();
        product_name = new ArrayList<>();
        product_type = new ArrayList<>();
        product_price = new ArrayList<>();

        storeData();

        customAdapterAuchan = new CustomAdapterAuchan(AuchanActivity.this, product_id, product_name, product_type, product_price);
        recyclerView.setAdapter(customAdapterAuchan);
        recyclerView.setLayoutManager(new LinearLayoutManager(AuchanActivity.this));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem search = menu.findItem(R.id.searchbutton);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s = s.toLowerCase();
//                List<Pair<String, Pair<String, Integer>>> newList = new ArrayList<>() ;
                ArrayList<String> newList = new ArrayList<>();
                ArrayList<String> newList2 = new ArrayList<>();
                ArrayList<String> newList3 = new ArrayList<>();
                ArrayList<String> newList4 = new ArrayList<>();
                for(int i = 0; i< product_name.size(); i++)
                {
                    String name = product_name.get(i);
                    if(name.toLowerCase().contains(s))
                    {
                        newList.add(name);
                        newList2.add(product_price.get(i));
                        newList3.add(product_id.get(i));
                        newList4.add(product_type.get(i));
                    }

                }
                customAdapterAuchan.setFilter(newList,newList2,newList3,newList4);
                return false;
            }
        });
        return true;
    }
    public void storeData() {
        Cursor cursor = db.readAllDataAuchan();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                product_id.add(cursor.getString(0));
                product_name.add(cursor.getString(1));
                product_type.add(cursor.getString(2));
                product_price.add(cursor.getString(3));
            }
        }
    }
}