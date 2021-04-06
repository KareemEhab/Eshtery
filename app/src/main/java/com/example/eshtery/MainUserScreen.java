package com.example.eshtery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.example.eshtery.RecyclerViewAdapters.HorizontalCategoryRecyclerAdapter;
import com.example.eshtery.RecyclerViewAdapters.HorizontalRecyclerAdapter;
import com.example.eshtery.RecyclerViewClasses.CategoryHorizontalMainScreen;
import com.example.eshtery.RecyclerViewClasses.Items;
import com.example.eshtery.RecyclerViewClasses.ItemsList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class MainUserScreen extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public static CounterFab counterfab;
    public static Context context;
    public String barcode = "";
    public EditText searchbarrr;

    private HorizontalRecyclerAdapter mAdapter;
    private ArrayList<Items> itemsList = new ArrayList<>();

    private HorizontalCategoryRecyclerAdapter categoryAdapter;
    private ArrayList<CategoryHorizontalMainScreen> categoryList = new ArrayList<>();


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10)
        {
            if(resultCode == RESULT_OK && data != null)
            {
                ArrayList<String> myResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                searchbarrr.setText(myResult.get(0));
            }
        }
        else {


            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (intentResult != null) {
                if (intentResult.getContents() == null)
                    barcode = "Cancelled";
                else {
                    barcode = intentResult.getContents();
                    Database db = new Database(getApplicationContext());
                    Cursor C = db.getBarcodeItem(barcode);
                    Intent I = new Intent(getApplicationContext(), ItemsListActivity.class);
                    ItemsListActivity.search = true;
                    if (C.getCount() != 0)
                        ItemsListActivity.searchKeyword = C.getString(0);
                    startActivity(I);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_screen);

        Button btnSearch = findViewById(R.id.buttonSearch);
        Button btnVoice = findViewById(R.id.buttonVoice);
        Button btnBarcode = findViewById(R.id.buttonBarcodeSearch);
        searchbarrr = findViewById(R.id.editTextSearchBar);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(MainUserScreen.this, ItemsListActivity.class);
                ItemsListActivity.search = true;
                ItemsListActivity.searchKeyword = ((EditText)findViewById(R.id.editTextSearchBar)).getText().toString();
                startActivity(I);
            }
        });

        btnBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });

        btnVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSpeechInput(view);
            }
        });




        FloatingActionButton fab = findViewById(R.id.fab2);
        counterfab = (CounterFab)findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //counterfab.increase();
                Intent I = new Intent(MainUserScreen.this, ShoppingCart.class);
                startActivity(I);
            }
        });

        SharedPreferences pref = getSharedPreferences("CART", MODE_PRIVATE);
        Map<String,?> keys = pref.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            increment();
        }



        RecyclerView recyclerCategoryView = findViewById(R.id.recyclerViewCategory);
        recyclerCategoryView.bringToFront();
        categoryAdapter = new HorizontalCategoryRecyclerAdapter(categoryList);
        LinearLayoutManager categoryLayoutManager = new LinearLayoutManager(getApplicationContext());
        categoryLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerCategoryView.setLayoutManager(categoryLayoutManager);
        recyclerCategoryView.setItemAnimator(new DefaultItemAnimator());
        recyclerCategoryView.setAdapter(categoryAdapter);

        CategoryHorizontalMainScreen categoryItem1 = new CategoryHorizontalMainScreen("Electronics", R.drawable.electronics);
        CategoryHorizontalMainScreen categoryItem2 = new CategoryHorizontalMainScreen("Kitchen", R.drawable.kitchen);
        CategoryHorizontalMainScreen categoryItem3 = new CategoryHorizontalMainScreen("Sports", R.drawable.sportsimg);
        CategoryHorizontalMainScreen categoryItem4 = new CategoryHorizontalMainScreen("Toys", R.drawable.toysimg);
        CategoryHorizontalMainScreen categoryItem5 = new CategoryHorizontalMainScreen("Home", R.drawable.homesupplies);
        CategoryHorizontalMainScreen categoryItem6 = new CategoryHorizontalMainScreen("Perfumes", R.drawable.perfumesimg);

        categoryList.add(categoryItem1);
        categoryList.add(categoryItem2);
        categoryList.add(categoryItem3);
        categoryList.add(categoryItem4);
        categoryList.add(categoryItem5);
        categoryList.add(categoryItem6);
        categoryAdapter.notifyDataSetChanged();

        recyclerCategoryView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerCategoryView ,new RecyclerItemClickListener.OnItemClickListener() {
            @Override public void onItemClick(View view, int position) {
                // do whatever
                ItemsListActivity.currentCategor = categoryList.get(position).getName();
                Intent I = new Intent(MainUserScreen.this, ItemsListActivity.class);
                ItemsListActivity.search = false;
                startActivity(I);
            }

            @Override public void onLongItemClick(View view, int position) {
                // do whatever
            }
        })
);



        /*RecyclerView recyclerView = findViewById(R.id.recyclerViewLatestItems);
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
        mAdapter.notifyDataSetChanged();*/



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
    public static void increment()
    {
        counterfab.increase();
    }

    public static void decrement()
    {
        counterfab.decrease();
    }

    public static void loadActivity(String Category)
    {
    }

    public void startNewActivity()
    {
        Intent I = new Intent(MainUserScreen.this, AddItemActivity.class);
        startActivity(I);
    }

    public void scan()
    {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    public void getSpeechInput(View view)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, Locale.getDefault());

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent,10);
        else
            Toast.makeText(getApplicationContext(), "Device doesn't support this function", Toast.LENGTH_SHORT).show();
    }
}