package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnPlato;
    Button btnBebida;
    Button btnPagar;
    Button btnNuevaCompra;
    TextView txtVlrTotal;
    int cantHamburguesa, cantPasta,cantFrijol, cantCarne, cantEmpanada, cantAgua, cantLimonada, cantGaseosa;
    int ingresoBebid, ingresoPlato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlato = findViewById(R.id.btnPlatos);
        btnBebida = findViewById(R.id.btnBebidas);
        btnPagar = findViewById(R.id.btnPagar);
        btnNuevaCompra = findViewById(R.id.btnNuevaCompra);
        txtVlrTotal = findViewById(R.id.txtVlrTotal);

        //Evento click boton Platos
        btnPlato.setOnClickListener(v-> {
            CartaPlatos(v);
        });

        //Evento click boton Bebidas
        btnBebida.setOnClickListener(v-> {
            CartaBebidas(v);
        });

        //Evento click boton Total a pagar
        btnPagar.setOnClickListener(v-> {
            TotalCompra(v);
        });
    }
    //Metodo que invoca la actividad de la carta de platos
    private void CartaPlatos(View v) {
        ingresoPlato=1;
        Intent intent= new Intent(this, Platos.class);
        //intent.putExtra("plato", btnPlato.getText().toString());
        startActivity(intent);
    }
    //Metodo que invoca la actividad de la carta de bebidas
    private void CartaBebidas(View v) {
        ingresoBebid=1;
        Intent intent= new Intent(this, Bebidas.class);
        intent.putExtra("bebida", btnBebida.getText().toString());
        startActivity(intent);

    }

    //Metodo que invoca la actividad del total de la compra
    private void TotalCompra(View v) {
        //PLATOS
        if (ingresoPlato==1){
        if (getIntent().getStringExtra("hamburguesa").equals("")){
            cantHamburguesa=0;
        } else {
            cantHamburguesa=Integer.parseInt(getIntent().getStringExtra("hamburguesa"));
        }
        if (getIntent().getStringExtra("pasta").equals("")){
            cantPasta=0;
        } else {
            cantPasta=Integer.parseInt(getIntent().getStringExtra("pasta"));
        }
        if (getIntent().getStringExtra("frijol").equals("")){
            cantFrijol=0;
        } else {
            cantFrijol=Integer.parseInt(getIntent().getStringExtra("frijol"));
        }
        if (getIntent().getStringExtra("carne").equals("")){
            cantCarne=0;
        } else {
            cantCarne=Integer.parseInt(getIntent().getStringExtra("carne"));
        }
        if (getIntent().getStringExtra("empanada").equals("")){
            cantEmpanada=0;
        } else {
            cantEmpanada=Integer.parseInt(getIntent().getStringExtra("empanada"));
        }}
        //BEBIDAS
        if (getIntent().getStringExtra("orden").equals("")) {
            if (getIntent().getStringExtra("agua").equals("")) {
                cantAgua = 0;
            } else {
                cantAgua = Integer.parseInt(getIntent().getStringExtra("agua"));
            }
            if (getIntent().getStringExtra("limonada").equals("")) {
                cantLimonada = 0;
            } else {
                cantLimonada = Integer.parseInt(getIntent().getStringExtra("limonada"));
            }
            if (getIntent().getStringExtra("gaseosa").equals("")) {
                cantGaseosa = 0;
            } else {
                cantGaseosa = Integer.parseInt(getIntent().getStringExtra("gaseosa"));
            }
        }
        int totHambur, totPasta, totFrijol, totCarne, totEmpan,totAgua, totLimonada, totGaseosa,sumtot;
        if (cantHamburguesa !=0){
            totHambur = cantHamburguesa*15000;
        }
        else {
            totHambur = 0;
        }
        if (cantPasta !=0){
            totPasta = cantPasta*17000;
        }
        else {
            totPasta = 0;
        }
        if (cantFrijol !=0){
            totFrijol = cantFrijol*12000;
        }
        else {
            totFrijol = 0;
        }
        if (cantCarne !=0){
            totCarne = cantCarne*20000;
        }
        else {
            totCarne = 0;
        }
        if (cantEmpanada !=0){
            totEmpan = cantEmpanada*4500;
        }
        else {
            totEmpan = 0;
        }

        //BEBIDAS
        if (cantAgua !=0){
            totAgua = cantAgua*3500;
        }
        else {
            totAgua = 0;
        }
        if (cantLimonada !=0){
            totLimonada = cantLimonada*4700;
        }
        else {
            totLimonada = 0;
        }
        if (cantGaseosa !=0){
            totGaseosa = cantGaseosa*5100;
        }
        else {
            totGaseosa = 0;
        }
        sumtot = totHambur+totPasta+totFrijol+totCarne+totEmpan+totAgua+totLimonada+totGaseosa;
        txtVlrTotal.setText("Total a Pagar: "+sumtot);


    }
}

