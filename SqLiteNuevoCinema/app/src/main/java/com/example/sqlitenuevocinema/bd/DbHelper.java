package com.example.sqlitenuevocinema.bd;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.IconMarginSpan;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {



    public DbHelper(@Nullable Context context) {
        super(context, Constantes.NOMBRE_BD, null, Constantes.VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creación de tabalas
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_USUARIO);
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_VENTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Elimina tabla si existe
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.NOMBRE_TABLA_USUARIO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.NOMBRE_TABLA_VENTA);

        //Crea nuevamente las tablas
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_USUARIO);
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_VENTAS);
    }
}
