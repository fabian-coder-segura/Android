package com.example.sqlitenuevocinema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlitenuevocinema.bd.Constantes;
import com.example.sqlitenuevocinema.bd.DbHelper;

public class Registro extends AppCompatActivity {

    EditText txtdocumento,txtnombre,txttelefono;
    Button btnRegistrar;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtdocumento=findViewById(R.id.txtNuevoDocumento);
        txtnombre=findViewById(R.id.txtNuevoNombre);
        txttelefono=findViewById(R.id.txtNuevoTelefono);

        btnRegistrar=findViewById(R.id.btnNuevoRegistro);

        btnRegistrar.setOnClickListener(view -> {
            if (txtdocumento.getText().toString().equals("") || txtnombre.getText().toString().equals("") || txttelefono.getText().toString().equals("")){
                Toast.makeText(this, "Los campos son obligatorios", Toast.LENGTH_SHORT).show();
            }
            else {
                registroUsuario();
            }
        });
    }

    //Registro de usuario
    private void registroUsuario() {
        try{
            //Conexión
            DbHelper helper = new DbHelper(this);
            //Objeto de acceso a la BD
            SQLiteDatabase base_datos = helper.getWritableDatabase();
            //Valores
            ContentValues values = new ContentValues();
            values.put("DOCUMENTO",Integer.parseInt(txtdocumento.getText().toString()));
            values.put("NOMBRE",txtnombre.getText().toString());
            values.put("TELEFONO",txttelefono.getText().toString());

            //Inserción
            long id = base_datos.insert(Constantes.NOMBRE_TABLA_USUARIO,null,values);

            base_datos.close();

            if (id>0){
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();//Confirmación
                //Invocación para la venta
                intent = new Intent(this,Venta.class);
                intent.putExtra("parametroDocumento",txtdocumento.getText().toString());
                startActivity(intent);;
            }
            else{
                Toast.makeText(this, "Error al registrar el nuevo usuario", Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        }
    }
}