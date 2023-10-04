package com.example.sqlliteproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
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
    ArrayList<String> listaInformacion;//Lista los nombres de los usuarios

    ArrayList<Usuarios> listaUsuarios;//Lista objetos de tipo Usuario
    ArrayAdapter adapter;//Adpater para manejo de la lista
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

        //Inicializa listados
        listaInformacion = new ArrayList<>();
        listaUsuarios = new ArrayList<>();
        consultarUsuariosExistentes();//Carga contenido a los arreglos

        //Adaptapter para la visualización del ListView
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,listaInformacion);
        lvUsuarios.setAdapter(adapter);//Relaciona el adapter con el ListView

        btnNuevousuario.setOnClickListener(v->{
            registrarUsuarioBD();
            consultarUsuariosExistentes();//Actualiza listado
            limiarCampos();//Limpia campos de interfaz
        });

        btnRegresar.setOnClickListener(v->{
            startActivity(new Intent(this,MainActivity.class));
        });

        //Click del ListView
        lvUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Invocación del método que visualiza alerta enviado ID del usuario a eliminar
                confirmarEliminacion("Dese eliminar el usuario: " +
                        listaUsuarios.get(position).getNombre(),
                        listaUsuarios.get(position).getId(),position);
            }
        });
    }

    //Genera un AlertDialog con la confirmación de eliminación
    private void confirmarEliminacion(String msn, long idObjetoEliminar,int position){
        AlertDialog.Builder mensaje = new AlertDialog.Builder(this);
        mensaje.setMessage(msn)
                .setTitle("¿Confirma eliminar?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eliminarUsuario(idObjetoEliminar);//Elimina el usuario

                        //Elimina el objeto de los listados
                        listaUsuarios.remove(position);
                        listaInformacion.remove(position);
                        adapter.notifyDataSetChanged();//Actualiza referencia del adapter para visualización de elementos

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();//Oculta la alerta
                    }
                });
        mensaje.create().show();//Hace visible el mensaje
    }

    private void eliminarUsuario(long idUsuario){
        try{
            //Conexión
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase base_datos = helper.getWritableDatabase();//Objeto para escritura
            String[] parametrosEliminacion = {String.valueOf(idUsuario)};//Parámetro de eliminación
            base_datos.delete(Constantes.TABLA_USURIO,"ID=?",//Sentencia delete
                    parametrosEliminacion);
            base_datos.close();//Cierra la conexión
            Toast.makeText(this,"Información de usuario eliminada",Toast.LENGTH_LONG).show();
        }
        catch(Exception e){
            Toast.makeText(this,"ERROR:  No fue posible eliminar la información de los usuarios",Toast.LENGTH_LONG).show();
        }
    }

    //Limpia contenido de los textos en la interfaz
    private void limiarCampos(){
        txtEmail.setText("");
        txtNombre.setText("");
    }


    //Consulta usuarios existentes en la BD
    private void consultarUsuariosExistentes(){
        try{
            //Limpia contenido de los arreglos para luego agregarles información
            listaUsuarios.clear();
            listaInformacion.clear();

            //Conexión
            DBHelper helper = new DBHelper(this);
            //Objeto para manipulación de datos
            SQLiteDatabase base_datos = helper.getReadableDatabase();
            //Cursosr con resultados
            Cursor cursor = base_datos.rawQuery("SELECT ID,NOMBRE,EMAIL FROM " +
                    Constantes.TABLA_USURIO + " ORDER BY NOMBRE",null);

            Usuarios usuario;
            while (cursor.moveToNext()){
                //Instancia la clase Usuarios
                usuario = new Usuarios(cursor.getLong(0),
                        cursor.getString(1), cursor.getString(2));

                listaUsuarios.add(usuario);//Incluye objeto en la lista
                listaInformacion.add(usuario.getNombre() +
                        " (" + usuario.getEmail() + ")");//INcluye String para visualización
            }

            if(!listaUsuarios.isEmpty() && adapter!=null){//Cuando existan elementos en la lista se actualiza el ListView
                adapter.notifyDataSetChanged();//Actualiza referencia del adapter para visualización de elementos
            }


        }
        catch (Exception e){
            Toast.makeText(this,"ERROR:  No fue posible consultar la información de los usuarios",Toast.LENGTH_LONG).show();
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