package com.example.tarea_4;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

public class View_Foto_Activity extends AppCompatActivity {

    ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_foto);
        imgv = (ImageView) findViewById(R.id.imgv);
        Bundle bundle = getIntent().getExtras();
        byte [] bimg = bundle.getByteArray("img");
        Bitmap bit = BitmapFactory.decodeByteArray(bimg, 0, bimg.length);
        imgv.setImageBitmap(bit);
    }
}