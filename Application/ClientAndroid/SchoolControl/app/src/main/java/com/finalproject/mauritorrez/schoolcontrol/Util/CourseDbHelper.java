package com.finalproject.mauritorrez.schoolcontrol.Util;

/**
 * Created by mauri on 3/30/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import static com.finalproject.mauritorrez.findjob.Data.JobPostDbContract.*;


public class CourseDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "schoolControl.db";
    private static int VERSION = 1;
    public CourseDbHelper(Context context) {
        // Context: Contexto de la aplicacion
        // DB_NAME: Nombre de la base de datos
        // Cursor Factory: Por defecto en null
        // Numero de version
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE NombreTabla (p_key TIPO PRIMARY KEY ON CONFLICT REPLACE, attr TIPO, attr TIPO)
        String sqlCreateJobPost = "CREATE TABLE " + CourseDbContract.Curso.TABLE_NAME + "(" +
                CourseDbContract.Curso._ID + " INTEGER PRIMARY KEY ON CONFLICT REPLACE," +
                CourseDbContract.Curso.NOMCURSO_COLUMN + " TEXT NOT NULL," +
                CourseDbContract.Curso.GRADOCURSO_COLUMN + " TEXT NOT NULL," +
                CourseDbContract.Curso.NIVEL_COLUMN + " TEXT NOT NULL);";
/*
        String sqlCreateContact = "CREATE TABLE " + Contact.TABLE_NAME + "(" +
                Contact._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contact.NUMBER_COLUMN + " TEXT NOT NULL," +
                Contact.JOB_POST_ID_COLUMN + " INTEGER NOT NULL," +
                "FOREIGN KEY(" + Contact.JOB_POST_ID_COLUMN + ")" +
                " REFERENCES " + JobPost.TABLE_NAME + "(" + JobPost._ID + ")," +
                "UNIQUE (" + Contact.NUMBER_COLUMN + "," + Contact.JOB_POST_ID_COLUMN
                + ") ON CONFLICT REPLACE);";
*/
        // Ejecuta el SQL
        db.execSQL(sqlCreateJobPost);
        //db.execSQL(sqlCreateContact);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + CourseDbContract.Curso.TABLE_NAME);
        db.execSQL("DROP TABLE " + CourseDbContract.Estudiante.TABLE_NAME);
        onCreate(db);
    }
}

