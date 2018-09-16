package com.example.jczha.myfirstapp;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import DatasetObjects.HRInterval;
import DatasetObjects.HeartRateZoneInfo;
import DatasetObjects.UserInformation;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetHeartRateData().execute();
    }

    private class GetHeartRateData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String  url = "https://api.fitbit.com/1/user/-/activities/heart/date/today/1d/1sec/time/00:00/00:01.json";
            String jsonStr = sh.makeServiceCall(url);

            ArrayList<HeartRateZoneInfo> hr_zones_info = new ArrayList<HeartRateZoneInfo>();

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Get user information
                    JSONArray h_activities = jsonObj.getJSONArray("activities-heart");
                    for (int i=0; i < h_activities.length(); i++) {
                        JSONObject h_activity_rec = h_activities.getJSONObject(i);
                        String dateTime = h_activity_rec.getString("dateTime");
                        //TODO: handle custome heart rate zones
                        JSONArray custom_hr_zones = h_activities.getJSONObject(i).getJSONArray("customHeartRateZones");
                        // Handle standard heart rate zones
                        JSONArray standard_hr_zones = h_activities.getJSONObject(i).getJSONArray("heartRateZones");
                        for (int j=0; j < standard_hr_zones.length(); j++) {
                            String activity_name = standard_hr_zones.getJSONObject(j).getString("name");
                            Integer max_hr = standard_hr_zones.getJSONObject(j).getInt("max");
                            Integer min_hr = standard_hr_zones.getJSONObject(i).getInt("min");
                            HeartRateZoneInfo hr_zone_info = new HeartRateZoneInfo(dateTime, activity_name, max_hr, min_hr);
                            hr_zones_info.add(hr_zone_info);
                        }
                    }

                    // Get realtime hr data
                    JSONObject rt_hr_data = jsonObj.getJSONObject("activities-heart-intraday");
                    JSONArray rt_hr_dataset = rt_hr_data.getJSONArray("dataset");
                    for (int i=0; i<rt_hr_dataset.length(); i++) {

                    }

                } catch(Exception e) {

                }
            }
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
