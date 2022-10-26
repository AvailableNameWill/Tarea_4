package com.example.tarea_4.config;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.tarea_4.clases.foto;
import com.example.tarea_4.clases.transacs;

import java.util.ArrayList;

public class conexion extends SQLiteOpenHelper {

    public conexion(Context context, String dbName, SQLiteDatabase.CursorFactory fact, int version){
        super(context, dbName, fact, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(transacs.crearTblFoto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(transacs.dropFotos);
        onCreate(db);
    }

    public boolean saveData(byte[] img, String name, String descripcion){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(transacs.img, img);
        cv.put(transacs.name, name);
        cv.put(transacs.description, descripcion);

        long ins = db.insert(transacs.tblName, transacs.id, cv);
        if(ins==-1) return false;
        else return true;
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + transacs.tblName);
    }

    public ArrayList<foto> getData(ArrayList<foto> _foto){
        SQLiteDatabase db = this.getReadableDatabase();

        foto gFoto = null;
        _foto = new ArrayList<foto>();
        Cursor cursor = db.rawQuery("select id, name, description from " + transacs.tblName, null);

        while (cursor.moveToNext()){
            gFoto = new foto();
            gFoto.setId(cursor.getInt(0));
            gFoto.setName(cursor.getString(1));
            gFoto.setDescription(cursor.getString(2));
            gFoto.setImg(null);
            _foto.add(gFoto);
        }

        cursor.close();

        return _foto;
    }

    public Bitmap getImg(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select img from " + transacs.tblName + " where id = ?", new String[]{id});
        cursor.moveToFirst();
        byte[] bimg = cursor.getBlob(0);
        Bitmap img = BitmapFactory.decodeByteArray(bimg, 0, bimg.length);

        return img;
    }

    /*public ArrayList<foto> getFotos(ArrayList<foto> _foto) {
        SQLiteDatabase db =this.getReadableDatabase();
        foto gFoto = null;

        _foto = new ArrayList<foto>();
        Cursor cursor =db.rawQuery(transacs.getFotos, null);

        while (cursor.moveToNext()){
            gFoto = new foto();
            gFoto.setId(cursor.getInt(0));
            byte[] bimg = cursor.getBlob(1);
            Bitmap img = BitmapFactory.decodeByteArray(bimg,0,bimg.length);
            gFoto.setImg(img);
            gFoto.setName(cursor.getString(2));
            gFoto.setDescription(cursor.getString(3));

            _foto.add(gFoto);
        }

        cursor.close();

        return _foto;
    }*/
}
