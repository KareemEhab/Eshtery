package com.example.eshtery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetails extends AppCompatActivity {

    public static String currentItem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        Database db = new Database(this);
        Cursor c = db.fetchCurrentItem(currentItem);
        ImageView img = findViewById(R.id.imageViewItemDetails);
        TextView name = findViewById(R.id.textViewItemDetailsName);
        TextView price = findViewById(R.id.textViewDetailsPrice);
        TextView description = findViewById(R.id.textViewItemDetailsActualDescription);

        byte[] image = c.getBlob(6);
        img.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));
        name.setText(c.getString(1));
        price.setText(c.getString(2));
        description.setText(c.getString(7));

        Button btn = findViewById(R.id.buttonItemDetailsCart);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("CART", MODE_PRIVATE);
                String item = "";
                item = pref.getString(name.getText().toString(), item);
                if(item.equals(""))
                {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString(name.getText().toString(), name.getText().toString());
                    editor.apply();
                    Toast.makeText(getApplicationContext(),"Item Added to Cart!", Toast.LENGTH_LONG).show();
                    MainUserScreen.increment();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Item Already Added!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}