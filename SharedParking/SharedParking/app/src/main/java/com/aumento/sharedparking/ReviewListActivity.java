package com.aumento.sharedparking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.sharedparking.adapter.ReviewListAdapter;
import com.aumento.sharedparking.modelclass.ReviewListModelClass;
import com.aumento.sharedparking.utils.GlobalPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewListActivity extends AppCompatActivity {

    private static final String TAG = "ReviewListActivity";

    private RecyclerView reviewListRV;
    private String pid, ip;

    private List<ReviewListModelClass> reviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_list);

        GlobalPreference globalPreference = new GlobalPreference(this);
        ip = globalPreference.RetriveIP();
        pid = getIntent().getStringExtra("pid");

        reviewListRV = (RecyclerView) findViewById(R.id.reviewListRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        reviewListRV.setLayoutManager(layoutManager);

        loadData();


    }

    private void loadData() {

        reviewList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/getParkingReview.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String rating_value = object.getString("rating_value");
                        String review = object.getString("review");

                        reviewList.add(new ReviewListModelClass(id,rating_value,review));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ReviewListAdapter listAdapter = new ReviewListAdapter(reviewList,ReviewListActivity.this);
                reviewListRV.setAdapter(listAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
                Toast.makeText(ReviewListActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("pid", pid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}