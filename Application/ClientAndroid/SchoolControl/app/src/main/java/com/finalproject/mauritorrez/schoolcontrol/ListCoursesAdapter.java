package com.finalproject.mauritorrez.schoolcontrol;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finalproject.mauritorrez.schoolcontrol.DTO.CourseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauri on 3/30/2016.
 */
public class ListCoursesAdapter extends ArrayAdapter<CourseDTO> {


    private static class ViewHolder {
        TextView nombre;
        TextView grado;
    }

    private Context context;
    private List<CourseDTO> values;
    private int layoutViewId;

    public ListCoursesAdapter(Context context, ArrayList<CourseDTO> values)
    {
        super(context, R.layout.list_course_element, values);
        //this.context = context;
        //this.values = values;
        //layoutViewId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {


        CourseDTO course = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_course_element, parent, false);
            viewHolder.nombre = (TextView) convertView.findViewById(R.id.nombre_curso_text_view);
            viewHolder.grado = (TextView) convertView.findViewById(R.id.grado_curso_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.nombre.setText(course.getNomCurso());
        viewHolder.grado.setText(course.getGradoCurso());
        // Return the completed view to render on screen
        return convertView;



    }

}
