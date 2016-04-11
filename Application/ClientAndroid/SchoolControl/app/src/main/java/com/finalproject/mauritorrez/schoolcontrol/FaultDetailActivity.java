package com.finalproject.mauritorrez.schoolcontrol;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.finalproject.mauritorrez.schoolcontrol.DTO.CourseDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.FaultDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.StudentDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.UserDTO;

import java.util.ArrayList;

/**
 * Created by mauri on 4/7/2016.
 */
public class FaultDetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = FaultDetailActivity.class.getSimpleName();

    public FaultDTO curentFault ;
    public UserDTO resultUser;
    public CourseDTO resultCourse;
    public StudentDTO resultStudent;

    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String CURRENT_FAULT = "CURRENT_FAULT";
    public static final String RESULT_COURSE = "RESULT_COURSE";
    public static final String CURRENT_STUDENT_result = "CURRENT_STUDENT_result";

    private Context context;

    TextView nomCompleto;
    TextView fonos;
    TextView direccion;
    TextView mail;
    TextView tipoFalta;
    TextView mensaje;
    TextView fecha;
    TextView curso;



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fault_detail_activity);
        context=getApplicationContext();

        //Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        //setActionBar(toolbar);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        resultUser = (UserDTO)getIntent().getExtras().getSerializable(CURRENT_USER);
        curentFault = (FaultDTO)getIntent().getExtras().getSerializable(CURRENT_FAULT);
        resultCourse = (CourseDTO)getIntent().getExtras().getSerializable(RESULT_COURSE);
        resultStudent = (StudentDTO)getIntent().getExtras().getSerializable(CURRENT_STUDENT_result);

        nomCompleto = (TextView)findViewById(R.id.nombre_completo_student_text_view_detail);
        fonos = (TextView)findViewById(R.id.telefonos_student_text_view_detail);
        direccion = (TextView)findViewById(R.id.direcciones_student_text_view_detail);
        mail = (TextView)findViewById(R.id.mails_student_text_view_detail);
        tipoFalta = (TextView)findViewById(R.id.tipo_falta_text_view_detail);
        mensaje = (TextView)findViewById(R.id.mensaje_text_view_detail);
        fecha = (TextView)findViewById(R.id.fecha_enviado_text_view_detail);
        curso = (TextView)findViewById(R.id.curso_text_view_detail);

        nomCompleto.setText(resultStudent.getNombre1() + " " + resultStudent.getNombre2() + " " +resultStudent.getApellido_paterno() + " " + resultStudent.getApellido_materno());
        fonos.setText(resultStudent.getCel() + " " + resultStudent.getFono());
        direccion.setText(resultStudent.getDir1());
        mail.setText(resultStudent.getMail1());
        tipoFalta.setText(curentFault.getTipoFaltaText());
        mensaje.setText(curentFault.getMensaje());
        fecha.setText(curentFault.getFechaEnviado().toString());
        curso.setText(resultCourse.getGradoCurso() + "-" + resultCourse.getNomCurso());

        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

}
