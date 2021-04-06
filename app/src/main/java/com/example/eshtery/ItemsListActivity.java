package com.example.eshtery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.eshtery.RecyclerViewAdapters.HorizontalCategoryRecyclerAdapter;
import com.example.eshtery.RecyclerViewAdapters.VerticalItemsListAdapter;
import com.example.eshtery.RecyclerViewClasses.ItemsList;

import java.util.ArrayList;

public class ItemsListActivity extends AppCompatActivity {

    public static String currentCategor = "";

    private VerticalItemsListAdapter adapter;
    private ArrayList<ItemsList> list = new ArrayList<ItemsList>();
    public static boolean search = false;
    public static String searchKeyword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        RecyclerView recyclerCategoryView = findViewById(R.id.recyclerViewItems);
        recyclerCategoryView.bringToFront();
        adapter = new VerticalItemsListAdapter(list);
        LinearLayoutManager itemsLayoutManager = new LinearLayoutManager(getApplicationContext());
        itemsLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerCategoryView.setLayoutManager(itemsLayoutManager);
        recyclerCategoryView.setItemAnimator(new DefaultItemAnimator());
        recyclerCategoryView.setAdapter(adapter);

        Database db = new Database(this);
        if(search == false) {


            Cursor C = db.fetchAllCategoryItems(currentCategor);
            while (!C.isAfterLast()) {
                byte[] image = C.getBlob(6);
                ItemsList item = new ItemsList(BitmapFactory.decodeByteArray(image, 0, image.length), C.getString(1), C.getString(2));
                list.add(item);
                C.moveToNext();
            }
            adapter.notifyDataSetChanged();
        }

        else
        {
            Cursor C = db.Search(searchKeyword);
            while(!C.isAfterLast()){
                byte[] image = C.getBlob(6);
                ItemsList item = new ItemsList(BitmapFactory.decodeByteArray(image, 0, image.length), C.getString(1), C.getString(2));
                list.add(item);
                C.moveToNext();
            }
            adapter.notifyDataSetChanged();
        }

        recyclerCategoryView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerCategoryView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        ItemDetails.currentItem = list.get(position).getName();
                        Intent I = new Intent(ItemsListActivity.this, ItemDetails.class);
                        startActivity(I);
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }
}