package com.finalproject.mauritorrez.tutorcontrol;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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
import java.util.Date;
import java.util.UUID;

/**
 * Created by mauri on 4/8/2016.
 */
public class ListHistorialActivity extends AppCompatActivity {

    private static final String LOG_TAG = ListHistorialActivity.class.getSimpleName();

    public ListHistorialAdapter listHistorialAdapter;
    public ArrayList<FaultDTO> arrayListHistorial = new ArrayList<FaultDTO>();
    public static final String RESULT = "RESULT";
    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String CURRENT_STUDENT_result = "CURRENT_STUDENT_result";
    public static final String CURRENT_FAULT = "CURRENT_FAULT";

    private ListView listViewHistorial;

    //CourseDTO resultCourse;
    UserDTO resultUser;
    StudentDTO resultStudent;

    private Context context;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_historial);
        context=getApplicationContext();

        //Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        //setActionBar(toolbar);
        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //resultCourse = (CourseDTO)getIntent().getExtras().getSerializable(RESULT);
        resultUser = (UserDTO)getIntent().getExtras().getSerializable(CURRENT_USER);
        resultStudent = (StudentDTO)getIntent().getExtras().getSerializable(CURRENT_STUDENT_result);

        listViewHistorial = (ListView)findViewById(R.id.hitorial_list_view);

        syncData();

        listHistorialAdapter = new ListHistorialAdapter(this,
                //id.nombre_curso_text_view,
                //0,
                arrayListHistorial);
        listViewHistorial.setAdapter(listHistorialAdapter);


        listViewHistorial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TableRow tableRow = (TableRow)parent.getItemAtPosition(position);
                //UserDTO resultUser = (UserDTO) getIntent().getExtras().getSerializable(CURRENT_USER);
                //CourseDTO course = (CourseDTO) getIntent().getExtras().getSerializable(RESULT);
                //StudentDTO student = (StudentDTO) getIntent().getExtras().getSerializable(RESULT);

                long idPoss = parent.getItemIdAtPosition(position);
                Log.d(LOG_TAG, "id " + id + " - positin " + position + " id poss    " + idPoss);

                FaultDTO selectedFault = listHistorialAdapter.getItem(position);
                Log.d(LOG_TAG, " messaje " + selectedFault.getMensaje() + " - fecha enviado " + selectedFault.getFechaEnviado());

                //CourseDTO coursePost = getCourseByPoss(idPoss);

                Intent intent = new Intent(getBaseContext(), FaultDetailActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putSerializable(DetailsElementActivity.RESULT, jobPost);
                intent.putExtra(FaultDetailActivity.CURRENT_FAULT, selectedFault);
                intent.putExtra(FaultDetailActivity.CURRENT_USER, resultUser);
                //intent.putExtra(FaultDetailActivity.RESULT_COURSE, resultCourse);
                intent.putExtra(FaultDetailActivity.CURRENT_STUDENT_result, resultStudent);
                startActivity(intent);
            }
        });

        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }


    public void syncData() {
        GetDataAsyncTask asyncTask = new GetDataAsyncTask();

        asyncTask.execute(resultStudent);

    }

    private class GetDataAsyncTask extends AsyncTask<StudentDTO, Void, Void> {


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
                urlConnection.setRequestMethod("POST");
                urlConnection.addRequestProperty("Content-Type", "application/json");
                urlConnection.connect();

                DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());

                JSONObject header = new JSONObject();
                header.put("messageType", "FaultGet");
                header.put("deviceId", "FaultGet");
                header.put("userGuid", "08A012D5-3DAB-4D0B-8444-90E77CF87D74");
                header.put("ClientType", "1");
                JSONObject body = new JSONObject();

                JSONArray array = new JSONArray();
                JSONArray newArray = new JSONArray();
                array.put(new JSONObject().put("Key", "GuidUser").put("Value", student[0].getGuidEstudiante()));
                newArray.put(array);

                JSONObject message = new JSONObject();
                message.put("Header", header);
                message.put("Body", newArray);

                Log.d(LOG_TAG, "historial messaje : " + message.toString());
                wr.writeBytes(message.toString());
                wr.flush();
                wr.close();


                int HttpResult = urlConnection.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {

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

                    if (meesageType.equalsIgnoreCase("EstudiantesGetByCourseId")) {
                        bodyArray = bodyArray.getJSONArray(0);
                        JSONObject elementArray = bodyArray.getJSONObject(0);
                        JSONObject bodyJson = bodyArray.getJSONObject(0);
                        bodyArray = bodyJson.getJSONArray("Value");

                        int length = bodyArray.length();

                        for (int i = 0; i < length; i++) {

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
                            tempFault.setTipoFaltaText(GetText(GuidTypoFalta));
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

                            arrayListHistorial.add(tempFault);

                        }
                    }

                } else {
                    Log.d(LOG_TAG, "url connection getresponse messaje : " + urlConnection.getResponseMessage());

                }
                Log.d(LOG_TAG, "finished historil");

            } catch (IOException e) {
                Log.d(LOG_TAG, " IOException : " + e.getMessage());
            } catch (JSONException e) {
                Log.d(LOG_TAG, " IOException : " + e.getMessage());
            } catch (Exception e) {
                Log.d(LOG_TAG, " IOException : " + e.getMessage());
            } finally {
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

        private String GetText(UUID tipoGuid)
        {
            String result = "";
            if (tipoGuid.toString().equalsIgnoreCase("6261ca29-04b2-4403-8489-7205f1bfb7ea"))
            {
                result = getString(R.string.radio_indisciplina);
            }else if (tipoGuid.toString().equalsIgnoreCase("4a5984e8-87f4-434d-a5bc-cbe1b36bb871"))
            {
                result = getString(R.string.radio_no_tarea);
            }else if (tipoGuid.toString().equalsIgnoreCase("0e92b3c6-98b5-4439-adb3-a8711949e0e1"))
            {
                result = getString(R.string.radio_no_material);
            }else if (tipoGuid.toString().equalsIgnoreCase("69df755d-015d-44ba-b897-3af5e9c08f58"))
            {
                result = getString(R.string.radio_otros);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            listViewHistorial.invalidateViews();


        }
    }

}
