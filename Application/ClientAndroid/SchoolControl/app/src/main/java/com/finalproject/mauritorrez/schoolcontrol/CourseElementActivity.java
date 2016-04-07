package com.finalproject.mauritorrez.schoolcontrol;

/**
 * Created by mauri on 3/30/2016.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

//import com.finalproject.mauritorrez.findjob.Data.JobPostDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.CourseDTO;

import java.util.ArrayList;

/**
 * Created by mauri on 10/24/2015.
 */
public class CourseElementActivity  extends AppCompatActivity {

    private static final String LOG_TAG = CourseElementActivity.class.getSimpleName();

    public static final String RESULT = "RESULT";
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_element);
        ArrayList<String> arrayList = new ArrayList<String>();

/*
        TextView titleTextView = (TextView)findViewById(R.id.nombre_curso_text_view);
        TextView descriptionTextView = (TextView)findViewById(R.id.grado_curso_text_view);
        //ListView contactsListView = (ListView)findViewById(R.id.contacts_list_view);
        CourseDTO result = (CourseDTO)getIntent().getExtras().getSerializable(RESULT);*/
/*
        Log.d(LOG_TAG, "getContacts Number: " + result.getContacts().size());
        titleTextView.setText(result.getTitle());
        descriptionTextView.setText(result.getDescription());
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_course_element,
                R.id.content_text_view, result.getContacts());
        contactsListView.setAdapter(arrayAdapter);*/
    }

}


