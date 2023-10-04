package com.example.apptomapedido;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import DB.Constantes;
import DB.DBHelper;

public class Productos extends AppCompatActivity {

    Button mesa;
    ListView lvProductos;
    ArrayList<String> listaInformacion;//Lista los nombres de los usuarios

    ArrayList<producto> listaproductos;//Lista objetos de tipo Usuario
    ArrayAdapter adapter;//Adpater para manejo de la lista

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        mesa = findViewById(R.id.mesa);
        lvProductos = findViewById(R.id.lvProductos);

        //Inicializa listados
        listaInformacion = new ArrayList<>();
        listaproductos = new ArrayList<>();
        consultaProductos();//Carga contenido a los arreglos

        //Adaptapter para la visualización del ListView
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,listaInformacion);
        lvProductos.setAdapter(adapter);//Relaciona el adapter con el ListView

        mesa.setOnClickListener(v->{
            startActivity(new Intent(this,ResumenMesa.class));
        });
    }

    private void consultaProductos(){
        try{
            //Conexión
            DBHelper helper = new DBHelper(this);
            //Objeto para manipulación de datos
            SQLiteDatabase db = helper.getReadableDatabase();
            //Cursosr con resultados
            Cursor cursor = db.rawQuery("SELECT * FROM productos", null);
            producto productos;

            while (cursor.moveToNext()){
                //Instancia la clase Usuarios
                productos = new producto(cursor.getLong(0),
                        cursor.getString(1), cursor.getDouble(2));

                listaproductos.add(productos);//Incluye objeto en la lista
                listaInformacion.add(productos.getNombre() +
                        " (" + productos.getPrecio() + ")");//INcluye String para visualización
            }

            if(!listaproductos.isEmpty() && adapter!=null){//Cuando existan elementos en la lista se actualiza el ListView
                adapter.notifyDataSetChanged();//Actualiza referencia del adapter para visualización de elementos
            }


        }
        catch (Exception e){
            Toast.makeText(this,"ERROR:  No fue posible consultar la información de los usuarios",Toast.LENGTH_LONG).show();
        }
    }



}