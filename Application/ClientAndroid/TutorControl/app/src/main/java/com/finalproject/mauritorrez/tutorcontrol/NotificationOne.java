package com.finalproject.mauritorrez.tutorcontrol;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.finalproject.mauritorrez.tutorcontrol.DTO.FaultDTO;

/**
 * Created by mauri on 4/10/2016.
 */
public class NotificationOne extends AppCompatActivity {

    private FaultDTO currentFault = new FaultDTO();
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_one);
        CharSequence s = "Inside the activity of Notification one ";
        int id=0;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            s = "error";
        }
        else {
            id = extras.getInt("notificationId");
            currentFault = (FaultDTO)getIntent().getExtras().getSerializable("current_fault");
        }
        TextView t = (TextView) findViewById(R.id.text1);
        s = s+"with id = "+id +"   "+ currentFault.getMensaje();
        t.setText(s);
        NotificationManager myNotificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // remove the notification with the specific id
        myNotificationManager.cancel(id);
    }


}
