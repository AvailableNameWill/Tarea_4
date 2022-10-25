package com.example.tarea_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tarea_4.config.conexion;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Save_Data_Activity extends AppCompatActivity {

    private ImageView img;
    private EditText txtName;
    private EditText txtDesc;
    private Button btnTake;
    private Button btnSave;
    private conexion con;
    String PathImagen;

    static final int Peticion_captura_imagen = 100;
    static final int peticion_acceso_cam = 201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);
        chargeObj();

        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    permisos();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty()){

                }
            }
        });
    }

    private boolean isEmpty(){
        if(txtName.getText().toString().isEmpty()){
            Toast.makeText(this, "!!!Aviso \n No puede dejar campos vacios: Nombre", Toast.LENGTH_SHORT).show();
            return true;
        }else if(txtDesc.getText().toString().isEmpty()){
            Toast.makeText(this, "!!!Aviso \n No puede dejar campos vacios: Descripcion", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private void permisos(){
        //validar si el permiso esta otorgado o no para tomar fotos
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, peticion_acceso_cam);
        }else{
            TakePhotoDir();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == peticion_acceso_cam){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                TakePhotoDir();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Peticion_captura_imagen && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imag = (Bitmap) extras.get("data");
            img.setImageBitmap(imag);
            galleryAddPic();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        PathImagen = image.getAbsolutePath();
        return image;
    }

    private void TakePhotoDir() {
        Intent Intenttakephoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Intenttakephoto.resolveActivity(getPackageManager()) != null) {
            File foto = null;

            try {
                foto = createImageFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            if (foto != null) {
                Uri fotoUri = FileProvider.getUriForFile(this, "com.example.tarea_4.fileprovider", foto);
                Intenttakephoto.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(Intenttakephoto, Peticion_captura_imagen);

            }
        }
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(PathImagen);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void chargeObj(){
        img = (ImageView) findViewById(R.id.img);
        txtName = (EditText) findViewById(R.id.txtName);
        txtDesc = (EditText) findViewById(R.id.txtDesc);
        btnTake =(Button) findViewById(R.id.btnTakeF);
        btnSave = (Button) findViewById(R.id.btnSave);
    }
}