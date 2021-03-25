package com.example.eshtery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomHorizontalAdapter extends ArrayAdapter<Item> {

    private static final String TAG = "CustomHorizontalAdapter";
    private Context context;
    int resource;

    public CustomHorizontalAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String Name = getItem(position).getName();
        String Price = getItem(position).getPrice();
        int Image = getItem(position).getImage();

        Item item = new Item(Name,Price,Image);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent,false);
        ImageView img = (ImageView)convertView.findViewById(R.id.imageAdapter);
        TextView txtName = (TextView)convertView.findViewById(R.id.textAdapterName);
        TextView txtPrice = (TextView)convertView.findViewById(R.id.textAdapterPrice);
        ImageButton cart = (ImageButton) convertView.findViewById(R.id.btnAdapterCart);
        ImageButton wishList = (ImageButton) convertView.findViewById(R.id.btnAdapterWishList);

        img.setImageResource(Image);
        txtName.setText(Name);
        txtPrice.setText(Price);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainScreen.increment(Name);
            }
        });
        return convertView;
    }
}
