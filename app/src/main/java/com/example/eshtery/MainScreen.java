package com.example.eshtery;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static CounterFab counterfab;
    public static Context context;

    private HorizontalRecyclerAdapter mAdapter;
    private ArrayList<Item> itemsList = new ArrayList<>();

    private HorizontalCategoryRecyclerAdapter categoryAdapter;
    private ArrayList<Category> categoryList = new ArrayList<>();

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

    private static final String TAG = "MainScreen";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        context = getApplicationContext();

        RecyclerView recyclerCategoryView = findViewById(R.id.horizontalCategoryView);
        recyclerCategoryView.bringToFront();
        categoryAdapter = new HorizontalCategoryRecyclerAdapter(categoryList);
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(getApplicationContext());
        categoryLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCategoryView.setLayoutManager(categoryLayoutManager);
        recyclerCategoryView.setItemAnimator(new DefaultItemAnimator());
        recyclerCategoryView.setAdapter(categoryAdapter);

        Category categoryItem1 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem2 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem3 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem4 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem5 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem6 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem7 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem8 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem9 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem10 = new Category("REEEEE", R.drawable.toys);
        Category categoryItem11 = new Category("REEEEE", R.drawable.toys);

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



        RecyclerView recyclerView = findViewById(R.id.horizontalView);
        recyclerView.bringToFront();
        mAdapter = new HorizontalRecyclerAdapter(itemsList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

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



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        counterfab = (CounterFab)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counterfab.increase();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_deals, R.id.nav_fashion, R.id.nav_electronics, R.id.nav_kitchen, R.id.nav_sports, R.id.nav_toys, R.id.nav_perfumes, R.id.nav_home, R.id.nav_wishlist, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static void increment(String name)
    {
        counterfab.increase();
        Toast.makeText(context ,name, Toast.LENGTH_LONG).show();
    }
}