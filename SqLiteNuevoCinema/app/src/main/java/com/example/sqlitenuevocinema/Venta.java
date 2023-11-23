package com.example.sqlitenuevocinema;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlitenuevocinema.bd.Constantes;
import com.example.sqlitenuevocinema.bd.DbHelper;

import java.util.ArrayList;

public class Venta extends AppCompatActivity {

    EditText txtCantidadAsientos;
    TextView txtTotal;
    Button btnComprar,btnRegresar,btnConsultar,btnEliminarVentas;
    String documentoUsuario;

    ArrayList<String> listaVentas;
    ListView listViewVenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        txtCantidadAsientos= findViewById(R.id.txtAsientos);
        txtTotal=findViewById(R.id.txtTotal);
        btnComprar=findViewById(R.id.btnComprar);
        btnRegresar=findViewById(R.id.btnRegresar);
        btnConsultar=findViewById(R.id.bntConsultar);
        btnEliminarVentas=findViewById(R.id.btneliminarVentas);
        listViewVenta = findViewById(R.id.listViewVentas);

        documentoUsuario=getIntent().getStringExtra("parametroDocumento");
        btnComprar.setOnClickListener(view -> {
            //Valida obligatoriedad de campos
            if (txtCantidadAsientos.getText().toString().equals("")) {
                Toast.makeText(this, "Es obligatorio registrar el número de asientos", Toast.LENGTH_SHORT).show();
            }
            else{
                ventaAsientos(documentoUsuario);
            }
        });

        //Regresar

        //Listar
        btnConsultar.setOnClickListener(view -> {

        });

        //Eliminar
        btnEliminarVentas.setOnClickListener(view -> {

        });
    }

    //Registra la venta en la BD
    private void ventaAsientos(String documento){
        try{
            long total =0;//Calcular el total de la venta
            txtTotal.setText("Total venta: " + "(Sin asignar)");//Visualiza el total de la venta
            //Conexión
            DbHelper helper = new DbHelper(this);
            //Objeto de acceso a la BD
            SQLiteDatabase base_datos = helper.getWritableDatabase();
            //Valores a insertar

            //Inserción
            long id = 0;

            //Cierra la conexión
            base_datos.close();

            if (id>0){
                txtCantidadAsientos.setText("");//Limpia campo
                Toast.makeText(this, "Venta registrada correctamente", Toast.LENGTH_SHORT).show();//Confirmación
            }
            else{
                Toast.makeText(this, "Error al registrar la venta", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error al registrar venta", Toast.LENGTH_SHORT).show();
        }
    }

    private void listarVentas(){
        try{
            //Conexió
            DbHelper helper = new DbHelper(this);
            //Objeto de acceso a la BD
            SQLiteDatabase base_datos = helper.getReadableDatabase();

            //Cursosr con resultados

            //Recorre cursos para visualizar contenido en Lis View a través de ArrayList
            listaVentas = new ArrayList<>();
            long totalVentas=0;

            //Agrega elemento a cursos
            //Acumula valor de ventas para visualizar al usuario

            if(totalVentas!=0){
                //Visualiza valor acumulado
                Toast.makeText(this, "Total ventas: ", Toast.LENGTH_SHORT).show();
                //Visualiza List View
                ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaVentas);
                listViewVenta.setAdapter(adaptador);
            }
            else {
                Toast.makeText(this, "La consulta no generó resultados", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "No fue posible consulta el usuario", Toast.LENGTH_SHORT).show();
        }
    }

    //Elimina todas las ventas
    private void eliminarVentas(){
        try{
            //Conexió
            DbHelper helper = new DbHelper(this);
            //Objeto de acceso a la BD
            SQLiteDatabase base_datos = helper.getWritableDatabase();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("¿Desea eliminar las ventas?")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Elimina toda la información de ventas

                            Toast.makeText(getApplicationContext(), "Información eliminada", Toast.LENGTH_SHORT).show();
                            listViewVenta.setAdapter(null);
                            listarVentas();//Actualiza lista
                        }
                    })
                    .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    }).create().show();
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}