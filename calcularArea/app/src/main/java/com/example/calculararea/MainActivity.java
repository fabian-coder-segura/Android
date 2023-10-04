package com.example.calculararea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btncalcular;
    EditText txtbase, txtaltura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relacion con layaut

        txtaltura = findViewById(R.id.txtaltura);
        txtbase = findViewById(R.id.txtbase);
        btncalcular = findViewById(R.id.btncalcular);

        //evento calcular
        btncalcular.setOnClickListener(v->{
            Intent intent = new Intent(this,CalcularArea.class);
            intent.putExtra("base",txtbase.getText().toString());
            intent.putExtra("altura",txtaltura.getText().toString());

            startActivity(intent);
        });
    }
}