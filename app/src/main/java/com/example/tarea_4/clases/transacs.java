package com.example.tarea_4.clases;

public class transacs {
    public static final String dbName = "tarea_4";
    public static final String tblName = "fotos";

    public static final String id = "id";
    public static final String img = "img";
    public static final String name = "name";
    public static final String description = "description";

    public static final String crearTblFoto = "CREATE TABLE fotos (id INTEGER PRIMARY KEY AUTOINCREMENT" +
                                              ", img BLOB, name TEXT, description TEXT)";

    public static final String getFotos = "SELECT * FROM " + transacs.tblName;

    public static final String dropFotos = "DROP TABLE IF EXISTS fotos";
}