***********************************************************************************************
INTENT PLATOS
package com.example.restaurante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Platos extends AppCompatActivity {
    Button btnVolver;
    EditText cantHamburg;
    EditText cantPast;
    EditText cantFrijole;
    EditText cantCarnes;
    EditText cantEmpanadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);
        //conexion con layout
        cantHamburg = findViewById(R.id.cantHambur);
        cantPast = findViewById(R.id.cantPasta);
        cantFrijole = findViewById(R.id.cantFrijol);
        cantCarnes = findViewById(R.id.cantCarne);
        cantEmpanadas = findViewById(R.id.cantEmpanada);

        btnVolver=findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(v->{
            volverMenu(v);

        });
    }
    //Retorna al Main
    private void volverMenu(View view) {
        //Generacion del Intent
        Intent intent=new Intent(this, MainActivity.class);
        //envio parametros
        intent.putExtra("hamburguesa", cantHamburg.getText().toString());
        intent.putExtra("pasta", cantPast.getText().toString());
        intent.putExtra("frijol", cantFrijole.getText().toString());
        intent.putExtra("carne", cantCarnes.getText().toString());
        intent.putExtra("empanada", cantEmpanadas.getText().toString());
        intent.putExtra("orden", "Plato");
        //Inicio de la actividad
        startActivity(intent);
    }
}

*****************************************************************************
NOMINA CON PREFERENCIAS
package com.example.ejercicioaplicacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button btnCalcularDiaria;
    Button btnNomMensual;
    Button btnBorrarNomina;
    EditText NumHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creacion de la preferencia
        SharedPreferences preferencia=getSharedPreferences("Nomina",MODE_PRIVATE);

        //Enlace recursos de interfaz de usuario con las variables
        NumHora = findViewById(R.id.txtNumHora);
        btnCalcularDiaria = findViewById(R.id.btnCalcular);
        btnNomMensual = findViewById(R.id.btnNomMensual);
        btnBorrarNomina = findViewById(R.id.btnBorrarNomina);

        btnCalcularDiaria.setOnClickListener(view -> {
        int numHours = Integer.parseInt(NumHora.getText().toString());
        int numTotalHoras=numHours*25;
        double base=(1500000/30)/24;
        double prestaSocial=(base*numTotalHoras)*0.045;
        float nomina= (float) ((base*numTotalHoras)-prestaSocial);

        //Obteniendo el contenido de la preferencia
        int HoraPrefe= preferencia.getInt("Horas",0);
        float NominaPrefe= preferencia.getFloat("TotalNomina",0);


        //escritura en la preferencia
        SharedPreferences.Editor editor= preferencia.edit();
        editor.putInt("Horas", HoraPrefe+numHours);
        editor.putFloat("TotalNomina", NominaPrefe+nomina);
        editor.apply();
        Toast.makeText(this, "La nomina diaria es: "+ String.valueOf(NominaPrefe), Toast.LENGTH_LONG).show();

        });
        btnNomMensual.setOnClickListener(view -> {
            int HorasMes= preferencia.getInt("Horas",0);
            float NominaMes= preferencia.getFloat("TotalNomina",0);
            Toast.makeText(this, "La nomina mensual es $"+ String.valueOf(NominaMes)+" con " +String.valueOf(HorasMes) +" horas.", Toast.LENGTH_LONG).show();
        });

        btnBorrarNomina.setOnClickListener(view -> {
            //escritura en la preferencia
            SharedPreferences.Editor editor= preferencia.edit();
            editor.putInt("Horas", 0);
            editor.putFloat("TotalNomina", 0);
            editor.apply();
            Toast.makeText(this, "La nomina se ha reiniciado", Toast.LENGTH_LONG).show();
        });
        }
}

