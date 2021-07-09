package com.aumento.sharedparking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.sharedparking.utils.GlobalPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ParkingDetailsActivity extends AppCompatActivity {

    private static final String TAG = "ParkingDetailsActivity";

    private GlobalPreference globalPreference;
    private String uid, ip, pid;
    private String response, vehicle_number, vehicle_type, price;

    private TextView bookingTimeTV, vehicleNumberTV, vehicleTypeTV, bookingLocationTV, bookingDateTV, availableSlotsTV, number, parkingTimeTV, rateTV;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private FrameLayout itemminus, itemplus;

    float count = 1;
    private TextView placeNameTV;
    private TextView reviewTV;
    private String amount;
    private TextView parkingTV;
    private int fromHr, fromMin, toHr, toMin, selHr, selMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_details);

        Intent intent = getIntent();
        response  = intent.getStringExtra("response");
        Log.d(TAG, "onCreate: "+response);

        globalPreference = new GlobalPreference(this);
        ip  = globalPreference.RetriveIP();
        uid = globalPreference.RetriveUID();

        init();

        loadJson(response);
    }

    private void loadJson(String response) {

        TextView ratingTV = findViewById(R.id.ratingTextView);
        TextView nameTV = findViewById(R.id.nameTV);
        LinearLayout callTV = findViewById(R.id.callTV);

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                vehicle_number = object.getString("vehicle_number");
                vehicle_type = object.getString("vehicle_type");
                vehicleNumberTV.setText(vehicle_number);
                vehicleTypeTV.setText(vehicle_type);

            }

            jsonArray = jsonObject.getJSONArray("parking");
            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                pid = object.getString("id");
                String name = object.getString("name");
                String location = object.getString("location");
                String slots = object.getString("slots");
                String oname = object.getString("oname");
                String ophone = object.getString("ophone");
                String from_time = object.getString("from_time");
                String to_time = object.getString("to_time");
                String rating = object.getString("rating");
                price = object.getString("price");

                String[] from = from_time.split(":");
                fromHr = Integer.parseInt(from[0]);
                fromMin = Integer.parseInt(from[1]);

                String[] to = to_time.split(":");
                toHr = Integer.parseInt(to[0]);
                toMin = Integer.parseInt(to[1]);

                callTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        intent.setData(Uri.parse("tel:"+ophone));
                        startActivity(intent);
                    }
                });

                ratingTV.setText(rating);
                nameTV.setText(oname);
                placeNameTV.setText(name);
                parkingTV.setText(name);
                bookingLocationTV.setText(location);
                availableSlotsTV.setText(slots);
                parkingTimeTV.setText(from_time+" - "+to_time);
                bookingTimeTV.setText(from_time);
                rateTV.setText("Rs. "+price);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void init() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mMonth++;
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        bookingTimeTV = (TextView) findViewById(R.id.bookingTimeTextView);
        vehicleNumberTV = (TextView) findViewById(R.id.vehicleNumberTextView);
        vehicleTypeTV = (TextView) findViewById(R.id.vehicleTypeTextView);
        bookingLocationTV = (TextView) findViewById(R.id.bookingLocationTextView);
        bookingDateTV = (TextView) findViewById(R.id.bookingDateTextView);
        availableSlotsTV = (TextView) findViewById(R.id.availableSlotsTextView);
        number = (TextView) findViewById(R.id.number);
        placeNameTV = (TextView) findViewById(R.id.placeNameTextView);
        parkingTimeTV = (TextView) findViewById(R.id.parkingTimeTextView);
        parkingTV = (TextView) findViewById(R.id.parkingTV);
        reviewTV = (TextView) findViewById(R.id.reviewTextView);
        rateTV = (TextView) findViewById(R.id.rateTextView);
        Button payButton = (Button) findViewById(R.id.payButton);

        itemminus = findViewById(R.id.itemminus);
        itemplus = findViewById(R.id.itemplus);

        bookingDateTV.setText(mDay+"-"+mMonth+"-"+mYear);
        bookingTimeTV.setText(mHour+":"+mMinute);

        reviewTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParkingDetailsActivity.this,ReviewListActivity.class);
                intent.putExtra("pid",pid);
                startActivity(intent);
            }
        });

        bookingTimeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ParkingDetailsActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                boolean validTime = true;
                                if (hourOfDay < fromHr || (hourOfDay == fromHr && minute < fromMin)){
                                    validTime = false;
                                }

                                if (hourOfDay  > toHr || (hourOfDay == toHr && minute > toMin)){
                                    validTime = false;
                                }

                                if (validTime) {
                                    selHr = hourOfDay;
                                    selMin = minute;
                                    bookingTimeTV.setText(hourOfDay + ":" + minute);
                                }
                                else{
                                    Toast.makeText(ParkingDetailsActivity.this, "No slots available at the selected time", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        itemminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(count > 1 )
                    count = count - 0.5f;
                number.setText(String.valueOf(count));

            }
        });

        itemplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              /*  String myTime = selHr+":"+selMin;
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Date d = null;
                try {
                    d = df.parse(myTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                cal.add(Calendar.MINUTE, 30);
                String newTime = df.format(cal.getTime());

                if(selHr  > toHr || (selHr == toHr && selMin > toMin))
              */

                count = count + 0.5f;
                number.setText(String.valueOf(count));
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book();

                /*amount = String.valueOf(Double.valueOf(price)*count);

                Intent intent = new Intent(ParkingDetailsActivity.this,PaymentGatewayActivity.class);
                intent.putExtra("uid", uid);
                intent.putExtra("pid", pid);
                intent.putExtra("vehicleType", vehicle_type);
                intent.putExtra("vehicleNumber", vehicle_number);
                intent.putExtra("bdate", bookingDateTV.getText().toString());
                intent.putExtra("duration", number.getText().toString());
                intent.putExtra("amount", amount);
                startActivity(intent);
                finish();*/

            }
        });

    }

    private void book() {

        amount = String.valueOf(Double.valueOf(price)*count);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/booking.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);
                if(response.equals("success"))
                {
                    Toast.makeText(ParkingDetailsActivity.this, "Booked", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else
                    Toast.makeText(ParkingDetailsActivity.this, ""+response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
                Toast.makeText(ParkingDetailsActivity.this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid", uid);
                params.put("pid", pid);
                params.put("vehicleType", vehicle_type);
                params.put("vehicleNumber", vehicle_number);
                params.put("bdate", bookingDateTV.getText().toString());
                params.put("btime", bookingTimeTV.getText().toString());
                params.put("duration", number.getText().toString());
                params.put("amount", amount);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ParkingDetailsActivity.this);
        requestQueue.add(stringRequest);

    }
}