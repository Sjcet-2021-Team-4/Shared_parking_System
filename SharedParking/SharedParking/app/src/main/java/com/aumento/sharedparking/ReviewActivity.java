package com.aumento.sharedparking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.sharedparking.utils.GlobalPreference;

import java.util.HashMap;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity {

    private static final String TAG = "ReviewActivity";

    RatingBar ratingBar;
    EditText reviewET;
    private String ip;
    private String uid;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        GlobalPreference globalPreference = new GlobalPreference(this);
        ip = globalPreference.RetriveIP();
        uid = globalPreference.RetriveUID();

        id = getIntent().getStringExtra("id");

        reviewET = (EditText) findViewById(R.id.reviewEditText);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        TextView submitButton = (TextView) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitReview();
            }
        });
    }

    private void submitReview() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://"+ip+"/shared_parking/API/submitReview.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: "+response);
                if(response.equals("success"))
                    finish();

                Toast.makeText(ReviewActivity.this, ""+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: "+error);
                Toast.makeText(ReviewActivity.this, ""+error, Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("pid",id);
                params.put("user_id",uid);
                params.put("review",reviewET.getText().toString());
                params.put("rating_value", String.valueOf(ratingBar.getRating()));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}