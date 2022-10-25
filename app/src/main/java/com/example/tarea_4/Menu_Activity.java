package com.example.tarea_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Activity extends AppCompatActivity {

    Button btnSdataA, btnVdataA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnSdataA = (Button) findViewById(R.id.btnSdataA);
        btnVdataA = (Button) findViewById(R.id.btnVdataA);

        btnSdataA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Save_Data_Activity.class);
                startActivity(intent);
            }
        });

        btnVdataA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), View_Data_Activity.class);
                startActivity(intent);
            }
        });
    }
}