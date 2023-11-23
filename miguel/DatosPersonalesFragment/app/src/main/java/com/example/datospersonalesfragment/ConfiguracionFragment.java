package com.example.datospersonalesfragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.PhantomReference;

public class ConfiguracionFragment extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        visualizarMenu();

        //Relaciona el fragmento de configuración a partir de la clase estática creada
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Fragment())
                .commit();
    }


    //Habilita la visualización de la barra
    private void visualizarMenu() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    //Clase estática que contiene relación al fragmento de preferencia
    public static class Fragment extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //Relacione coo fragmento el XML de configuración
            addPreferencesFromResource(R.xml.configuracion);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Retorno a la actividad anterior
        if (item.getItemId()==android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
