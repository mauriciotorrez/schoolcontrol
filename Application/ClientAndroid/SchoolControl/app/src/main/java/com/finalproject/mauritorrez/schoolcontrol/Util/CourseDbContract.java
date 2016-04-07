package com.finalproject.mauritorrez.schoolcontrol.Util;

/**
 * Created by mauri on 3/30/2016.
 */
import android.provider.BaseColumns;

public class CourseDbContract {
    public static class Curso implements BaseColumns {
        public static final String TABLE_NAME = "cursos";

        // ID ya no, ya que esta definido en la interfaz BaseColumns
        // Constantes que representan los atributos de la tabla
        public static final String IDCOURSE_COLUMN = "guidCurso";
        public static final String NOMCURSO_COLUMN = "nomCurso";
        public static final String GRADOCURSO_COLUMN = "gradoCurso";
        public static final String NIVEL_COLUMN = "nivel";
    }

    public static class Estudiante implements BaseColumns {
        public static final String TABLE_NAME = "estudiantes";

        public static final String NUMBER_COLUMN = "number";
        public static final String COURSE_GUID_COLUMN = "curso_guid";
    }
}