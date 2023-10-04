package com.example.exparcial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnIngresar;
    TextView txtUsuario;
    TextView txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnIngresar = findViewById(R.id.btnIngresar);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtClave = findViewById(R.id.txtClave);

        //Evento click boton Ingresar
        btnIngresar.setOnClickListener(view -> {
            if (txtUsuario.getText().toString().equals("administrador") && txtClave.getText().toString().equals("v3nt4s22")){
                Ventas(view);
            } else {
                Toast.makeText(this,"Datos de ingreso incorrectos.",Toast.LENGTH_LONG).show();
            };
        });

    }
    public void Ventas(View view){
        Intent intent= new Intent(this, com.example.exparcial.Ventas.class);
        startActivity(intent);
    }
}
