package com.aumento.sharedparking;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.sharedparking.utils.GlobalPreference;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private AppBarConfiguration mAppBarConfiguration;
    FloatingActionButton scanQrFab;
    private IntentIntegrator qrScan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        scanQrFab = findViewById(R.id.scanQrFab);
        qrScan = new IntentIntegrator(this);
        scanQrFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                GlobalPreference globalPreference = new GlobalPreference(HomeActivity.this);
                globalPreference.setLoginStatus(false);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {

            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            }

            else {
                String response = result.getContents().toString();
                Log.d(TAG, "onActivityResult: "+response);
                try {
                    parseJData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void parseJData(String response) throws JSONException {

        GlobalPreference globalPreference = new GlobalPreference(this);
        String uid = globalPreference.RetriveUID();
        String ip = globalPreference.RetriveIP();

        JSONObject object = new JSONObject(response);
        String pid = object.getString("pid");
        String vehicleType = object.getString("vehicleType");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/logparking.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                if(response.equals("Entered"))
                    Toast.makeText(HomeActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                else if(response.equals("Failed"))
                    Toast.makeText(HomeActivity.this, "" + response, Toast.LENGTH_SHORT).show();

                else{
                    try {
                        JSONObject object = new JSONObject(response);
                        String bid = object.getString("bid");
                        String lid = object.getString("lid");
                        String vehicle_type = object.getString("vehicle_type");
                        String rate = object.getString("rate");
                        String time = object.getString("time");
                        String date = object.getString("date");

                        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                        ViewGroup viewGroup = findViewById(android.R.id.content);
                        View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.customview, viewGroup, false);
                        TextView hoursTV = dialogView.findViewById(R.id.hoursTextView);
                        TextView rateTV = dialogView.findViewById(R.id.rateTextView);
                        hoursTV.setText(time+" Hrs");
                        rateTV.setText("Rs. "+rate);
                        Button payBT = dialogView.findViewById(R.id.payBT);
                        builder.setView(dialogView);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        payBT.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(HomeActivity.this,PaymentGatewayActivity.class);
                                intent.putExtra("pid",pid);
                                intent.putExtra("lid",lid);
                                intent.putExtra("bid",bid);
                                intent.putExtra("vehicleType",vehicle_type);
                                intent.putExtra("bdate",date);
                                intent.putExtra("amount",rate);
                                intent.putExtra("duration",time);
                                startActivity(intent);

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /*
                if (response.contains("Failed")) {
                    Toast.makeText(HomeActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                } else {

                }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
                Toast.makeText(HomeActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid", uid);
                params.put("pid", pid);
                params.put("vehicleType", vehicleType);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
        requestQueue.add(stringRequest);

    }

}