************************
LAYOUT
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".Bebidas">

    <ImageView
        android:id="@+id/imgAgua"
        android:layout_width="144dp"
        android:layout_height="130dp"
        app:srcCompat="@drawable/agua"
        android:layout_margin="20dp"/>

    <EditText
        android:id="@+id/cantAgua"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="50dp"
        android:layout_marginTop="50dp"
        android:layout_marginEnd="50dp"
        android:layout_marginBottom="50dp"
        android:layout_toRightOf="@+id/imgAgua"
        android:ems="10"
        android:hint="Cantidad"
        android:inputType="number"/>

    <ImageView
        android:id="@+id/imgLimonada"
        android:layout_width="142dp"
        android:layout_height="130dp"
        android:layout_below="@+id/imgAgua"
        app:srcCompat="@drawable/limonadaa"
        android:layout_margin="20dp"/>

    <EditText
        android:id="@+id/cantLimonada"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/imgAgua"
        android:layout_marginStart="50dp"
        android:layout_marginTop="50dp"
        android:layout_marginEnd="50dp"
        android:layout_marginBottom="50dp"
        android:layout_toRightOf="@+id/imgAgua"
        android:ems="10"
        android:hint="Cantidad"
        android:inputType="number" />

    <ImageView
        android:id="@+id/imgGaseosa"
        android:layout_width="140dp"
        android:layout_height="130dp"
        android:layout_below="@+id/imgLimonada"
        app:srcCompat="@drawable/gaseosa"
        android:layout_margin="20dp"
        />

    <EditText
        android:id="@+id/cantGaseosa"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/cantLimonada"
        android:layout_marginStart="50dp"
        android:layout_marginTop="50dp"
        android:layout_marginEnd="50dp"
        android:layout_marginBottom="50dp"
        android:layout_toRightOf="@+id/imgGaseosa"
        android:ems="10"
        android:hint="Cantidad"
        android:inputType="number" />

    <Button
        android:id="@+id/btnVolver"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/imgGaseosa"
        android:text="Hacer pedido"
        android:layout_margin="20dp"
        />
</RelativeLayout>

