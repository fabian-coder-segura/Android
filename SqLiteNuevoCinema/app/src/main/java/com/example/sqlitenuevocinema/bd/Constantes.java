package com.example.sqlitenuevocinema.bd;

public class Constantes {
    public static final String NOMBRE_TABLA_USUARIO="USUARIOS";
    public static final String NOMBRE_TABLA_VENTA="VENTAS";
    public static final String NOMBRE_BD="BD_NUEVO_CINEMA.bd";
    public static final int VERSION_BD=1;

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE USUARIOS (DOCUMENTO INTEGER PRIMARY KEY,  NOMBRE TEXT NOT NULL,  TELEFONO TEXT NOT NULL)";

    public static final String CREAR_TABLA_VENTAS="CREATE TABLE VENTAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "DOCUMENTO INTEGER,  ASIENTOS INTEGER NOT NULL,  TOTAL INTEGER NOT NULL, "+
            "FOREIGN KEY (DOCUMENTO) REFERENCES USUARIOS(DOCUMENTO))";



}
