package com.example.eshtery;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Testing extends AppCompatActivity {
    private HorizontalRecyclerAdapter mAdapter;
    private ArrayList<Item> itemsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new HorizontalRecyclerAdapter(itemsList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();
    }
    private void prepareMovieData() {
        Item item1 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item2 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item3 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item4 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item5 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item6 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item7 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item8 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item9 = new Item("REEEEEE", "1000$", R.drawable.toys);

        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        itemsList.add(item4);
        itemsList.add(item5);
        itemsList.add(item6);
        itemsList.add(item7);
        itemsList.add(item8);
        itemsList.add(item9);
        mAdapter.notifyDataSetChanged();
    }
}


/*
        Log.d(TAG, "onCreate: Started.");
        RecyclerView list = (RecyclerView) findViewById(R.id.listView1);
        Item item1 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item2 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item3 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item4 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item5 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item6 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item7 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item8 = new Item("REEEEEE", "1000$", R.drawable.toys);
        Item item9 = new Item("REEEEEE", "1000$", R.drawable.toys);

        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(item1);
        itemsList.add(item2);
        itemsList.add(item3);
        itemsList.add(item4);
        itemsList.add(item5);
        itemsList.add(item6);
        itemsList.add(item7);
        itemsList.add(item8);
        itemsList.add(item9);

        CustomHorizontalAdapter adapter = new CustomHorizontalAdapter(this, R.layout.custom_listview, itemsList);
        list.setAdapter(adapter);
 */