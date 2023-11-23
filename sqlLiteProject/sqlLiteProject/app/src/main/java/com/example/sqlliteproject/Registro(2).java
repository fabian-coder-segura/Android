package com.example.sqlliteproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import BD.Constantes;
import BD.DBHelper;

public class Registro extends AppCompatActivity {
    EditText txtNombre,txtEmail;
    Button btnNuevousuario,btnRegresar;
    ListView lvUsuarios;
    ArrayList<String> listaInformacion;
    ArrayList<Usuarios> listaUsuarios;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Relación con elementos del layout
        txtNombre = findViewById(R.id.txtNombre_NuevoUsuario);
        txtEmail = findViewById(R.id.txtEmail_NuevoUsuario);
        btnNuevousuario = findViewById(R.id.btnCrearUsuario);
        btnRegresar = findViewById(R.id.btnRegresar);
        lvUsuarios = findViewById(R.id.lvUsuarios);

        listaInformacion = new ArrayList<>();
        listaUsuarios = new ArrayList<>();

        consultaUsuariosExistentes();





        btnNuevousuario.setOnClickListener(v->{
            registrarUsuarioBD();
            consultaUsuariosExistentes();
        });

        btnRegresar.setOnClickListener(v->{
            startActivity(new Intent(this,MainActivity.class));
        });

        // click del listView
        lvUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //invocacion del metodo que visualiza alerta enviando ID del usuarui a eliminar.
                confirmarEliminacion("Desea eliminar el usuario: " +
                        listaUsuarios.get(position).getNombre(),
                        listaUsuarios.get(position).getId(), position);
                        consultaUsuariosExistentes();

            }
        });
    }

    // genera un alertDialog
    private void confirmarEliminacion(String msn, long idObjetoEliminar, int position){
        AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
        mensaje.setMessage(msn)
                .setTitle("¿Confirma eliminar?")
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarUsuario(idObjetoEliminar);
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        mensaje.create().show();
    }

    private void eliminarUsuario(long idUsuario){
        try {
            //conexion
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase base_datos = helper.getWritableDatabase();
            String[] parametrosEliminacion = {String.valueOf(idUsuario)};
            base_datos.delete(Constantes.TABLA_USURIO, "ID=?",
                    parametrosEliminacion);
            base_datos.close();
            consultaUsuariosExistentes();
            Toast.makeText(this,"Informacion de usuario eliminada",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"ERROR:  No fue posible eliminar la información del usuario",Toast.LENGTH_LONG).show();
        }

    }

    //consulta
    private void consultaUsuariosExistentes() {
        try {
            listaUsuarios.clear();
            listaInformacion.clear();
            //conexion
            DBHelper helper = new DBHelper(this);
            //Objeto para manipulacion de datos
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            //cursor con resultados
            Cursor cursor = base_datos.rawQuery("SELECT ID, NOMBRE, EMAIL FROM " +
                    Constantes.TABLA_USURIO + " ORDER BY NOMBRE", null);

            Usuarios usuario;
            while (cursor.moveToNext()){
                //instanciado la clase usuario
                usuario = new Usuarios(cursor.getLong(0),
                        cursor.getString(1), cursor.getString(2));

                listaUsuarios.add(usuario);//incluye el objeto en la lista
                listaInformacion.add(usuario.getNombre() + " (" + usuario.getEmail() + " )");
            }

            //Adapter para la visualizacion del listView
            ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_list_item_1,listaInformacion);
            lvUsuarios.setAdapter(adapter);

        } catch (Exception e) {
            Toast.makeText(this,"ERROR:  No fue posible consultar la información",Toast.LENGTH_LONG).show();
        }
    }

    //Método para la creación de usuarios
    private void registrarUsuarioBD(){
        try{
            //Conexion
            DBHelper helper = new DBHelper(this);
            //Objeto para escritura
            SQLiteDatabase base_datos = helper.getWritableDatabase();

            if(!consultarDuplicidad(txtEmail.getText().toString())) {
                //inserción clave-valor
                ContentValues values = new ContentValues();
                values.put("NOMBRE", txtNombre.getText().toString());
                values.put("EMAIL", txtEmail.getText().toString());

                //Inserción en la BD, retorna el ID
                long id = base_datos.insert(Constantes.TABLA_USURIO,
                        null, values);

                if (id > 0) {
                    Toast.makeText(this, "USUARIO CREADO", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Error al crear el usuario", Toast.LENGTH_LONG).show();
                }
            }
            else {
                Toast.makeText(this, "El correo electrónico se encuentra registrado para otro usuario", Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){
            Toast.makeText(this,"ERROR:  No fue posible registrar la información",Toast.LENGTH_LONG).show();
        }
    }



    private boolean consultarDuplicidad(String email){
        try{
            //Conexion
            DBHelper helper = new DBHelper(this);
            //Objeto para lectura
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            //Arreglo con parámetros
            String[] parametrosConsulta = {email};
            //Sentencia SELECT
            Cursor cursor = base_datos.rawQuery("SELECT NOMBRE FROM " +
                    Constantes.TABLA_USURIO + " WHERE EMAIL"+"=?",parametrosConsulta);
            if(cursor.getCount()>0) {
                return true;
            }
            else{
                return false;
            }

        }
        catch(Exception e){
            return false;
        }
    }

    private void registrarUsuarioBD_2(String nombre){
        try {
            //Conexion
            DBHelper helper = new DBHelper(this);
            //Objeto para escritura
            SQLiteDatabase base_datos = helper.getWritableDatabase();
            //inserción
            base_datos.execSQL("INSERT INTO " + Constantes.TABLA_USURIO +
                    " (NOMBRE,EMAIL) " +
                    " VALUES ("+ nombre +",'pedro@gmail.com')"
            );

            base_datos.close();
            Toast.makeText(this, "USUARIO CREADO", Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this,"ERROR:  No fue posible registrar la información",Toast.LENGTH_LONG).show();
        }
    }
}