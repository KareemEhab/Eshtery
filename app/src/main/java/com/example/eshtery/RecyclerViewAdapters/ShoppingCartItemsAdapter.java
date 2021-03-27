package com.example.eshtery.RecyclerViewAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshtery.MainUserScreen;
import com.example.eshtery.R;
import com.example.eshtery.RecyclerViewClasses.ItemsList;

import java.util.List;

public class ShoppingCartItemsAdapter extends RecyclerView.Adapter<ShoppingCartItemsAdapter.MyViewHolder> {
    private List<ItemsList> items;
    class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView title, year, genre;
        ImageView img;
        TextView txtName;
        TextView txtPrice;
        ImageButton btnSubtract;
        ImageButton btnAdd;
        MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageViewItemsList);
            txtName = view.findViewById(R.id.textViewItemsName);
            txtPrice = view.findViewById(R.id.textViewItemsPrice);
            btnSubtract = view.findViewById(R.id.imageButtonItemListMinus);
            btnAdd = view.findViewById(R.id.imageButtonItemListPlus);
        }
    }
    public ShoppingCartItemsAdapter(List<ItemsList> moviesList) {
        this.items = moviesList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_items_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ItemsList item = items.get(position);
        holder.img.setImageResource(item.getImage());
        holder.txtName.setText(item.getName());
        holder.txtPrice.setText(item.getPrice());
        holder.btnSubtract.setImageResource(item.getImage());
        holder.btnAdd.setImageResource(item.getImage());
        holder.btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}