package com.example.androidproject.ui.auchan;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.ui.megaimage.CustomAdapterMegaImage;

import java.util.ArrayList;

public class CustomAdapterAuchan extends RecyclerView.Adapter<CustomAdapterAuchan.MyViewHolder> {
    public Context context;
    SharedPreferences sp;
    public ArrayList product_id, product_name, product_type, product_price;

    CustomAdapterAuchan(Context context,
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
    @SuppressLint("NotifyDataSetChanged")
    public void setFilter(ArrayList<String> lst, ArrayList<String> lst2, ArrayList<String> lst3, ArrayList<String> lst4){
        product_name = lst;
        product_price = lst2;
        product_id = lst3;
        product_type = lst4;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CustomAdapterAuchan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.auchan_row, parent, false);
        return new CustomAdapterAuchan.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapterAuchan.MyViewHolder holder, int position) {
        holder.product_id.setText(String.valueOf(product_id.get(position)));
        holder.product_name.setText(String.valueOf(product_name.get(position)));
        holder.product_type.setText(String.valueOf(product_type.get(position)));
        holder.product_price.setText(String.valueOf(product_price.get(position)));
        sp = context.getSharedPreferences("ProductsPrefs", Context.MODE_PRIVATE);
        holder.auchanCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Added to your list!", Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor editor = sp.edit();

                editor.putString("p_id", String.valueOf(product_id.get(position)));
                editor.putString("p_name", String.valueOf(product_name.get(position)));
                editor.putString("p_type", String.valueOf(product_type.get(position)));
                editor.putString("p_price", String.valueOf(product_price.get(position)));
                editor.apply();
            }
        });
    }

    @Override
    public int getItemCount() {
        return product_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView product_id, product_name, product_type, product_price;
        CardView auchanCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            product_id = itemView.findViewById(R.id.product_id);
            product_name = itemView.findViewById(R.id.product_name);
            product_type = itemView.findViewById(R.id.product_type);
            product_price = itemView.findViewById(R.id.product_price);
            auchanCard = itemView.findViewById(R.id.auchanCard);
        }
    }
}
