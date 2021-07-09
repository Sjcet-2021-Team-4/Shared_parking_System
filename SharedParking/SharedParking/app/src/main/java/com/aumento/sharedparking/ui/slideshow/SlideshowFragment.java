package com.aumento.sharedparking.ui.slideshow;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.sharedparking.LoginActivity;
import com.aumento.sharedparking.R;
import com.aumento.sharedparking.SignupActivity;
import com.aumento.sharedparking.adapter.VehicleListAdapter;
import com.aumento.sharedparking.modelclass.VehicleModelClass;
import com.aumento.sharedparking.utils.GlobalPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SlideshowFragment extends Fragment {

    private static final String TAG = "SlideshowFragment";

    String ip, uid;
    RecyclerView myVehiclesRV;

    private TextView userNameTV, userPhoneNumberTV, userEmailTV;

    private ArrayList<VehicleModelClass> vehicleList;

    String[] type = { "2 Wheeler", "4 Wheeler" };
    private String vTypeSelected;
    private String addVNumber;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        GlobalPreference globalPreference = new GlobalPreference(getContext());
        ip = globalPreference.RetriveIP();
        uid = globalPreference.RetriveUID();

        myVehiclesRV = (RecyclerView) root.findViewById(R.id.myVehiclesRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        myVehiclesRV.setLayoutManager(layoutManager);

        loadData();

        userNameTV = root.findViewById(R.id.userNameTV);
        userPhoneNumberTV = root.findViewById(R.id.userPhoneNumberTV);
        userEmailTV = root.findViewById(R.id.userEmailTV);

        Button button = root.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                ViewGroup viewGroup = root.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.raw_add_vehicle, viewGroup, false);
                EditText vNumber = dialogView.findViewById(R.id.vNumberET);
                Spinner vspinner = dialogView.findViewById(R.id.vehicleTypeSpinner);
                Button addButton = dialogView.findViewById(R.id.addButton);
                ArrayAdapter ad = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, type);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                vspinner.setAdapter(ad);
                vspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        vTypeSelected = type[i];
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addVNumber = vNumber.getText().toString();
//                        Toast.makeText(getContext(), vTypeSelected+" "+addVNumber, Toast.LENGTH_SHORT).show();
                        addVehicle(addVNumber,vTypeSelected);
                    }
                });

                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


        return root;
    }

    private void addVehicle(String VNumber, String vTypeSelected) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/addVehicle.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                if (response.contains("Failed")) {
                    Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
                } else {
                    loadData();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid", uid);
                params.put("vnumber", VNumber);
                params.put("vtype", vTypeSelected);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

    private void loadData() {

        vehicleList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/getDetails.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                if (response.contains("Failed")) {
                    Toast.makeText(getContext(), "" + response, Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        JSONObject object = jsonArray.getJSONObject(0);

                        String name = object.getString("name");
                        String phone = object.getString("phone");
                        String email = object.getString("email");

                        userNameTV.setText(name);
                        userPhoneNumberTV.setText(phone);
                        userEmailTV.setText(email);

                        jsonArray = jsonObject.getJSONArray("vehicles");
                        for(int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object1 = jsonArray.getJSONObject(i);
                            String vehicle_number = object1.getString("vehicle_number");
                            String vehicle_type = object1.getString("vehicle_type");

                            vehicleList.add(new VehicleModelClass(vehicle_number,vehicle_type));
                        }

                        VehicleListAdapter adapter = new VehicleListAdapter(vehicleList,getActivity());
                        myVehiclesRV.setAdapter(adapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
                Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid", uid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}