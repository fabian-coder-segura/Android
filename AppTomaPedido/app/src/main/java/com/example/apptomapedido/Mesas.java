package com.example.apptomapedido;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class Mesas extends AppCompatActivity {

    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesas);
        next = findViewById(R.id.button);

        next.setOnClickListener(v->{
            startActivity(new Intent(this,Productos.class));
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
}