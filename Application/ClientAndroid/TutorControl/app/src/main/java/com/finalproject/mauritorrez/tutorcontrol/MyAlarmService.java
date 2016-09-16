package com.finalproject.mauritorrez.tutorcontrol;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import android.app.Service;

import android.content.Intent;

import android.os.IBinder;

import android.widget.Toast;

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

/**
 * Created by mauri on 4/9/2016.
 */
public class MyAlarmService extends Service {



    @Override

    public void onCreate() {

// TODO Auto-generated method stub

        Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();

    }



    @Override

    public IBinder onBind(Intent intent) {

// TODO Auto-generated method stub

        Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();

        return null;

    }



    @Override

    public void onDestroy() {

// TODO Auto-generated method stub

        super.onDestroy();

        Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();

    }

    StudentDTO resultStudent = new StudentDTO();

    @Override

    public void onStart(Intent intent, int startId) {

// TODO Auto-generated method stub

        super.onStart(intent, startId);
        resultStudent = (StudentDTO)intent.getExtras().getSerializable("student_id");
        Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();
/*
        Intent intent1 = new Intent(getBaseContext(), ListHistorialActivity.class);
        intent1.putExtra(ListHistorialActivity.RESULT, new UserDTO());
        intent1.putExtra(ListHistorialActivity.CURRENT_STUDENT_result, resultStudent);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/


        NotificationAsyncTask asyncNotification = new NotificationAsyncTask();

        asyncNotification.execute(resultStudent);

        Log.d("ssssssssssssssssss", "ruunnnnnnnnnnn ");

    }

    @Override

    public boolean onUnbind(Intent intent) {

// TODO Auto-generated method stub

        Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();

        return super.onUnbind(intent);

    }


    public class NotificationAsyncTask extends AsyncTask<StudentDTO, Void, Void> {

        FaultDTO tempFault = new FaultDTO();
        ArrayList<FaultDTO> arrayListFault = new ArrayList<FaultDTO>();
        @Override
        protected Void doInBackground(StudentDTO... student) {

            // The URL To connect:
            // http://dipandroid-ucb.herokuapp.com/work_posts.json
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            Uri buildUri = Uri.parse("http://schoolcontrol.somee.com/api/values/Save");


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
                header.put("messageType","PendingFaults");
                header.put("deviceId","PendingFaults");
                header.put("userGuid","08A012D5-3DAB-4D0B-8444-90E77CF87D74");
                header.put("ClientType","1");
                JSONObject body = new JSONObject();

                JSONArray array = new JSONArray();
                JSONArray newArray = new JSONArray();
                array.put(new JSONObject().put("Key", "EstudianteGuid").put("Value", student[0].getGuidEstudiante()));
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
                    Log.d("event", "JSON: " + clientInfoJSON);

                    JSONObject messageJson = new JSONObject(clientInfoJSON);
                    JSONObject header1 = messageJson.getJSONObject("Header");
                    JSONArray bodyArray = messageJson.getJSONArray("Body");
                    String meesageType = header1.getString("MessageType");

                    if (meesageType.equalsIgnoreCase("faults")) {
                        bodyArray = bodyArray.getJSONArray(0);
                        JSONObject elementArray = bodyArray.getJSONObject(0);
                        JSONObject bodyJson = bodyArray.getJSONObject(0);
                        bodyArray = bodyJson.getJSONArray("Value");

                        int length = bodyArray.length();

                        for (int i = 0; i <= length; i++) {

                            JSONObject element = bodyArray.getJSONObject(i);

                            UUID GuidFault = UUID.fromString(element.getString("GuidFault"));
                            UUID GuidEstudiante = UUID.fromString(element.getString("GuidEstudiante"));
                            String GuidProfesor = element.getString("GuidProfesor");
                            String GuidAdministrador = element.getString("GuidAdministrador");
                            UUID GuidTypoFalta = UUID.fromString(element.getString("GuidTypoFault"));
                            String fechaEnviado = element.getString("FechaEnviado");
                            String mensaje = element.getString("Mensage");


                            FaultDTO tempFault = new FaultDTO();
                            tempFault.setGuidFalta(GuidFault);
                            tempFault.setGuidEstudiante(GuidEstudiante);
                            //tempFault.setTipoFaltaText(GetText(GuidTypoFalta));
                            if (!GuidProfesor.isEmpty())
                            {
                                tempFault.setGuidProfesor(UUID.fromString(GuidProfesor));
                            }
                            if (!GuidAdministrador.isEmpty())
                            {
                                tempFault.setGuidAdministrador(UUID.fromString(GuidAdministrador));
                            }
                            tempFault.setGuidTipoFalta(GuidTypoFalta);
                            tempFault.setMensaje(mensaje);
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");
                            Date temp = format.parse(fechaEnviado);
                            tempFault.setFechaEnviado(temp);

                            arrayListFault.add(tempFault);
                        }
                    }

                }
                else
                {
                    Log.d("event", "url connection getresponse messaje : " + urlConnection.getResponseMessage());

                }

                Log.d("event", "finish async : " );

            } catch (IOException e) {
                Log.d("event", "IO error : " + e.getMessage());

            }
            catch (JSONException es) {
                Log.d("event", "JSON error : " + es.getMessage());
            }
            catch (Exception ex){
                Log.d("event", "Exceptio error : " + ex.getMessage());
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
            //String tempUserType = currentUser.getUserType();
            if (arrayListFault.size() > 0)
            {

                Intent intent = new Intent(getBaseContext(), ListHistorialActivity.class);
                intent.putExtra(ListHistorialActivity.RESULT, new UserDTO());
                intent.putExtra(ListHistorialActivity.CURRENT_STUDENT_result, resultStudent);
                intent.putExtra("arrayListFault", arrayListFault);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        }


    }


}

