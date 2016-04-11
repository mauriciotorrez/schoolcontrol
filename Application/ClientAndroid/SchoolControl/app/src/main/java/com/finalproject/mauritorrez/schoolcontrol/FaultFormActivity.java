package com.finalproject.mauritorrez.schoolcontrol;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.finalproject.mauritorrez.schoolcontrol.DTO.CourseDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.FaultDTO;
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
 * Created by mauri on 4/4/2016.
 */
public class FaultFormActivity extends AppCompatActivity {

    private static final String LOG_TAG = FaultFormActivity.class.getSimpleName();

    private static final int JOB_POST_LOADER_ID = 1;
    public ListCoursesAdapter listCoursesAdaptertAdapter;
    public ArrayList<CourseDTO> arrayListCourses = new ArrayList<CourseDTO>();
    public static final String RESULT = "RESULT";
    public static final String CURRENT_USER = "CURRENT_USER";
    public static final String CURRENT_STUDENT_result = "CURRENT_STUDENT_result";

    TextView nomCompleto;
    TextView fonos;
    TextView direccion;
    TextView mail;

    CourseDTO resultCourse;
    UserDTO resultUser;
    StudentDTO resultStudent;

    private RadioGroup grouptipoFalta;
    private RadioButton groupFaltaRadio;