***********************************************
MAIN ACTIVITY PROYECTO W.
package com.example.colegionuevofuturo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String error1 = "No se han Ingresado todos los datos requeridos.";
    String error2 = "Los datos Ingresados Son Incorrectos.";
    EditText iniciousuario, iniciocontraseña;
    Button btninicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciousuario = findViewById(R.id.usuario);
        iniciocontraseña = findViewById(R.id.contraseña);
        btninicio = findViewById(R.id.btniniciosesion);

        btninicio.setOnClickListener(view -> {
            if (iniciousuario.getText().toString().equals("") || iniciocontraseña.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(),error1,Toast.LENGTH_LONG).show();
            } else if (iniciousuario.getText().toString().equals("administrador") && iniciocontraseña.getText().toString().equals("nuevofuturo1")){
                Actividad1();
            } else {
                Toast.makeText(getApplicationContext(),error2,Toast.LENGTH_LONG).show();
            };
        });
    }
    public void Actividad1(){
        Intent intent = new Intent(this, Actividad1.class);
        startActivity(intent);
    }
}
************************************
ACTIVIDAD 1 PROYECTO W.
package com.example.colegionuevofuturo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Actividad1 extends AppCompatActivity {

    Button btnRegistrar1, btnRegistrar2, btnRegistrar3, btncerrar;
    TextView estudiante1, estudiante2, estudiante3;
    String nestudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad1);

        estudiante1 = findViewById(R.id.est1);
        estudiante2 = findViewById(R.id.est2);
        estudiante3 = findViewById(R.id.est3);
        
        btnRegistrar1 = findViewById(R.id.btnregistros1);
        btnRegistrar2 = findViewById(R.id.btnregistros2);
        btnRegistrar3 = findViewById(R.id.btnregistros3);
        btncerrar = findViewById(R.id.btncerrarsesion);

        btnRegistrar1.setOnClickListener(view -> {
            Actividad2(1);
        });
        btnRegistrar2.setOnClickListener(view -> {
            Actividad2(2);
        });
        btnRegistrar3.setOnClickListener(view -> {
            Actividad2(3);
        });
        btncerrar.setOnClickListener(view -> {
            ActividadPrincipal();
        });

    }
    public void Actividad2(int a){
        Intent intent = new Intent(this, Actividad2.class);
        intent.putExtra("nestud", "Estudiante Número "+a);
        startActivity(intent);
    }

    public void ActividadPrincipal() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
******************************
ACTIVIDAD 2 PROYECTO W.
package com.example.colegionuevofuturo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Actividad2 extends AppCompatActivity {

    String error = "Alguna nota ingresada no es correcta o Hacen falta Notas";
    int v=0, cn=0, m=0, promedio=0;
    Button btncalcular, btnlista;
    TextView numeroestudiante;
    EditText not1, not2, not3, not4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        numeroestudiante = findViewById(R.id.numeroestudiante);
        numeroestudiante.setText(getIntent().getStringExtra("nestud"));

        not1 = findViewById(R.id.nota1);
        not2 = findViewById(R.id.nota2);
        not3 = findViewById(R.id.nota3);
        not4 = findViewById(R.id.nota4);

        btncalcular = findViewById(R.id.calculo);

        btncalcular.setOnClickListener(view ->{
            if (not1.getText().toString().equals("")){
                v++;
            } else if (Integer.parseInt(not1.getText().toString())>10){
                m++;
            } else {
                promedio = promedio+Integer.parseInt(not1.getText().toString());
                cn++;
            }
            if (not2.getText().toString().equals("")){
                v++;
            } else if (Integer.parseInt(not2.getText().toString())>10){
                m++;
            } else {
                promedio = promedio+Integer.parseInt(not2.getText().toString());
                cn++;
            }
            if (not3.getText().toString().equals("")){
                v++;
            } else if (Integer.parseInt(not3.getText().toString())>10){
                m++;
            } else {
                promedio = promedio+Integer.parseInt(not3.getText().toString());
                cn++;
            }
            if (not4.getText().toString().equals("")){
                v++;
            } else if (Integer.parseInt(not4.getText().toString())>10){
                m++;
            } else {
                promedio = promedio+Integer.parseInt(not4.getText().toString());
                cn++;
            }
            if (v>2 || m>0){
                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                promedio = 0;
                cn = 0;
                v=0;
                m=0;
            } else {
                promedio = promedio/cn;
                Toast.makeText(getApplicationContext(),"El promedio de notas del "+numeroestudiante.getText()+" es :"+promedio,Toast.LENGTH_LONG).show();
                promedio = 0;
                cn = 0;
            }
        });

        btnlista = findViewById(R.id.listaestudiantes);

        btnlista.setOnClickListener(view -> {
            Actividad1();
        });
    }

    public void Actividad1(){
        Intent intent = new Intent(this, Actividad1.class);
        startActivity(intent);
    }
}
