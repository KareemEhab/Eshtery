package com.example.eshtery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.eshtery.RecyclerViewAdapters.HorizontalCategoryRecyclerAdapter;
import com.example.eshtery.RecyclerViewAdapters.HorizontalRecyclerAdapter;
import com.example.eshtery.RecyclerViewAdapters.ShoppingCartItemsAdapter;
import com.example.eshtery.RecyclerViewClasses.Items;
import com.example.eshtery.RecyclerViewClasses.ItemsList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart extends AppCompatActivity {

    private ShoppingCartItemsAdapter mAdapter;
    private ArrayList<ItemsList> itemsList = new ArrayList<>();
    public static int totalprice = 0;
    RecyclerView recyclerCategoryView;
    public static Map<String, Integer> quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //quantity.clear();

        recyclerCategoryView = findViewById(R.id.recyclerViewShoppingCart);
        recyclerCategoryView.bringToFront();
        mAdapter = new ShoppingCartItemsAdapter(itemsList);
        LinearLayoutManager itemsLayoutManager = new LinearLayoutManager(getApplicationContext());
        itemsLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCategoryView.setLayoutManager(itemsLayoutManager);
        recyclerCategoryView.setItemAnimator(new DefaultItemAnimator());
        recyclerCategoryView.setAdapter(mAdapter);
        totalprice = 0;
        quantity = new HashMap<String,Integer>();

        Database db = new Database(this);
        SharedPreferences pref = getSharedPreferences("CART", MODE_PRIVATE);
        Map<String,?> keys = pref.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            Cursor C = db.fetchCurrentItem(entry.getValue().toString());
            byte[] image = C.getBlob(6);
            ItemsList item = new ItemsList(BitmapFactory.decodeByteArray(image,0,image.length),C.getString(1), C.getString(2), R.drawable.subtract,R.drawable.add);
            itemsList.add(item);
            mAdapter.notifyDataSetChanged();
            totalprice += Integer.parseInt(C.getString(2).substring(0,C.getString(2).length()-1));
            quantity.put(C.getString(1), 1);
        }

        Button btn = findViewById(R.id.buttonCheckout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(ShoppingCart.this, Checkout.class);
                Bundle b = new Bundle();
                b.putString("total", String.valueOf(totalprice));
                I.putExtras(b);
                finish();
                startActivity(I);
            }
        });

        recyclerCategoryView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerCategoryView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        SharedPreferences pref = getSharedPreferences("CART", MODE_PRIVATE);
                        pref.edit().remove(itemsList.get(position).getName()).commit();
                        itemsList.remove(position);
                        mAdapter.notifyItemRemoved(position);
                        mAdapter.notifyItemRangeChanged(position, itemsList.size());
                        MainUserScreen.decrement();
                    }
                })
        );
    }
}