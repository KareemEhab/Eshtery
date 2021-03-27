package com.example.eshtery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.example.eshtery.RecyclerViewAdapters.HorizontalCategoryRecyclerAdapter;
import com.example.eshtery.RecyclerViewAdapters.HorizontalRecyclerAdapter;
import com.example.eshtery.RecyclerViewClasses.CategoryHorizontalMainScreen;
import com.example.eshtery.RecyclerViewClasses.Items;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainUserScreen extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static CounterFab counterfab;
    public static Context context;

    private HorizontalRecyclerAdapter mAdapter;
    private ArrayList<Items> itemsList = new ArrayList<>();

    private HorizontalCategoryRecyclerAdapter categoryAdapter;
    private ArrayList<CategoryHorizontalMainScreen> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_screen);

        FloatingActionButton fab = findViewById(R.id.fab2);
        counterfab = (CounterFab)findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterfab.increase();
            }
        });



        RecyclerView recyclerCategoryView = findViewById(R.id.recyclerViewCategory);
        recyclerCategoryView.bringToFront();
        categoryAdapter = new HorizontalCategoryRecyclerAdapter(categoryList);
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(getApplicationContext());
        categoryLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerCategoryView.setLayoutManager(categoryLayoutManager);
        recyclerCategoryView.setItemAnimator(new DefaultItemAnimator());
        recyclerCategoryView.setAdapter(categoryAdapter);

        CategoryHorizontalMainScreen categoryItem1 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem2 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem3 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem4 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem5 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem6 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem7 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem8 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem9 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem10 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);
        CategoryHorizontalMainScreen categoryItem11 = new CategoryHorizontalMainScreen("REEEEE", R.drawable.toys);

        categoryList.add(categoryItem1);
        categoryList.add(categoryItem2);
        categoryList.add(categoryItem3);
        categoryList.add(categoryItem4);
        categoryList.add(categoryItem5);
        categoryList.add(categoryItem6);
        categoryList.add(categoryItem7);
        categoryList.add(categoryItem8);
        categoryList.add(categoryItem9);
        categoryList.add(categoryItem10);
        categoryAdapter.notifyDataSetChanged();



        RecyclerView recyclerView = findViewById(R.id.recyclerViewLatestItems);
        recyclerView.bringToFront();
        mAdapter = new HorizontalRecyclerAdapter(itemsList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        Items item1 = new Items("REEEEEE", "1000$", R.drawable.toys);
        Items item2 = new Items("REEEEEE", "1000$", R.drawable.toys);
        Items item3 = new Items("REEEEEE", "1000$", R.drawable.toys);
        Items item4 = new Items("REEEEEE", "1000$", R.drawable.toys);
        Items item5 = new Items("REEEEEE", "1000$", R.drawable.toys);
        Items item6 = new Items("REEEEEE", "1000$", R.drawable.toys);
        Items item7 = new Items("REEEEEE", "1000$", R.drawable.toys);
        Items item8 = new Items("REEEEEE", "1000$", R.drawable.toys);
        Items item9 = new Items("REEEEEE", "1000$", R.drawable.toys);

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

    @Override
    public void onBackPressed() {
        AlertDialog dialog = new AlertDialog.Builder(this).setMessage("Are you sure you want to exit?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        }).setNegativeButton("CANCEL", null).show();
    }
    public static void increment(String name)
    {
        counterfab.increase();
        Toast.makeText(context ,name, Toast.LENGTH_LONG).show();
    }
}