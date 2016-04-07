package com.finalproject.mauritorrez.schoolcontrol;

/**
 * Created by mauri on 3/30/2016.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.finalproject.mauritorrez.schoolcontrol.DTO.CourseDTO;
import com.finalproject.mauritorrez.schoolcontrol.DTO.UserDTO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;


public class ListCoursesActivity extends Activity {

    private static final String LOG_TAG = ListCoursesActivity.class.getSimpleName();

    private static final int JOB_POST_LOADER_ID = 1;
    public ListCoursesAdapter listCoursesAdaptertAdapter;
    public ArrayList<CourseDTO> arrayListCourses = new ArrayList<CourseDTO>();
    private ListView listView;
    public static final String RESULT = "RESULT";
    public static final String CURRENT_USER = "CURRENT_USER";

    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_courses);
        context=getApplicationContext();

        //jobPostAdapter = new JobPostAdapter(this, null, 0);
        listView = (ListView)findViewById(R.id.works_list_view);
        //listView.setAdapter(jobPostAdapter);
        //getSupportLoaderManager().initLoader(JOB_POST_LOADER_ID, null, this);
        //Thread.sleep(3000);
        syncData();
        listCoursesAdaptertAdapter = new ListCoursesAdapter(this,
                //id.nombre_curso_text_view,
                //0,
                arrayListCourses);
        listView.setAdapter(listCoursesAdaptertAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TableRow tableRow = (TableRow)parent.getItemAtPosition(position);
                UserDTO resultUser = (UserDTO) getIntent().getExtras().getSerializable(RESULT);

                long idPoss = parent.getItemIdAtPosition(position);
                Log.d(LOG_TAG, "id " + id + " - positin " + position + " id poss    " + idPoss);

                CourseDTO selectedCourse = listCoursesAdaptertAdapter.getItem(position);
                Log.d(LOG_TAG, " selectedCourse id " + selectedCourse.getGradoCurso() + " - nombre curso " + selectedCourse.getNomCurso());

                //CourseDTO coursePost = getCourseByPoss(idPoss);

                Intent intent = new Intent(getBaseContext(), ListStudentActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putSerializable(DetailsElementActivity.RESULT, jobPost);
                intent.putExtra(ListStudentActivity.RESULT, selectedCourse);
                intent.putExtra(ListStudentActivity.CURRENT_USER, resultUser);
                startActivity(intent);
            }
        });



    }

    public CourseDTO getCourseByPoss(long idPoss)
    {
        return new CourseDTO();
    }

/*
    public CourseDTO populateCourseDTOO(long id)
    {
        CourseDTO jobPost = new CourseDTO();
        String id1 = String.valueOf(id);
        if (id > 0)
        {
            JobPostDbHelper dbHelper = new JobPostDbHelper(ListJobsActivity.this);
            Cursor cursor =  dbHelper.getReadableDatabase().rawQuery("SELECT * FROM job_posts WHERE _id=?", new String[] {id1});
            if (cursor != null)
            {
                if (cursor.moveToFirst())
                {
                    do {
                        jobPost.setTitle(cursor.getString(cursor.getColumnIndex(JobPost.TITLE_COLUMN)));
                        jobPost.setDescription(cursor.getString(cursor.getColumnIndex(JobPost.DESCRIPTION_COLUMN)));
                    }while (cursor.moveToNext());
                }
            }
            else
            {
                jobPost.setTitle("");
                jobPost.setDescription("");
            }
            int idx = 0;
            cursor =  dbHelper.getReadableDatabase().rawQuery("SELECT * FROM contacts WHERE job_post_id=?", new String[] {id1});
            if (cursor != null)
            {
                ArrayList<String> tempArray = new ArrayList<>();
                if (cursor.moveToFirst())
                {


                    do {
                        tempArray.add(cursor.getString(cursor.getColumnIndex(Contact.NUMBER_COLUMN)));
                    }while (cursor.moveToNext());
                }
                jobPost.setContacts(tempArray);
            }
            else
            {
                ArrayList<String> tempArray = new ArrayList<>();
                jobPost.setContacts(tempArray);
            }
        }
        return jobPost;
    }
*/

    public void syncData() {
        GetDataAsyncTask asyncTask = new GetDataAsyncTask();

        asyncTask.execute();


    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate our menu from the resources by using the menu inflater.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_syncronize:
                // Here we might call LocationManager.requestLocationUpdates()
                syncData();
                Log.d(LOG_TAG, "menu_syncronize click: ");
                return true;

            case R.id.menu_post_job:
                // Here we would open up our settings activity
                Log.d(LOG_TAG, "menu_post_job click: ");
                Intent intent = new Intent(getBaseContext(), JobPostFormActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
*//*
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new JobPostLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        jobPostAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        jobPostAdapter.swapCursor(null);
    }

*/

    private class GetDataAsyncTask extends AsyncTask<Void, Void, Void> {



        @Override
        protected Void doInBackground(Void... params) {
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
                header.put("messageType","CoursesGet");
                header.put("deviceId","CoursesGet");
                header.put("userGuid","08A012D5-3DAB-4D0B-8444-90E77CF87D74");
                header.put("ClientType","1");
                JSONObject body = new JSONObject();

                JSONArray array = new JSONArray();
                JSONArray newArray = new JSONArray();

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

                    if ( meesageType.equalsIgnoreCase("CourseGet"))
                    {
                        bodyArray = bodyArray.getJSONArray(0);
                        JSONObject elementArray = bodyArray.getJSONObject(0);
                        JSONObject bodyJson = bodyArray.getJSONObject(0);
                        bodyArray = bodyJson.getJSONArray("Value");

                        int length = bodyJson.length();

                        for (int i = 0; i <= length; i++) {

                            JSONObject element = bodyArray.getJSONObject(i);

                            UUID GuidCurso = UUID.fromString(element.getString("GuidCurso"));
                            String NomCurso = element.getString("NomCurso");
                            String GradoCurso = element.getString("GradoCurso");
                            int Nivel = element.getInt("Nivel");


                            CourseDTO tempCourse = new CourseDTO();
                            tempCourse.setGuidCurso(GuidCurso);
                            tempCourse.setNomCurso(NomCurso);
                            tempCourse.setGradoCurso(GradoCurso);
                            tempCourse.setNivel(Nivel);

                            arrayListCourses.add(tempCourse);

                        }
                    }

                }
                else
                {
                    Log.d(LOG_TAG, "url connection getresponse messaje : " + urlConnection.getResponseMessage());

                }
                Log.d(LOG_TAG, "finished courses");

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

