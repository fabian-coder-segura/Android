package com.example.datospersonalesfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datospersonalesfragment.Constantes;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar,btnLimpiar;
    EditText txtNombre,txtApellido,txtCiudad,txtPais,txtEdad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relación con recursos
        btnGuardar = findViewById(R.id.btn_save);
        btnLimpiar = findViewById(R.id.btn_clean);
        txtNombre = findViewById(R.id.txtName);
        txtApellido = findViewById(R.id.txtLastName);
        txtCiudad = findViewById(R.id.txtCity);
        txtPais = findViewById(R.id.txtCountry);
        txtEdad = findViewById(R.id.txtAge);

        //Evento botón guardar
        btnGuardar.setOnClickListener(view->{
            if(txtNombre.getText().toString().trim().equals("") || txtApellido.getText().toString().trim().equals("") || txtCiudad.getText().toString().trim().equals("")
            || txtPais.getText().toString().trim().equals("") || txtEdad.getText().toString().trim().equals(""))
            {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
            }
            else
            {
                guardarPreferencia();
                siguienteActividad(view);

            }
        });

        btnLimpiar.setOnClickListener(v -> {
            txtNombre.setText("");
            txtApellido.setText("");
            txtCiudad.setText("");
            txtPais.setText("");
            txtEdad.setText("");
        });
    }



    private void guardarPreferencia(){
        //Crea o edita contenido de la preferencia

        SharedPreferences preferencia = getSharedPreferences(Constantes.NOMBRE_PREFERENCIA,MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencia.edit();
        editor.putString(Constantes.NOMBRE,txtNombre.getText().toString());
        editor.putString(Constantes.APELLIDO,txtApellido.getText().toString());
        editor.putString(Constantes.CIUDAD,txtCiudad.getText().toString());
        editor.putString(Constantes.PAIS,txtPais.getText().toString());
        editor.putInt(Constantes.EDAD, Integer.parseInt(txtEdad.getText().toString()));
        editor.apply();
    }

    private void siguienteActividad(View view){
        Intent intent=new Intent(this,Resumen.class);
        startActivity(intent);
    }
}