package com.finalproject.mauritorrez.schoolcontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.finalproject.mauritorrez.schoolcontrol.DTO.CourseDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.StudentDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.UserDTO;

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
import java.util.ArrayList;
import java.util.UUID;


/**
 * Created by mauri on 4/3/2016.
 */
public class ListStudentActivity extends Activity {


    private static final String LOG_TAG = ListStudentActivity.class.getSimpleName();

    private static final int JOB_POST_LOADER_ID = 1;
    public ListStudentAdapter listStudentAdapter;
    public ArrayList<StudentDTO> arrayListStudents = new ArrayList<StudentDTO>();
    private ListView listView;
    public static final String RESULT = "RESULT";
    public static final String CURRENT_USER = "CURRENT_USER";
    private CourseDTO CurrentCourse;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_students);
        context=getApplicationContext();

        //jobPostAdapter = new JobPostAdapter(this, null, 0);
        listView = (ListView)findViewById(R.id.student_list_view);
        //listView.setAdapter(jobPostAdapter);
        //getSupportLoaderManager().initLoader(JOB_POST_LOADER_ID, null, this);
        //Thread.sleep(3000);

        CourseDTO resultCourse = (CourseDTO)getIntent().getExtras().getSerializable(RESULT);

        syncData(resultCourse);

        listStudentAdapter = new ListStudentAdapter(this,
                //id.nombre_curso_text_view,
                //0,
                arrayListStudents);
        listView.setAdapter(listStudentAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TableRow tableRow = (TableRow)parent.getItemAtPosition(position);
                long idPoss = parent.getItemIdAtPosition(position);

                CourseDTO resultCourse = (CourseDTO)getIntent().getExtras().getSerializable(RESULT);
                UserDTO CURRENT_USER_result = (UserDTO)getIntent().getExtras().getSerializable(CURRENT_USER);

                StudentDTO selectedStudent = listStudentAdapter.getItem(position);
                Intent intent = new Intent(getBaseContext(), FaultFormActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putSerializable(DetailsElementActivity.RESULT, jobPost);
                intent.putExtra(FaultFormActivity.RESULT, resultCourse);
                intent.putExtra(FaultFormActivity.CURRENT_USER, CURRENT_USER_result);
                intent.putExtra(FaultFormActivity.CURRENT_STUDENT_result, selectedStudent);
                startActivity(intent);
            }
        });


    }


    public void syncData(CourseDTO tempCourseDTO) {
        GetDataAsyncTask asyncTask = new GetDataAsyncTask();

        asyncTask.execute(tempCourseDTO);

    }



    private class GetDataAsyncTask extends AsyncTask<CourseDTO, Void, Void> {

        private CourseDTO currentCourse = new CourseDTO();

        @Override
        protected Void doInBackground(CourseDTO... course) {

            //currentUser.setUsername(user[0].getUsername());
            //currentUser.setPassword(user[0].getPassword());
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
                header.put("messageType","EstudiantesGetByCourseId");
                header.put("deviceId","EstudiantesGetByCourseId");
                header.put("userGuid","08A012D5-3DAB-4D0B-8444-90E77CF87D74");
                header.put("ClientType","1");
                JSONObject body = new JSONObject();

                JSONArray array = new JSONArray();
                JSONArray newArray = new JSONArray();
                array.put(new JSONObject().put("Key", "GuidCurso").put("Value", course[0].getGuidCurso()));
                //array.put(new JSONObject().put("Key", "Passw").put("Value", user[0].getPassword()));
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

                    if ( meesageType.equalsIgnoreCase("EstudiantesGetByCourseId"))
                    {
                        bodyArray = bodyArray.getJSONArray(0);
                        JSONObject elementArray = bodyArray.getJSONObject(0);
                        JSONObject bodyJson = bodyArray.getJSONObject(0);
                        bodyArray = bodyJson.getJSONArray("Value");

                        int length = bodyJson.length();

                        for (int i = 0; i <= length; i++) {

                            JSONObject element = bodyArray.getJSONObject(i);

                            UUID id_estudiante = UUID.fromString(element.getString("GuidEstudiante"));
                            String nombre1 = element.getString("Nombre1");
                            String nombre2 = element.getString("Nombre2");
                            String apellido_paterno = element.getString("Apellido_paterno");
                            String apellido_materno = element.getString("Apellido_materno");
                            String email1 = element.getString("Mail1");
                            String emal2 = element.getString("Mail2");
                            String fono = element.getString("fono");
                            String cel = element.getString("Cel");
                            String dir1 = element.getString("Dir1");
                            String dir2 = element.getString("Dir2");

                            StudentDTO tempStudent = new StudentDTO();
                            tempStudent.setGuidEstudiante(id_estudiante);
                            tempStudent.setNombre1(nombre1);
                            tempStudent.setNombre2(nombre2);
                            tempStudent.setApellido_paterno(apellido_paterno);
                            tempStudent.setApellido_materno(apellido_materno);
                            tempStudent.setMail1(email1);
                            tempStudent.setMail2(emal2);
                            tempStudent.setFono(fono);
                            tempStudent.setCel(cel);
                            tempStudent.setDir1(dir1);
                            tempStudent.setDir2(dir2);

                            arrayListStudents.add(tempStudent);
                        }
                    }

                }
                else
                {
                    Log.d(LOG_TAG, "url connection getresponse messaje : " + urlConnection.getResponseMessage());

                }
                Log.d(LOG_TAG, "finished students");

            }
            catch (IOException e) {
                Log.d(LOG_TAG, " IOException : " + e.getMessage());
            } catch (JSONException e) {
                Log.d(LOG_TAG, " IOException : " + e.getMessage());
            } catch(Exception e){
                Log.d(LOG_TAG, " IOException : " + e.getMessage());
            }finally {
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
            listView.invalidateViews();

        }
    }

}
