package com.example.solar.newsproject;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.ListAdapter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity implements  AdapterView.OnItemClickListener{
    private ArrayList<NewData> list1;
    private ListView lvNews;
    private NewsAdapter adapter;
    private TextView tvtitle, tvcontent, tvlink;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //ListView Code
        list1 = new ArrayList<NewData>();
        new GetNews().execute();

        //fillSongsList();
        tvtitle = findViewById(R.id.title);
        tvcontent = findViewById(R.id.textContent);
        tvlink = findViewById(R.id.link);
        adapter = new NewsAdapter(this, list1);
        lvNews = findViewById(R.id.lv01);
        lvNews.setAdapter(adapter);
        lvNews.setOnItemClickListener(this);



    }

    private void fillSongsList(){

        list1.add(new NewData("Aire","Golden Ganga","Exitos"));
        list1.add(new NewData("Animal","Ainda Duo","Vendaval"));
        list1.add(new NewData("Tormenta","Alex Andwanter","Rebeldes"));
        list1.add(new NewData("Cosas infinitas","Bengala","Bajo la sal"));
        list1.add(new NewData("Viva la vida","Coldplay","Viva la vida"));

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        tvtitle.setText(list1.get(i).getTitle());
        tvcontent.setText(list1.get(i).getContent());
        tvlink.setText(list1.get(i).getLink());
    }

    private class GetNews extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://content.guardianapis.com/search?api-key=ebecd602-f289-44fb-9f50-ff28ffab0a44";
            String jsonStr = "";

            try {
                jsonStr = sh.makeHttpRequest(createUrl(url));
            } catch (IOException e) {
                return null;
            }

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONObject response = jsonObj.getJSONObject("response");
                    JSONArray results = response.getJSONArray("results");

                    // looping through All results
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject r = results.getJSONObject(i);
                        String id = r.getString("id");
                        String name = r.getString("webTitle");
                        String email = r.getString("webUrl");

                        // tmp hash map for single contact
                        NewData nueva = new NewData(id, name, email);

                        // adding contact to contact list
                        list1.add(nueva);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            return null;
        }
        return url;
    }

}
