package com.example.sqlitenuevocinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitenuevocinema.bd.Constantes;
import com.example.sqlitenuevocinema.bd.DbHelper;

public class MainActivity extends AppCompatActivity {

    EditText txtDocumento;
    Button btnInicioSesion;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDocumento=findViewById(R.id.txtDocumento);
        btnInicioSesion=findViewById(R.id.btnIniciarsesion);

        btnInicioSesion.setOnClickListener(view -> {
            iniciarSesion();
        });

    }

    //Valida existencia de usuario
    private void iniciarSesion() {
        try{
            //Conexió
            DbHelper helper = new DbHelper(this);
            //Objeto de acceso a la BD
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            //Where
            String[] parametrosConsulta={txtDocumento.getText().toString()};
            //Select
            String[]  camposConsulta = {"DOCUMENTO"};
            //Cursosr con resultados
            Cursor cursor = base_datos.query(Constantes.NOMBRE_TABLA_USUARIO,camposConsulta,"DOCUMENTO"+"=?",parametrosConsulta,null,null,null);

            cursor.moveToFirst();

            //Valida resultado de cursor
            if (cursor!=null && cursor.getCount()>0){

                Toast.makeText(this, "Bienvenido " + cursor.getString(0), Toast.LENGTH_SHORT).show();
                //Invocación para la venta
                intent = new Intent(this, Venta.class);
                intent.putExtra("parametroDocumento", txtDocumento.getText().toString());
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Documento inexistente" , Toast.LENGTH_SHORT).show();

                //Invocación para lel registro
                intent = new Intent(this,Registro.class);
                startActivity(intent);
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}