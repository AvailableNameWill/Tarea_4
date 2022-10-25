package com.example.tarea_4.config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tarea_4.clases.transacs;

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
}
