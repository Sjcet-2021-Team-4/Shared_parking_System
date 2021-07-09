package com.aumento.sharedparking.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aumento.sharedparking.HomeActivity;
import com.aumento.sharedparking.LoginActivity;
import com.aumento.sharedparking.PaymentGatewayActivity;
import com.aumento.sharedparking.R;
import com.aumento.sharedparking.ReviewActivity;
import com.aumento.sharedparking.modelclass.BookingModelClass;
import com.aumento.sharedparking.ui.gallery.GalleryFragment;
import com.aumento.sharedparking.utils.GlobalPreference;
import com.google.zxing.integration.android.IntentIntegrator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookingListAdapter extends RecyclerView.Adapter<BookingListAdapter.MyViewHolder> {

    private static final String TAG = "BookingListAdapter";

    private final String ip;
    private final GalleryFragment fragment;
    private List<BookingModelClass> medicList;
    private Context mCtx;

    public BookingListAdapter(List<BookingModelClass> medicList, Context mCtx, GalleryFragment fragment) {
        GlobalPreference globalPreference = new GlobalPreference(mCtx);
        ip = globalPreference.RetriveIP();
        this.medicList = medicList;
        this.fragment = fragment;
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_my_bookings, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final BookingModelClass lists = medicList.get(position);

        holder.placeNameTV.setText(lists.getPlace_name());
        holder.vehicleNumberTV.setText(lists.getVehicle_number());
        holder.vehicleTypeTV.setText(lists.getVehicle_type());
        holder.bdateTV.setText(lists.getBdate());
        holder.bdurationTV.setText(lists.getBduration()+"Hrs");
        holder.bamountTV.setText(lists.getAmount()+"Rs");

        holder.writeReviewTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, ReviewActivity.class);
                intent.putExtra("id",lists.getPid());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mCtx.startActivity(intent);
            }
        });

        holder.directionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate(lists.getPid());
            }
        });



        holder.exitBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                exitBooking(lists.getId(), position, holder);

            }
        });

    }

    private void navigate(String pid) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/getDirection.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);

                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String lat = object.getString("latitude");
                        String lon = object.getString("longitude");

                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps?daddr="+lat+","+lon));
                        mCtx.startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("pid", pid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        requestQueue.add(stringRequest);

    }



    private void exitBooking(String bid, int position, MyViewHolder holder) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://" + ip + "/shared_parking/API/finish_booking.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
//                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject object = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("data");
//                    JSONObject object = jsonArray.getJSONObject(0);

                    String bid = object.getString("bid");
                    String pid = object.getString("pid");
                    String vehicle_type = object.getString("vehicle_type");
                    String rate = object.getString("rate");
                    String time = object.getString("time");
                    String date = object.getString("date");

                   /* AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                    ViewGroup viewGroup = holder.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(mCtx).inflate(R.layout.customview, viewGroup, false);
                    TextView hoursTV = dialogView.findViewById(R.id.hoursTextView);
                    TextView rateTV = dialogView.findViewById(R.id.rateTextView);
                    hoursTV.setText(time+" Hrs");
                    rateTV.setText("Rs. "+rate);
                    Button payBT = dialogView.findViewById(R.id.payBT);
                    builder.setView(dialogView);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();*/

//                    payBT.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {

                            Intent intent = new Intent(mCtx,PaymentGatewayActivity.class);
                            intent.putExtra("pid",pid);
                            intent.putExtra("bid",bid);
                            intent.putExtra("vehicleType",vehicle_type);
                            intent.putExtra("bdate",date);
                            intent.putExtra("amount",rate);
                            intent.putExtra("duration",time);
                            mCtx.startActivity(intent);

//                        }
//                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "onResponse: " + response);
                if(response.equals("success")) {
                    medicList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, medicList.size());
                    notifyDataSetChanged();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("bid", bid);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(mCtx);
        requestQueue.add(stringRequest);

    }

    @Override
    public int getItemCount() {
        return medicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView bookingCV;
        private ImageView scanQrIV;
        private Button exitBT;
        private TextView placeNameTV, vehicleTypeTV, vehicleNumberTV, bdateTV, bdurationTV, bamountTV, writeReviewTV, directionTV;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            placeNameTV = (TextView) itemView.findViewById(R.id.vnameTV);
            vehicleTypeTV = (TextView) itemView.findViewById(R.id.vehicleTypeTV);
            writeReviewTV = (TextView) itemView.findViewById(R.id.writeReviewTextView);
            vehicleNumberTV = (TextView) itemView.findViewById(R.id.vtypeTV);
            bdateTV = (TextView) itemView.findViewById(R.id.bdateTV);
            bdurationTV = (TextView) itemView.findViewById(R.id.bdurationTV);
            directionTV = (TextView) itemView.findViewById(R.id.directionTextView);

            bamountTV = (TextView) itemView.findViewById(R.id.bamountTV);
            bookingCV = (CardView) itemView.findViewById(R.id.bookingCardView);
            exitBT = (Button) itemView.findViewById(R.id.exitButton);

        }
    }
}
