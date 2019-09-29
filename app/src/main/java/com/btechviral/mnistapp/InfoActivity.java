package com.btechviral.mnistapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView recyclerView;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        textView = findViewById(R.id.result);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        String url = "https://owlbot.info/api/v3/dictionary/";
        String format = "?format=json";

        String word = getIntent().getStringExtra("word");

        Log.e("word", word);

        textView.setText(word);

        itemList = new ArrayList<>();

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url + word.toLowerCase() + format, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.e("response", response.toString());
                            JSONArray array = response.getJSONArray("definitions");
                            for(int i = 0; i < array.length(); i++){
                                JSONObject object = array.getJSONObject(i);
                                String type = object.getString("type");
                                String def = object.getString("definition");
                                String example = object.getString("example");
                                String url = object.getString("image_url");
                                Item item = new Item(type, def, example, url);
                                itemList.add(item);
                            }
                            if(itemList.isEmpty()){
                                findViewById(R.id.error_text).setVisibility(View.VISIBLE);
                            } else{
                                MyAdapter adapter = new MyAdapter(itemList, InfoActivity.this);
                                recyclerView.setAdapter(adapter);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "Token f5760ae71056db4f00eb404cb3c8ecfdc7a40634");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
