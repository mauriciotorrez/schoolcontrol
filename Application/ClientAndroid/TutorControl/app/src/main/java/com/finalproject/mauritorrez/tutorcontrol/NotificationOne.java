package com.finalproject.mauritorrez.tutorcontrol;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import com.finalproject.mauritorrez.tutorcontrol.DTO.FaultDTO;
import com.finalproject.mauritorrez.tutorcontrol.DTO.StudentDTO;
import com.finalproject.mauritorrez.tutorcontrol.DTO.UserDTO;

import java.util.ArrayList;

/**
 * Created by mauri on 4/10/2016.
 */
public class NotificationOne extends AppCompatActivity {

    private FaultDTO currentFault = new FaultDTO();
    private UserDTO currentUser = new UserDTO();
    private StudentDTO currentStudent = new StudentDTO();

    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String CURRENT_STUDENT_result = "CURRENT_STUDENT_result";


    private final int REQUEST_CODE = 20;


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
            currentStudent = (StudentDTO)getIntent().getExtras().getSerializable(CURRENT_STUDENT_result);
            currentUser = (UserDTO)getIntent().getExtras().getSerializable(CURRENT_USER);
        }
        TextView t = (TextView) findViewById(R.id.text1);
        s = s+"with id = "+id +"   "+ currentFault.getMensaje();
        t.setText(s);
        NotificationManager myNotificationManager =
        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // remove the notification with the specific id
        myNotificationManager.cancel(id);
    }


    @Override
    public void onBackPressed() {

        //String data = mEditText.getText();
        Intent intent = new Intent(getBaseContext(), ListHistorialActivity.class);
        intent.putExtra(ListHistorialActivity.RESULT, currentUser);
        intent.putExtra(ListHistorialActivity.CURRENT_STUDENT_result, currentStudent);
        intent.putExtra("arrayListFault", new ArrayList<FaultDTO>());
        startActivity(intent);
        setResult(REQUEST_CODE, intent);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        Log.d("resultt", "onKeyDown Called");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               // Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                break;
        }

        Log.d("CDA atra", "onKeyDown Called");
        onBackPressed();
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }


}
