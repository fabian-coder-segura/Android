package com.example.calculararea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class CalcularArea extends AppCompatActivity {

    float base, altura;
    Button btnRec, btnTri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_area);

        //relacion con elementos layaut
        btnRec = findViewById(R.id.btnRectangulo);
        btnTri = findViewById(R.id.btnTriangulo);

        //consulta intent
        base = Float.parseFloat(getIntent().getStringExtra("base"));
        altura = Float.parseFloat(getIntent().getStringExtra("altura"));

        //Clcik

        btnRec.setOnClickListener(v->{
            Toast.makeText(this,"Área Rectangulo: " + (base*altura)
            ,Toast.LENGTH_LONG).show();
        });

        btnTri.setOnClickListener(v->{
            Toast.makeText(this,"Área Triangulo: " + (base*altura)/2
                    ,Toast.LENGTH_LONG).show();
        });
    }
}