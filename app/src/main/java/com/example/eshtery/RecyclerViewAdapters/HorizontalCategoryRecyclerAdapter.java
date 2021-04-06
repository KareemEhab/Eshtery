package com.example.eshtery.RecyclerViewAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshtery.AddItemActivity;
import com.example.eshtery.ItemsListActivity;
import com.example.eshtery.MainActivity;
import com.example.eshtery.R;
import com.example.eshtery.RecyclerViewClasses.CategoryHorizontalMainScreen;

import java.util.List;

public class HorizontalCategoryRecyclerAdapter extends RecyclerView.Adapter<HorizontalCategoryRecyclerAdapter.MyViewHolder> {
    private List<CategoryHorizontalMainScreen> categories;
    Context context;
    public static String Category = "Electronics";

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        ImageView img;
        MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.categoryImgButton);
            txtName = view.findViewById(R.id.textCategoryAdapter);
        }
    }
    public HorizontalCategoryRecyclerAdapter(List<CategoryHorizontalMainScreen> categories) {
        this.categories = categories;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_custom_category_homescreen_list, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CategoryHorizontalMainScreen category = categories.get(position);
        holder.txtName.setText(category.getName());
        holder.img.setImageResource(category.getImage());
        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category = category.getName();
                ItemsListActivity.currentCategor = holder.txtName.getText().toString();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category = category.getName();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}