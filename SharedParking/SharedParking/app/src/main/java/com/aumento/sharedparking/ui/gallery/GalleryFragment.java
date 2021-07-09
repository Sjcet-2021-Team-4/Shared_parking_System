package com.aumento.sharedparking.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.aumento.sharedparking.HomeActivity;
import com.aumento.sharedparking.LoginActivity;
import com.aumento.sharedparking.R;
import com.aumento.sharedparking.adapter.BookingListAdapter;
import com.aumento.sharedparking.modelclass.BookingModelClass;
import com.aumento.sharedparking.utils.GlobalPreference;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private static final String TAG = "GalleryFragment";

    private RecyclerView myBookingRecycler;
    private String ip, uid;
    private ArrayList<BookingModelClass> bookingList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        myBookingRecycler = (RecyclerView) root.findViewById(R.id.myBookingRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        myBookingRecycler.setLayoutManager(layoutManager);

        GlobalPreference globalPreference = new GlobalPreference(getContext());
        ip = globalPreference.RetriveIP();
        uid = globalPreference.RetriveUID();

        loadData();

        return root;
    }

    private void loadData() {

        bookingList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/getbookingList.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                if (response.contains("Failed")) {
                    Toast.makeText(getActivity(), "No Bookings", Toast.LENGTH_SHORT).show();
                } else {

                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("id");
                            String uid = object.getString("uid");
                            String pid = object.getString("pid");
                            String name = object.getString("name");
                            String vehicle_type = object.getString("vehicle_type");
                            String vehicle_number = object.getString("vehicle_number");
                            String bdate = object.getString("bdate");
                            String bduration = object.getString("bduration");
                            String amount = object.getString("amount");
                            bookingList.add(new BookingModelClass(id,pid,name,vehicle_type,vehicle_number,bdate,bduration,amount));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    BookingListAdapter listAdapter = new BookingListAdapter(bookingList,getActivity(),GalleryFragment.this);
                    myBookingRecycler.setAdapter(listAdapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
                Toast.makeText(getActivity(), "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid", uid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}