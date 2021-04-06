package com.example.eshtery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddItemActivity extends AppCompatActivity {

    private ImageView img;
    private Button btn;
    private Button btnBarCode;
    Uri imageUri;
    private static final int PICK_IMAGE = 1;
    public static TextView Barcode;
    private Button btnInsert;
    public Bitmap bitmap;
    Activity activity;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK)
        {
            imageUri = data.getData();
        try{
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            bitmap = BitmapFactory.decodeStream(inputStream);

            //img.setImageBitmap(rotateImageIfRequired(getApplicationContext(),bitmap,imageUri));
            img.setImageBitmap(bitmap);
        }catch(IOException e){
            e.printStackTrace();

        }

        }

        else{
            IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
            if(intentResult != null)
            {
                if(intentResult.getContents() == null)
                    Barcode.setText("Cancelled");
                else
                    Barcode.setText(intentResult.getContents());
            }
        }
    }

    private static Bitmap rotateImageIfRequired(Context context,Bitmap img, Uri selectedImage) {

        // Detect rotation
        int rotation = getRotation(context, selectedImage);
        if (rotation != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
            img.recycle();
            return rotatedImg;
        }
        else{
            return img;
        }
    }

    private static int getRotation(Context context, Uri selectedImage) {

        int rotation = 0;
        ContentResolver content = context.getContentResolver();

        Cursor mediaCursor = content.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[] { "orientation", "date_added" },
                null, null, "date_added desc");

        if (mediaCursor != null && mediaCursor.getCount() != 0) {
            while(mediaCursor.moveToNext()){
                rotation = mediaCursor.getInt(0);
                break;
            }
        }
        mediaCursor.close();
        return rotation;
    }

    public void scan()
    {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        img = (ImageView) findViewById(R.id.imageViewItemAddedImage);
        btn = findViewById(R.id.buttonAddImage);
        btnBarCode = findViewById(R.id.buttonBarCode);
        Barcode = findViewById(R.id.textViewBarCodeResult);
        btnInsert = findViewById(R.id.buttonAddItem);
        activity = this;


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                else
                    selectImage();
            }
        });

        btnBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext());
                String ItemName = ((EditText)findViewById(R.id.editTextItemName)).getText().toString();
                String ItemPrice = ((EditText)findViewById(R.id.editTextItemPrice)).getText().toString();
                String ItemQuantity = ((EditText)findViewById(R.id.editTextItemQuantity)).getText().toString();
                String BarCode = ((TextView)findViewById(R.id.textViewBarCodeResult)).getText().toString();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 0 , stream);
                byte[] image = stream.toByteArray();
                String Description = ((EditText)findViewById(R.id.editTextAddDescription)).getText().toString();
                int CategoryID = 0;
                String Category = ((Spinner)findViewById(R.id.spinnerItemCategory)).getSelectedItem().toString();
                switch(Category)
                {
                    case "Electronics":
                        CategoryID = 1;
                        break;
                    case "Kitchen":
                        CategoryID = 2;
                        break;
                    case "Sports":
                        CategoryID = 3;
                        break;
                    case "Toys":
                        CategoryID = 4;
                        break;
                    case "Home":
                        CategoryID = 5;
                        break;
                    case "Perfumes":
                        CategoryID = 6;
                        break;
                }
                db.insertIntoProducts(ItemName,ItemPrice,ItemQuantity,image,BarCode, Description, CategoryID);
                Toast.makeText(getApplicationContext(), "Item Added Successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void selectImage()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1 && grantResults.length > 0)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                selectImage();
            else
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();
        }
    }
}