package com.example.eshtery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HorizontalRecyclerAdapter extends RecyclerView.Adapter<HorizontalRecyclerAdapter.MyViewHolder> {
    private List<Item> items;
    class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView title, year, genre;
        ImageView img;
        TextView txtName;
        TextView txtPrice;
        ImageButton cart;
        ImageButton wishList;
        MyViewHolder(View view) {
            super(view);
            //title = view.findViewById(R.id.title);
            //genre = view.findViewById(R.id.genre);
            //year = view.findViewById(R.id.year);
            img = view.findViewById(R.id.imageAdapter);
            txtName = view.findViewById(R.id.textAdapterName);
            txtPrice = view.findViewById(R.id.textAdapterPrice);
            cart = view.findViewById(R.id.btnAdapterCart);
            wishList = view.findViewById(R.id.btnAdapterWishList);
        }
    }
    public HorizontalRecyclerAdapter(List<Item> moviesList) {
        this.items = moviesList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_listview, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //MovieModel movie = moviesList.get(position);
        //holder.title.setText(movie.getTitle());
        //holder.genre.setText(movie.getGenre());
        //holder.year.setText(movie.getYear());

        Item item = items.get(position);
        holder.img.setImageResource(item.getImage());
        holder.txtName.setText(item.getName());
        holder.txtPrice.setText(item.getPrice());
        holder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainScreen.increment(item.getName());
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
}