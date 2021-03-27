package com.example.eshtery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.eshtery.RecyclerViewAdapters.HorizontalCategoryRecyclerAdapter;
import com.example.eshtery.RecyclerViewAdapters.VerticalItemsListAdapter;
import com.example.eshtery.RecyclerViewClasses.ItemsList;

import java.util.ArrayList;

public class ItemsListActivity extends AppCompatActivity {


    private VerticalItemsListAdapter adapter;
    private ArrayList<ItemsList> list = new ArrayList<ItemsList>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        RecyclerView recyclerCategoryView = findViewById(R.id.recyclerViewItems);
        recyclerCategoryView.bringToFront();
        adapter = new VerticalItemsListAdapter(list);
        LinearLayoutManager itemsLayoutManager = new LinearLayoutManager(getApplicationContext());
        itemsLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerCategoryView.setLayoutManager(itemsLayoutManager);
        recyclerCategoryView.setItemAnimator(new DefaultItemAnimator());
        recyclerCategoryView.setAdapter(adapter);

        ItemsList item1 = new ItemsList( R.drawable.toys,"Item", "1000$");
        ItemsList item2 = new ItemsList( R.drawable.toys,"Item", "1000$");
        ItemsList item3 = new ItemsList( R.drawable.toys,"Item", "1000$");
        ItemsList item4 = new ItemsList( R.drawable.toys,"Item", "1000$");
        ItemsList item5 = new ItemsList( R.drawable.toys,"Item", "1000$");
        ItemsList item6 = new ItemsList( R.drawable.toys,"Item", "1000$");
        ItemsList item7 = new ItemsList( R.drawable.toys,"Item", "1000$");
        ItemsList item8 = new ItemsList( R.drawable.toys,"Item", "1000$");

        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);
        list.add(item5);
        list.add(item6);
        list.add(item7);
        list.add(item8);
        adapter.notifyDataSetChanged();
    }
}