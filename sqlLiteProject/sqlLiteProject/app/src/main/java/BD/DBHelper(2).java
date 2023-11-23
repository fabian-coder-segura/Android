package BD;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //Constante para creación de la tabla USUARIOS
    private static final String CREAR_TABLA_USUARIOS = "CREATE TABLE " + Constantes.TABLA_USURIO +
            " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            " NOMBRE TEXT NOT NULL," +
            " EMAIL TEXT NOT NULL)";

    //Constructor con la estructura para generar la baase de datos
    public DBHelper(@Nullable Context context) {
        super(context, Constantes.NOMBRE_BD, null, Constantes.VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creación de la tabla USUARIOS en La BD
        db.execSQL(CREAR_TABLA_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Actualización de la BD por cambiso de versión
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_USURIO);//Usuarios
        db.execSQL(CREAR_TABLA_USUARIOS);//Después de eliminar, crea nuevamente la tabla
    }
}
