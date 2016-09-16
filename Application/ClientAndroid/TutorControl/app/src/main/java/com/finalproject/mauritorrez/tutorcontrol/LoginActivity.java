package com.finalproject.mauritorrez.tutorcontrol;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.finalproject.mauritorrez.tutorcontrol.DTO.FaultDTO;
import com.finalproject.mauritorrez.tutorcontrol.DTO.StudentDTO;
import com.finalproject.mauritorrez.tutorcontrol.DTO.UserDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


import android.widget.Toast;


import android.app.NotificationManager;



import android.app.TaskStackBuilder;

import android.content.Context;



import android.support.v7.app.NotificationCompat;


public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    private EditText Username;
    private EditText Password;
    private String CurrentDate;
    private String UserType;
    private UUID UserGuid;


    private PendingIntent pendingIntent;




    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=getApplicationContext();

        Username = (EditText)findViewById(R.id.username_EditText);
        Password = (EditText)findViewById(R.id.password_EditText);
        Calendar calendar= Calendar.getInstance();
        CurrentDate = calendar.getTime().toString();
/*
        Button buttonStart = (Button)findViewById(R.id.startalarm);

        Button buttonCancel = (Button)findViewById(R.id.cancelalarm);

        Button buttonnotification = (Button)findViewById(R.id.ne_notification);
*/

    }

    public void onClick_ingresar(View view) {
        DoPostcLogin();
        Log.d(LOG_TAG, "Ingresar clic : ");
    }


    public void DoPostcLogin() {
        PostLoginAsyncTask asyncTask = new PostLoginAsyncTask();

        UserDTO tempUserPost = new UserDTO();
        tempUserPost.setUsername(Username.getText().toString());
        tempUserPost.setPassword(Password.getText().toString());

        asyncTask.execute(tempUserPost);

    }

    public void onClick_start(View view) {

        Log.d(LOG_TAG, "Ingresar clic : ");

        Intent myIntent = new Intent(LoginActivity.this, MyAlarmService.class);
        pendingIntent = PendingIntent.getService(LoginActivity.this, 0, myIntent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);
        //alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 2 * 1000, pendingIntent);
        Toast.makeText(LoginActivity.this, "Start Alarm", Toast.LENGTH_LONG).show();
    }



    public void onClick_cancel(View view) {
        Log.d(LOG_TAG, "Ingresar clic : ");
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        // Tell the user about what we did.
        Toast.makeText(LoginActivity.this, "Cancel!", Toast.LENGTH_LONG).show();
    }



    private class PostLoginAsyncTask extends AsyncTask<UserDTO, Void, Void> {

        private UserDTO currentUser = new UserDTO();
        private StudentDTO currentStudent = new StudentDTO();

        @Override
        protected Void doInBackground(UserDTO... user) {

            currentUser.setUsername(user[0].getUsername());
            currentUser.setPassword(user[0].getPassword());
            // The URL To connect:
            // http://dipandroid-ucb.herokuapp.com/work_posts.json
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            /*
            Uri buildUri = Uri.parse("http://localhost:8075/").buildUpon()
                    .appendPath("api/values/Save").build();*/
            Uri buildUri = Uri.parse("http://schoolcontrol.somee.com/api/values/Save");
            //Uri buildUri = Uri.parse("http://10.0.0.5:8075/api/values/Save");
            //Uri buildUri = Uri.parse("http://dipandroid-ucb.herokuapp.com/work_posts.json");

            try {

                URL url = new URL(buildUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setInstanceFollowRedirects(false);
                urlConnection.setUseCaches(false);
                urlConnection.setRequestMethod("POST");
                urlConnection.addRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept-Encoding", "identity");

                urlConnection.connect();

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());

                JSONObject header = new JSONObject();
                header.put("messageType","Login");
                header.put("deviceId","Login");
                header.put("userGuid","08A012D5-3DAB-4D0B-8444-90E77CF87D74");
                header.put("ClientType","1");
                JSONObject body = new JSONObject();

                JSONArray array = new JSONArray();
                JSONArray newArray = new JSONArray();
                array.put(new JSONObject().put("Key", "Usuario").put("Value", user[0].getUsername()));
                array.put(new JSONObject().put("Key", "Passw").put("Value", user[0].getPassword()));
                newArray.put(array);

                JSONObject message = new JSONObject();
                message.put("Header", header);
                message.put("Body", newArray);


                wr.writeBytes(message.toString());
                wr.flush();
                wr.close();


                int HttpResult = urlConnection.getResponseCode();
                if(HttpResult==HttpURLConnection.HTTP_OK)
                {

                    InputStream inputStream = urlConnection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer buffer = new StringBuffer();

                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line).append("\n");
                    }

                    String clientInfoJSON = buffer.toString();
                    Log.d(LOG_TAG, "JSON: " + clientInfoJSON);

                    JSONObject messageJson = new JSONObject(clientInfoJSON);
                    JSONObject header1 = messageJson.getJSONObject("Header");
                    JSONArray bodyArray = messageJson.getJSONArray("Body");
                    String meesageType = header1.getString("MessageType");

                    if ( meesageType.equalsIgnoreCase("Login"))
                    {
                        bodyArray = bodyArray.getJSONArray(0);
                        int length = bodyArray.length();

                        for (int i = 0; i < length; i++) {

                            JSONObject element = bodyArray.getJSONObject(i);

                            String key = element.getString("Key");
                            String value = element.getString("Value");
                            if (key.equalsIgnoreCase("profesorGuid"))
                            {
                                currentUser.setUserGuid(UUID.fromString(value));
                            }
                            else if (key.equalsIgnoreCase("UserType"))
                            {
                                currentUser.setUserType(value);
                            }
                            else if (key.equalsIgnoreCase("studentGuid"))
                            {
                                currentStudent.setGuidEstudiante(UUID.fromString(value));
                            }
                        }
                    }

                }
                else
                {
                    Log.d(LOG_TAG, "url connection getresponse messaje : " + urlConnection.getResponseMessage());

                }

                Log.d(LOG_TAG, "finish async : " );

            } catch (IOException e) {
                Log.d(LOG_TAG, "IO error : " + e.getMessage());

            }
            catch (JSONException es) {
                Log.d(LOG_TAG, "JSON error : " + es.getMessage());
            }
            catch (Exception ex){
                Log.d(LOG_TAG, "Exceptio error : " + ex.getMessage());
            }
            finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            //getSupportLoaderManager().getLoader(JOB_POST_LOADER_ID).onContentChanged();
            String tempUserType = currentUser.getUserType();

            if (!tempUserType.isEmpty())
            {
                //NotificationAsyncTask asyncNotification = new NotificationAsyncTask();

                //asyncNotification.execute(currentStudent);

                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                alertDialog.setTitle("Login");
                alertDialog.setMessage("The job has posted successfully");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(getBaseContext(), ListHistorialActivity.class);
                                intent.putExtra(ListHistorialActivity.RESULT, currentUser);
                                intent.putExtra(ListHistorialActivity.CURRENT_STUDENT_result, currentStudent);
                                intent.putExtra("arrayListFault", new ArrayList<FaultDTO>());
                                startActivity(intent);
                            }
                        });
                alertDialog.show();
            }

        }
    }


}