    EditText mensajeMultiline;

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fault_form);
        context=getApplicationContext();

        resultCourse = (CourseDTO)getIntent().getExtras().getSerializable(RESULT);
        resultUser = (UserDTO)getIntent().getExtras().getSerializable(CURRENT_USER);
        resultStudent = (StudentDTO)getIntent().getExtras().getSerializable(CURRENT_STUDENT_result);

        nomCompleto = (TextView)findViewById(R.id.nombre_completo_student_text_view);
        fonos = (TextView)findViewById(R.id.telefonos_student_text_view);
        direccion = (TextView)findViewById(R.id.direcciones_student_text_view);
        mail = (TextView)findViewById(R.id.mails_student_text_view);

        grouptipoFalta = (RadioGroup)findViewById(R.id.radio_tipo_indisciplina);

        mensajeMultiline = (EditText)findViewById(R.id.editText_mensaje_multiline);

        nomCompleto.setText(resultStudent.getNombre1() + " " + resultStudent.getNombre2() + " " +resultStudent.getApellido_paterno() + " " + resultStudent.getApellido_materno());
        fonos.setText(resultStudent.getCel() + " " + resultStudent.getFono());
        direccion.setText(resultStudent.getDir1());
        mail.setText(resultStudent.getMail1());
    }


    /*
    5200C71F-7A0F-465D-9633-B08606AD32A8	Indisciplina
B5F76BB9-4F11-4B72-BD12-CCCBE403F715	NoMaterial
6D726730-01F8-4B78-BDE0-D016E2027278	Otros
534F1202-3FCE-44D2-8AC5-D74F8B746E80	NoTarea



deply

69df755d-015d-44ba-b897-3af5e9c08f58	Otros
6261ca29-04b2-4403-8489-7205f1bfb7ea	Indisciplina
0e92b3c6-98b5-4439-adb3-a8711949e0e1	NoMaterial
4a5984e8-87f4-434d-a5bc-cbe1b36bb871	NoTarea

     */


    public void onClick_historial(View view) {
        //DoPostcLogin();
        Log.d(LOG_TAG, "historial clic : ");
        Intent intent = new Intent(getBaseContext(), ListHistorialActivity.class);
        intent.putExtra(FaultFormActivity.RESULT, resultCourse);
        intent.putExtra(FaultFormActivity.CURRENT_USER, resultUser);
        intent.putExtra(FaultFormActivity.CURRENT_STUDENT_result, resultStudent);
        startActivity(intent);

    }

    public void onClick_enviar(View view) {
        //DoPostcLogin();
        Log.d(LOG_TAG, "enviar click : ");

        GetDataAsyncTask asyncTask = new GetDataAsyncTask();

        FaultDTO tempFaultDTO = new FaultDTO();

        tempFaultDTO.setGuidEstudiante(resultStudent.getGuidEstudiante());
        int tipoFaltaId = grouptipoFalta.getCheckedRadioButtonId();
        groupFaltaRadio = (RadioButton) findViewById(tipoFaltaId);

        String radioText = groupFaltaRadio.getText().toString();

        if (radioText.equalsIgnoreCase(getString(R.string.radio_indisciplina)))
        {
            tempFaultDTO.setGuidTipoFalta(UUID.fromString("6261ca29-04b2-4403-8489-7205f1bfb7ea"));
        }else if (radioText.equalsIgnoreCase(getString(R.string.radio_no_tarea)))
        {
            tempFaultDTO.setGuidTipoFalta(UUID.fromString("4a5984e8-87f4-434d-a5bc-cbe1b36bb871"));
        }else if (radioText.equalsIgnoreCase(getString(R.string.radio_no_material)))
        {
            tempFaultDTO.setGuidTipoFalta(UUID.fromString("0e92b3c6-98b5-4439-adb3-a8711949e0e1"));
        }else if (radioText.equalsIgnoreCase(getString(R.string.radio_otros)))
        {
            tempFaultDTO.setGuidTipoFalta(UUID.fromString("69df755d-015d-44ba-b897-3af5e9c08f58"));
        }

        if (resultUser.getUserType().equalsIgnoreCase("Profesor"))
        {
            tempFaultDTO.setGuidProfesor(resultUser.getUserGuid());
        }
        else
        {
            tempFaultDTO.setGuidAdministrador(resultUser.getUserGuid());
        }

        tempFaultDTO.setMensaje(mensajeMultiline.getText().toString());

        //tempUserPost.setUsername(Username.getText().toString());
        //empUserPost.setPassword(Password.getText().toString());
        //ArrayList<String> ar = new ArrayList<String>();
        //ar.add(Contact.getText().toString());
        //tempJobPost.setContacts(ar);

        asyncTask.execute(tempFaultDTO);

    }


    private class GetDataAsyncTask extends AsyncTask<FaultDTO, Void, Void> {

        //private CourseDTO currentCourse = new CourseDTO();
        private String faultCreated;

        @Override
        protected Void doInBackground(FaultDTO... fault) {

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
                header.put("messageType","SaveFault");
                header.put("deviceId","SaveFault");
                header.put("userGuid","08A012D5-3DAB-4D0B-8444-90E77CF87D74");
                header.put("ClientType","1");
                JSONObject body = new JSONObject();

                JSONArray array = new JSONArray();
                JSONArray newArray = new JSONArray();
                array.put(new JSONObject().put("Key", "GuidEstudiante").put("Value", fault[0].getGuidEstudiante()));
                array.put(new JSONObject().put("Key", "GuidProfesor").put("Value", fault[0].getGuidProfesor()));
                array.put(new JSONObject().put("Key", "GuidAdministrador").put("Value", fault[0].getGuidAdministrador()));
                array.put(new JSONObject().put("Key", "GuidTypoFault").put("Value", fault[0].getGuidTipoFalta()));
                array.put(new JSONObject().put("Key", "Mensage").put("Value", fault[0].getMensaje()));
                array.put(new JSONObject().put("Key", "Repuesta").put("Value", fault[0].getRespuesta()));
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

                    //if ( meesageType.equalsIgnoreCase("Fault"))
                    //{
                        bodyArray = bodyArray.getJSONArray(0);
                        int length = bodyArray.length();

                        for (int i = 0; i < length; i++) {

                            JSONObject element = bodyArray.getJSONObject(i);

                            String key = element.getString("Key");
                            String value = element.getString("Value");
                            if (key.equalsIgnoreCase("Fault"))
                            {
                                faultCreated = value;
                            }
                        }
                    //}

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
            if (!faultCreated.isEmpty())
            {
                AlertDialog alertDialog = new AlertDialog.Builder(FaultFormActivity.this).create();
                alertDialog.setTitle("Fault");
                alertDialog.setMessage("The fault has created successfully");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(getBaseContext(), ListHistorialActivity.class);
                                intent.putExtra(FaultFormActivity.RESULT, resultCourse);
                                intent.putExtra(FaultFormActivity.CURRENT_USER, resultUser);
                                intent.putExtra(FaultFormActivity.CURRENT_STUDENT_result, resultStudent);
                                startActivity(intent);
                            }
                        });
                alertDialog.show();
            }
            else
            {
                AlertDialog alertDialog = new AlertDialog.Builder(FaultFormActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Error Fault");

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }


        }
    }



}
