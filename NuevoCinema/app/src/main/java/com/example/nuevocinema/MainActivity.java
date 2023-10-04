package com.example.nuevocinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DB.Constantes;
import DB.DBHelper;

public class MainActivity extends AppCompatActivity {

    EditText txtDocumento;

    Button btnLogin;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDocumento =findViewById(R.id.TxtDocumento);
        btnLogin = findViewById(R.id.btnLogin);

    }

    private void iniciarSesion(){
        try {
            //conexion
            DBHelper helper = new DBHelper(this);
            //objeto de acceso a BD
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            //where
            String[] parametrosConsulta = {txtDocumento.getText().toString()};
            //select
            String[] campoConsulta  ={"DOCUMENTO"};
            //cursor con resultados
            Cursor cursor = base_datos.query(Constantes.NOMBRE_TABLA_USUARIO, campoConsulta,"DOCUMENTO"+"=?", parametrosConsulta,null, null,null);

            cursor.moveToFirst();

            //valida resultado del cursor
            if (cursor!= null && cursor.getCount()>0){

                Toast.makeText(this,"Bienvenido " + cursor.getString(0), Toast.LENGTH_LONG).show();
                //invocacion para la venta
               intent = new Intent(this,Ventas.class);
               intent.putExtra("parametroDocumento", txtDocumento.getText().toString());
               startActivity(intent);
            }

        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}