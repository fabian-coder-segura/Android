package DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(@Nullable Context context) {
        super(context, Constantes.NOMBRE_DB, null, Constantes.VERSION_BD);
    }

    /**
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_USUARIO);
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_VENTAS);
    }

    /**
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //ELIMINA LAS TABLAS SI EXISTE
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.NOMBRE_TABLA_USUARIO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constantes.NOMBRE_TABLA_VENTA);
        //CREA LAS TABLAS NUEVAMENTE
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_USUARIO);
        sqLiteDatabase.execSQL(Constantes.CREAR_TABLA_VENTAS);
    }
}
