package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual, btnBorrar;
    TextView Resultado;
    String T1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.num1);
        btn2 = findViewById(R.id.num2);
        btn3 = findViewById(R.id.num3);
        btn4 = findViewById(R.id.num4);
        btn5 = findViewById(R.id.num5);
        btn6 = findViewById(R.id.num6);
        btn7 = findViewById(R.id.num7);
        btn8 = findViewById(R.id.num8);
        btn9 = findViewById(R.id.num9);
        btn0 = findViewById(R.id.num0);

        btnSumar = findViewById(R.id.sumar);
        btnRestar = findViewById(R.id.restar);
        btnMultiplicar = findViewById(R.id.multiplicar);
        btnDividir = findViewById(R.id.dividir);
        btnIgual = findViewById(R.id.igual);
        btnBorrar = findViewById(R.id.borrar);

        Resultado = findViewById(R.id.resultado);

        btn1.setOnClickListener(view -> {
            Resultado.setText("1");
        });
        btn2.setOnClickListener(view -> {
            Resultado.setText("2");
        });
        btn3.setOnClickListener(view -> {
            Resultado.setText("3");
        });
        btn4.setOnClickListener(view -> {
            Resultado.setText("4");
        });
        btn5.setOnClickListener(view -> {
            Resultado.setText("5");
        });
        btn6.setOnClickListener(view -> {
            Resultado.setText("6");
        });
        btn7.setOnClickListener(view -> {
            Resultado.setText("7");
        });
        btn8.setOnClickListener(view -> {
            Resultado.setText("8");
        });
        btn9.setOnClickListener(view -> {
            Resultado.setText("9");
        });
        btn0.setOnClickListener(view -> {
            Resultado.setText("0");
        });

        if (Resultado != null){
            T1 = Resultado.getText().toString();

        }
    }

}