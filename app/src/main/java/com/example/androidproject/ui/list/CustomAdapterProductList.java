package com.example.androidproject.ui.list;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;

import java.util.ArrayList;

public class CustomAdapterProductList  extends RecyclerView.Adapter<CustomAdapterProductList.MyViewHolder> {
    public Context context;
    public ArrayList product_id, product_name, product_type, product_price;

    CustomAdapterProductList(Context context,
                             ArrayList product_id,
                             ArrayList product_name,
                             ArrayList product_type,
                             ArrayList product_price) {
        this.context = context;
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_type = product_type;
        this.product_price = product_price;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.product_list_row, parent, false);
        return new CustomAdapterProductList.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterProductList.MyViewHolder holder, int position) {
        SharedPreferences sp = context.getSharedPreferences("ProductsPrefs", Context.MODE_PRIVATE);
        holder.product_id.setText(sp.getString("p_id", ""));
        holder.product_name.setText(sp.getString("p_name", ""));
        holder.product_type.setText(sp.getString("p_type", ""));
        holder.product_price.setText(sp.getString("p_price", ""));
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_id, product_name, product_type, product_price;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id = itemView.findViewById(R.id.product_id);
            product_name = itemView.findViewById(R.id.product_name);
            product_type = itemView.findViewById(R.id.product_type);
            product_price = itemView.findViewById(R.id.product_price);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
