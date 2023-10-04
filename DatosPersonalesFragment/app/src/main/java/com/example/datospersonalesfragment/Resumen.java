package com.example.datospersonalesfragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class Resumen extends AppCompatActivity {

    String nombre, apellido,ciudad,pais,edad;
    TextView txtNombre,txtLugar;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        //Relación de recursos
        btnVolver=findViewById(R.id.btnVolver);
        txtNombre = findViewById(R.id.txtResumenNombre);
        txtLugar=findViewById(R.id.txtResumenCiudad);


        btnVolver.setOnClickListener(v->{
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        });

    }

    //Menú de la parte superior asociado al recurso
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Evento del ítem que integra el recurso Menú
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Conectamos con el layout de configuración
        if(item.getItemId()==R.id.menudID)
        {
            //Inicia como actividad la clase ConfiguracionFragment
            startActivity(new Intent(this, ConfiguracionFragment.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Consulta preferencia de configuración general
        SharedPreferences preferencia_global = PreferenceManager.getDefaultSharedPreferences(this);
        String size = preferencia_global.getString("nombre_preferencia","N/A");
        if(!size.equals("N/A")){
            switch (size){

                default: txtNombre.setTextSize(12);
                    txtLugar.setTextSize(12);
                    break;
            }
        }

        //COnsulta preferencia de datos personales
        SharedPreferences preferencia_dat_per = getSharedPreferences(Constantes.NOMBRE_PREFERENCIA,MODE_PRIVATE);
        nombre=preferencia_dat_per.getString(Constantes.NOMBRE,"N/A");
        apellido =preferencia_dat_per.getString(Constantes.APELLIDO,"N/A");
        edad=""+preferencia_dat_per.getInt(Constantes.EDAD,0);
        ciudad=preferencia_dat_per.getString(Constantes.CIUDAD,"N/A");
        pais=preferencia_dat_per.getString(Constantes.PAIS,"N/A");

        //Asigna nombres, apellidos y edad al texto
        if(!nombre.equals("N/A") && !apellido.equals("N/A") && !edad.equals("N/A")){
            txtNombre.setText(nombre+" "+apellido+" "+edad);
        }

        //Asigna lugar al texto
        if(!ciudad.equals("N/A") && !pais.equals("N/A")){
            txtLugar.setText(ciudad+" "+pais);
        }

    }
}