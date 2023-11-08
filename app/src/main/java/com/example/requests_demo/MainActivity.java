package com.example.requests_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView); // initialize the textView

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://jsonplaceholder.typicode.com/comments", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                StringBuilder data = new StringBuilder();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String email = jsonObject.getString("email");
                        data.append(name).append(" * ").append(email).append("\n" + "- - - - - - - - - - -"+"\n");
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                textView.setText(data.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}
