package com.example.eshtery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

public class AddItemActivity extends AppCompatActivity {

    private ImageView img;
    private Button btn;
    private Button btnBarCode;
    Uri imageUri;
    private static final int PICK_IMAGE = 1;
    public static TextView Barcode;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK)
        {
            imageUri = data.getData();
        try{
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
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

    public void scan()
    {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        img = findViewById(R.id.imageViewItemAddedImage);
        btn = findViewById(R.id.buttonAddImage);
        btnBarCode = findViewById(R.id.buttonBarCode);
        Barcode = findViewById(R.id.textViewBarCodeResult);


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("Image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Select Picture"),PICK_IMAGE);
            }
        });

        btnBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), BarCode.class));
                scan();
            }
        });
    }
}