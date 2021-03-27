package com.example.eshtery.RecyclerViewAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.eshtery.R;
import com.example.eshtery.RecyclerViewClasses.CateogryItemTypes;

import java.util.List;

public class CustomVerticalCategoryItemAdapter extends ArrayAdapter<CateogryItemTypes> {

    private static final String TAG = "CustomVerticalCategoryItemAdapter";
    private Context context;
    int resource;

    public CustomVerticalCategoryItemAdapter(@NonNull Context context, int resource, @NonNull List<CateogryItemTypes> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int Image = getItem(position).getImage();
        String Text = getItem(position).getCategoryName();

        CateogryItemTypes Category = new CateogryItemTypes(Text,Image);
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent,false);
        ImageButton img = (ImageButton) convertView.findViewById(R.id.categoryImgBtn);
        TextView txtName = (TextView)convertView.findViewById(R.id.categoryTextView);

        img.setImageResource(Image);
        txtName.setText(Text);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return convertView;
    }
}
