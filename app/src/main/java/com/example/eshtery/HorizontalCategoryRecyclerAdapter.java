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

public class HorizontalCategoryRecyclerAdapter extends RecyclerView.Adapter<HorizontalCategoryRecyclerAdapter.MyViewHolder> {
    private List<Category> categories;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView img;
        MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.categoryImgButton);
            txtName = view.findViewById(R.id.textCategoryAdapter);
        }
    }
    public HorizontalCategoryRecyclerAdapter(List<Category> categories) {
        this.categories = categories;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_category_listview, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.txtName.setText(category.getName());
        holder.img.setImageResource(category.getImage());
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(category.getName());
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(category.getName());
            }
        });

    }

    private void click(String name){
        switch(name)
        {
            case "Kitchen":
                break;
            case "Tools":
                break;
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}