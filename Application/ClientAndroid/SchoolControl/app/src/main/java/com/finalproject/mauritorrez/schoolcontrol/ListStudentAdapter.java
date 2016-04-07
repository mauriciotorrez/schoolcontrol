package com.finalproject.mauritorrez.schoolcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finalproject.mauritorrez.schoolcontrol.DTO.CourseDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.StudentDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauri on 4/3/2016.
 */
public class ListStudentAdapter extends ArrayAdapter<StudentDTO> {

    private static class ViewHolder {
        TextView nombre_student;
        TextView apellido_paterno_student;
    }

    private Context context;
    private List<StudentDTO> values;
    private int layoutViewId;

    public ListStudentAdapter(Context context, ArrayList<StudentDTO> values)
    {
        super(context, R.layout.list_student_element, values);
        //this.context = context;
        //this.values = values;
        //layoutViewId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        StudentDTO student = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_student_element, parent, false);
            viewHolder.nombre_student = (TextView) convertView.findViewById(R.id.nombre1_student_text_view);
            viewHolder.apellido_paterno_student = (TextView) convertView.findViewById(R.id.apellido_paterno_student_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.nombre_student.setText(student.getNombre1());
        viewHolder.apellido_paterno_student.setText(student.getApellido_materno());
        // Return the completed view to render on screen
        return convertView;

    }

}
