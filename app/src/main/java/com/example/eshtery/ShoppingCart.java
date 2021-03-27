package com.example.eshtery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.eshtery.RecyclerViewAdapters.HorizontalCategoryRecyclerAdapter;
import com.example.eshtery.RecyclerViewAdapters.HorizontalRecyclerAdapter;
import com.example.eshtery.RecyclerViewAdapters.ShoppingCartItemsAdapter;
import com.example.eshtery.RecyclerViewClasses.Items;
import com.example.eshtery.RecyclerViewClasses.ItemsList;

import java.util.ArrayList;

public class ShoppingCart extends AppCompatActivity {

    private ShoppingCartItemsAdapter mAdapter;
    private ArrayList<ItemsList> itemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerCategoryView = findViewById(R.id.recyclerViewShoppingCart);
        recyclerCategoryView.bringToFront();
        mAdapter = new ShoppingCartItemsAdapter(itemsList);
        LinearLayoutManager itemsLayoutManager = new LinearLayoutManager(getApplicationContext());
        itemsLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCategoryView.setLayoutManager(itemsLayoutManager);
        recyclerCategoryView.setItemAnimator(new DefaultItemAnimator());
        recyclerCategoryView.setAdapter(mAdapter);
    }
}