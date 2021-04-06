package com.example.eshtery.RecyclerViewAdapters;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshtery.Database;
import com.example.eshtery.MainUserScreen;
import com.example.eshtery.R;
import com.example.eshtery.RecyclerViewClasses.ItemsList;
import com.example.eshtery.ShoppingCart;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ShoppingCartItemsAdapter extends RecyclerView.Adapter<ShoppingCartItemsAdapter.MyViewHolder> {
    private List<ItemsList> items;
    class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView title, year, genre;
        ImageView img;
        TextView txtName;
        TextView txtPrice;
        ImageButton btnSubtract;
        ImageButton btnAdd;
        TextView txtQuantity;
        MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.imageViewItemsList);
            txtName = view.findViewById(R.id.textViewItemsName);
            txtPrice = view.findViewById(R.id.textViewItemsPrice);
            btnSubtract = view.findViewById(R.id.imageButtonItemListMinus);
            btnAdd = view.findViewById(R.id.imageButtonItemListPlus);
            txtQuantity = view.findViewById(R.id.textViewItemListCount);
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
        holder.img.setImageBitmap(item.getImage());
        holder.txtName.setText(item.getName());
        holder.txtPrice.setText(item.getPrice());

            holder.txtQuantity.setText("1");
            holder.btnSubtract.setImageResource(item.getSubImage());
            holder.btnAdd.setImageResource(item.getAddImage());
            holder.btnSubtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item.quantity == 0)
                    {

                    }
                    holder.txtQuantity.setText(String.valueOf(Integer.parseInt(holder.txtQuantity.getText().toString())-1));
                    item.quantity--;
                    ShoppingCart.totalprice -= Integer.parseInt(item.getPrice().substring(0,item.getPrice().length()-1));
                    ShoppingCart.quantity.put(item.getName(), ShoppingCart.quantity.get(item.getName())-1);
                }
            });

            holder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.txtQuantity.setText(String.valueOf(Integer.parseInt(holder.txtQuantity.getText().toString())+1));
                    item.quantity++;
                    ShoppingCart.totalprice += Integer.parseInt(item.getPrice().substring(0,item.getPrice().length()-1));
                    ShoppingCart.quantity.put(item.getName(), ShoppingCart.quantity.get(item.getName())+1);
                }
            });

    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}