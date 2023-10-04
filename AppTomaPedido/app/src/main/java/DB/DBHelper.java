package DB;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.apptomapedido.MainActivity;

public class DBHelper extends SQLiteOpenHelper {
    private static final String CREAR_TABLA_PRODUCTOS = "CREATE TABLE " + Constantes.TABLA_PRODUCTOS +
            " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nombre TEXT NOT NULL," +
            " precio REAL NOT NULL)";


    public DBHelper(Context context) {
        super(context, Constantes.DATABASE_NAME, null, Constantes.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crea la tabla de productos
        db.execSQL(CREAR_TABLA_PRODUCTOS);

        // Inserta productos estáticos
        db.execSQL("INSERT INTO productos (nombre, precio) VALUES ('Sopa', 10.0)");
        db.execSQL("INSERT INTO productos (nombre, precio) VALUES ('Bandeja Paisa', 15.0)");
        db.execSQL("INSERT INTO productos (nombre, precio) VALUES ('Cazuela', 20.0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
