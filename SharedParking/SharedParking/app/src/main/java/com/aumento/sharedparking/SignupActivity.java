package com.aumento.sharedparking;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.sharedparking.utils.GlobalPreference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    EditText userFirstNameET, userPhoneNumberET, userEmailET, userPasswordET, userVehicleNoET, aadharNoET;
    TextView submitButtonTV;
    private String ip;
    private String NAME_STRING = "^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$";
    private String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String MOBILE_STRING = "[0-9]{10}";
    private Spinner userVehicleTypeSpinner;

    String[] type = { "2 Wheeler", "4 Wheeler" };
    private String vTypeSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        GlobalPreference globalPreference = new GlobalPreference(getApplicationContext());
        ip = globalPreference.RetriveIP();

        init();

        submitButtonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, type);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userVehicleTypeSpinner.setAdapter(ad);
        userVehicleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vTypeSelected = type[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void signUp() {

        if(userFirstNameET.getText().toString().trim().equalsIgnoreCase("") || userEmailET.getText().toString().trim().equalsIgnoreCase("") ||
                userPhoneNumberET.getText().toString().trim().equalsIgnoreCase("") || userPasswordET.getText().toString().trim().equalsIgnoreCase("") ||
                userVehicleNoET.getText().toString().trim().equalsIgnoreCase("") || aadharNoET.getText().toString().trim().equalsIgnoreCase("") )
        {

            if(userFirstNameET.getText().toString().trim().equalsIgnoreCase(""))
                userFirstNameET.setError("This field can not be blank");

            if(userPhoneNumberET.getText().toString().trim().equalsIgnoreCase(""))
                userPhoneNumberET.setError("This field can not be blank");

            if(userEmailET.getText().toString().trim().equalsIgnoreCase(""))
                userEmailET.setError("This field can not be blank");

            if(aadharNoET.getText().toString().trim().equalsIgnoreCase(""))
                aadharNoET.setError("This field can not be blank");

            if(userPasswordET.getText().toString().trim().equalsIgnoreCase(""))
                userPasswordET.setError("This field can not be blank");

            if(userVehicleNoET.getText().toString().trim().equalsIgnoreCase(""))
                userVehicleNoET.setError("This field can not be blank");

        }
        else if(!Pattern.compile(EMAIL_STRING).matcher(userEmailET.getText().toString().trim()).matches())
        {
            userEmailET.setError("The e-mail you entered appears to be incorrect. \n (Example:youranme@gmail.com) ");
        }
        else if(!Pattern.compile(MOBILE_STRING).matcher(userPhoneNumberET.getText().toString().trim()).matches())
        {
            userPhoneNumberET.setError("The phone number you entered appears to be invalid.");
        }
        else if(!Pattern.compile(NAME_STRING).matcher(userFirstNameET.getText().toString().trim()).matches())
        {
            userFirstNameET.setError("Only letters are allowed");
        }
        else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/signup.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d(TAG, "onResponse: " + response);

                    Toast.makeText(SignupActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                    if (response.contains("Failed")) {
                        Toast.makeText(SignupActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: " + error);
                    Toast.makeText(SignupActivity.this, "" + error, Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("fname", userFirstNameET.getText().toString());
                    params.put("phone", userPhoneNumberET.getText().toString());
                    params.put("email", userEmailET.getText().toString());
                    params.put("aadharNo", aadharNoET.getText().toString());
                    params.put("vno", userVehicleNoET.getText().toString());
                    params.put("vtype", vTypeSelected);
                    params.put("password", userPasswordET.getText().toString());
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
            requestQueue.add(stringRequest);
        }

    }

    private void init() {

        userFirstNameET = (EditText) findViewById(R.id.userNameEditText);
        userPhoneNumberET = (EditText) findViewById(R.id.userPhoneNumberEditText);
        userEmailET = (EditText) findViewById(R.id.userEmailEditText);
        userPasswordET = (EditText) findViewById(R.id.userPasswordEditText);
        aadharNoET = (EditText) findViewById(R.id.aadharNoEditText);
        userVehicleTypeSpinner = (Spinner) findViewById(R.id.userVehicleTypeSpinner);
        userVehicleNoET = (EditText) findViewById(R.id.userVehicleNoEditText);
        submitButtonTV = (TextView) findViewById(R.id.submitButtonTextView);

    }
}