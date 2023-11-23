package com.example.sqlliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import BD.Constantes;
import BD.DBHelper;

public class MainActivity extends AppCompatActivity {

    //Recursos del layout
    EditText txtEmail;
    Button btnIniciarSesion, btnNuevoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relación con elementos del layout
        txtEmail = findViewById(R.id.txtEmail_InicioSesion);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnNuevoUsuario = findViewById(R.id.btnNuevoUsuario);

        btnIniciarSesion.setOnClickListener(v->{
            consultarUsuario();
        });

        btnNuevoUsuario.setOnClickListener(v->{
            startActivity(new Intent(this,Registro.class));
        });
    }

    //Método para la consulta de usuarios
    private void consultarUsuario(){
        try{
            //Conexión
            DBHelper helper = new DBHelper(this);
            //Objeto para la lectura en la BD
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            //Arreglo con las condiciones de la consulta -> WHERE
            String[] parametrosCOnsulta = {txtEmail.getText().toString()};
            //Arreglo con los campos de la consulta -> SELECT
            String[] camposConsulta = {"NOMBRE"};
            //Cursor con el resultado de la consulta
            Cursor cursor = base_datos.query(Constantes.TABLA_USURIO,
                    camposConsulta,"EMAIL"+"=?",
                    parametrosCOnsulta,null,null,null);
            cursor.moveToFirst();//Mueve el cursos al primer registro
            Toast.makeText(this,"Bienvenido: " + cursor.getString(0),
                    Toast.LENGTH_LONG).show();
        }
        catch  (Exception e){
            Toast.makeText(this,"Error al consultar el usuario",Toast.LENGTH_LONG).show();
            txtEmail.setText("");//Limppia camo email después del error
        }
    }
}