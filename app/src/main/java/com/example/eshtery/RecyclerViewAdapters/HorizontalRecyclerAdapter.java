package com.example.eshtery.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshtery.MainUserScreen;
import com.example.eshtery.R;
import com.example.eshtery.RecyclerViewClasses.Items;

import java.util.List;

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<HorizontalRecyclerAdapter.MyViewHolder> {
    private List<Items> items;
    class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView title, year, genre;
        ImageView img;
        TextView txtName;
        TextView txtPrice;
        ImageButton cart;
        ImageButton wishList;
        MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageAdapter);
            txtName = view.findViewById(R.id.textAdapterName);
            txtPrice = view.findViewById(R.id.textAdapterPrice);
            cart = view.findViewById(R.id.btnAdapterCart);
            wishList = view.findViewById(R.id.btnAdapterWishList);
        }
    }
    public HorizontalRecyclerAdapter(List<Items> moviesList) {
        this.items = moviesList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_custom_homescreen_horizontal_list, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Items item = items.get(position);
        holder.img.setImageResource(item.getImage());
        holder.txtName.setText(item.getName());
        holder.txtPrice.setText(item.getPrice());
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainUserScreen.increment(item.getName());
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}