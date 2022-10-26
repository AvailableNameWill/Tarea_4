package com.example.tarea_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.tarea_4.clases.foto;
import com.example.tarea_4.clases.transacs;
import com.example.tarea_4.config.conexion;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class View_Data_Activity extends AppCompatActivity {

    ListView fotoList;
    Button btnViewF;
    ArrayList<foto> _foto;
    ArrayList<String> concatList;

    private conexion con;
    private Integer id = 0;
    private Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        con = new conexion(this, transacs.dbName, null, 1);
        chargeObj();
        _foto = con.getData(_foto);
        llenarList();
        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, concatList);
        fotoList.setAdapter(adp);

        fotoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                id = _foto.get(i).getId();
            }
        });

        btnViewF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id!=0){
                    img = con.getImg(Integer.toString(id));
                    ByteArrayOutputStream ba = new ByteArrayOutputStream();
                    img.compress(Bitmap.CompressFormat.JPEG, 20, ba);
                    byte[] byImg= ba.toByteArray();
                    Intent intent = new Intent(getApplicationContext(), View_Foto_Activity.class);
                    intent.putExtra("img",byImg);
                    startActivity(intent);
                }
            }
        });
    }

    private void llenarList(){
        concatList = new ArrayList<String>();

        for(int i=0; i<_foto.size(); i++){
            concatList.add(_foto.get(i).getName() + "  |  " +
                           _foto.get(i).getDescription());
        }
    }

    private void chargeObj(){
        fotoList = (ListView) findViewById(R.id.fotoListV);
        btnViewF = (Button) findViewById(R.id.btnViewF);
    }
}