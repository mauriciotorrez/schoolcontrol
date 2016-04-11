package com.finalproject.mauritorrez.tutorcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.finalproject.mauritorrez.tutorcontrol.DTO.FaultDTO;
import com.finalproject.mauritorrez.tutorcontrol.DTO.StudentDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauri on 4/8/2016.
 */
public class ListHistorialAdapter extends ArrayAdapter<FaultDTO> {
    private static class ViewHolder {
        TextView tipo_falta;
        TextView fecha_falta;
    }

    private Context context;
    private List<StudentDTO> values;
    private int layoutViewId;

    public ListHistorialAdapter(Context context, ArrayList<FaultDTO> values)
    {
        super(context, R.layout.list_historial_element, values);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        FaultDTO fault = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_historial_element, parent, false);
            viewHolder.tipo_falta = (TextView) convertView.findViewById(R.id.tipo_falta_student_text_view);
            viewHolder.fecha_falta = (TextView) convertView.findViewById(R.id.fecha_enviado_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.tipo_falta.setText(fault.getTipoFaltaText());
        viewHolder.fecha_falta.setText(fault.getFechaEnviado().toString());
        // Return the completed view to render on screen
        return convertView;

    }
}
