package com.finalproject.mauritorrez.schoolcontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.finalproject.mauritorrez.schoolcontrol.DTO.UserDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    private EditText Username;
    private EditText Password;
    private String CurrentDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Username = (EditText)findViewById(R.id.username_EditText);
        Password = (EditText)findViewById(R.id.password_EditText);
        Calendar calendar= Calendar.getInstance();
        CurrentDate = calendar.getTime().toString();




       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        //ArrayList<String> ar = new ArrayList<String>();
        //ar.add(Contact.getText().toString());
        //tempJobPost.setContacts(ar);

        asyncTask.execute(tempUserPost);
/*
        AlertDialog alertDialog = new AlertDialog.Builder(JobPostFormActivity.this).create();
        alertDialog.setTitle("Encontrar Trabajo");
        alertDialog.setMessage("The job has posted successfully");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent intent = new Intent(getBaseContext(), ListJobsActivity.class);

                        startActivity(intent);
                    }
                });
        alertDialog.show();*/
    }





    private class PostLoginAsyncTask extends AsyncTask<UserDTO, Void, Void> {

        @Override
        protected Void doInBackground(UserDTO... user) {

            // The URL To connect:
            // http://dipandroid-ucb.herokuapp.com/work_posts.json
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            /*
            Uri buildUri = Uri.parse("http://localhost:8075/").buildUpon()
                    .appendPath("api/values/Save").build();*/
            Uri buildUri = Uri.parse("http://10.0.2.2:8075/api/values/Save");
            //Uri buildUri = Uri.parse("http://dipandroid-ucb.herokuapp.com/work_posts.json");

            try {

                StringBuilder sb = new StringBuilder();

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
                //body.put("Usuario",user[0].getUsername());
                //body.put("Passw",user[0].getPassword());

                body.put("Key", "usuario").put("Value", "pass");

                //JSONObject User = new JSONObject();
                //User.put("User", body);


                //JSONObject jsonObject = new JSONObject();

                JSONObject message = new JSONObject();
                message.put("header", header);
                message.put("body", body);

                JSONObject jsonParams = new JSONObject();
                //jsonParams.put("Message",message);
                //jsonObject.put("contacts",new JSONArray().put((user[0].getContacts()).get(0)));


/*
                JSONObject jsonParams = new JSONObject();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("title","1");
                jsonObject.put("description","2");
                //jsonObject.put("contacts",new JSONArray().put((jobPosts[0].getContacts()).get(0)));

                jsonParams.put("work_post", jsonObject);

*/
                wr.writeBytes(message.toString());
                wr.flush();
                wr.close();

/*
                BufferedWriter out =
                        new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
                out.write(message.toString());
                out.close();
*/

/*
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                StringBuilder stringBuilder = new StringBuilder();
                String line1;

                while( (line1 = bufferedReader.readLine()) != null )
                {
                    stringBuilder.append(line1).append("\n");
                }

                bufferedReader.close();
                String result = stringBuilder.toString();

*/
                int HttpResult = urlConnection.getResponseCode();
                if(HttpResult==HttpURLConnection.HTTP_OK)
                {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            urlConnection.getInputStream(),"atf-8" ));
                    String line = null;
                    while((line =

                            br.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    br.close();

                    Log.d(LOG_TAG, "sb result : " + sb.toString());
                }
                else
                {
                    Log.d(LOG_TAG, "url connection getresponse messaje : " + urlConnection.getResponseMessage());

                }



            } catch (IOException e) {
                Log.d(LOG_TAG, "IO error : " + e.getMessage());

            }
            catch (JSONException es) {
                Log.d(LOG_TAG, "JSON error : " + es.getMessage());

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
        }
    }




}
