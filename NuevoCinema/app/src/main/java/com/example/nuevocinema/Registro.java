package com.example.nuevocinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DB.Constantes;
import DB.DBHelper;

public class Registro extends AppCompatActivity {

    //relacion con layaut

    EditText txtDocumento, txtNombre, txtTelefono;
    Button btnRegistrar;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtDocumento = findViewById(R.id.TxtDocumentoR);
        txtNombre = findViewById(R.id.TxtNombreR);
        txtTelefono = findViewById(R.id.TxtTelefonoR);
        btnRegistrar = findViewById(R.id.btnRegistrar);
    }

    private void registroUsuario(){
        try {
            //conexion
            DBHelper helper = new DBHelper(this);
            //Objeto de la base de datos
            SQLiteDatabase base_datos = helper.getWritableDatabase();
            //valores
            ContentValues values = new ContentValues();
            values.put("DOCUMENTO", Integer.parseInt(txtDocumento.getText().toString()));
            values.put("NOMBRE",txtNombre.getText().toString());
            values.put("TELEFONO",txtTelefono.getText().toString());

            //insercion

            Long id= base_datos.insert(Constantes.NOMBRE_TABLA_USUARIO,null, values);

            base_datos.close();


            if (id>0){
                Toast.makeText(this,"Usuario registrado correctamente", Toast.LENGTH_LONG).show();//confirmacion
                //Invocacion para venta
                intent = new Intent (this, Ventas.class);
                intent.putExtra("parametroDocumento", txtDocumento.getText().toString());
                startActivity(intent);
            }else {
                Toast.makeText(this,"Error al registrar el nuevo usuario",Toast.LENGTH_LONG).show();
            }
        }catch (Exception e){
            Toast.makeText(this,"Error al registrar el usuario",Toast.LENGTH_LONG).show();
        }
    }
}
