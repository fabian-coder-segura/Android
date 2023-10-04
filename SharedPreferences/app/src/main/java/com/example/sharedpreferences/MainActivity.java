package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtContrasena;
    Button btnSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsuario=findViewById(R.id.txtUsuario);
        txtContrasena=findViewById(R.id.txtContrasena);

        btnSesion=findViewById(R.id.btnSesion);

        btnSesion.setOnClickListener(v -> {



            //Definción de la preferencia
            SharedPreferences preference =getSharedPreferences(Constantes.NOMBRE_PREFERENCIA_SESION,MODE_PRIVATE);
            SharedPreferences.Editor edit=preference.edit();//Editor de la preferencia


            String user=preference.getString("Usuario","null");
            String pass=preference.getString("Contrasena","null");


            //Escribe diferentes datos en la preferencia
            edit.putString(Constantes.USUARIO,""+txtUsuario.getText());
            edit.putString(Constantes.CONTRASENA,""+txtContrasena.getText());

            //Guardar los datos

            edit.apply();

            Log.i("user", preference.getString("Usuario","null"));
            Log.i("user", preference.getString("Contrasena","null"));


        });




    }
}