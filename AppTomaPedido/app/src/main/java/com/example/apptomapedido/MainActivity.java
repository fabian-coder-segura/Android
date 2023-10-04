package com.example.apptomapedido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import DB.Constantes;

public class MainActivity extends AppCompatActivity {

    EditText txtUser,txtPass;
    Button btnLogin;

    String user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);

        btnLogin = findViewById(R.id.btnLogin);

        //COnsulta preferencia de datos personales
        SharedPreferences preferencia_dat_per = getSharedPreferences(Constantes.NOMBRE_PREFERENCIA,MODE_PRIVATE);
        user = preferencia_dat_per.getString(Constantes.DEFAULT_USERNAME,"Mesero_1");
        pass = preferencia_dat_per.getString(Constantes.DEFAULT_PASSWORD,"12345");


        btnLogin.setOnClickListener(view->{
            if (txtUser.getText().toString().equals(user) && txtPass.getText().toString().equals(pass)) {
                // Las credenciales son correctas, puedes realizar las acciones necesarias
                Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                siguienteActividad(view);
            } else {
                // Las credenciales son incorrectas, muestra un mensaje de error
                Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void siguienteActividad(View view){
        Intent intent=new Intent(this,Mesas.class);
        startActivity(intent);
    }

}