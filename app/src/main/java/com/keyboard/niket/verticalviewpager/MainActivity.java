package com.keyboard.niket.verticalviewpager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.keyboard.niket.verticalviewpager.adapter.ViewPagerAdapter;
import com.keyboard.niket.verticalviewpager.constant.GlobalConstants;
import com.keyboard.niket.verticalviewpager.modelclass.DummyDataModel;
import com.keyboard.niket.verticalviewpager.verticalviewpager.VerticalViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {


    VerticalViewPager viewPager;
    ProgressDialog pDialog;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewpager);

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.start();

        makeStringRequest();



    }

    private void showProgressDialog() {
        if (pDialog != null) {
            pDialog.show();
        }
    }

    private void hideProgressDialog() {
        if (pDialog != null) {
            try {
                pDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            pDialog = null;
        }
    }

    public void makeStringRequest() {

        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setMessage("Fetching details");
            pDialog.setCancelable(false);
        }
        showProgressDialog();


        StringRequest strReq = new StringRequest(Request.Method.GET, GlobalConstants.DATA_URL_STRING_REQ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            checkSignUpResponse(response);

                            requestQueue.stop();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Api", "Error: " + error.getMessage());
                String res = error.toString();
                hideProgressDialog();
            }
        });


        requestQueue.add(strReq);

    }

    private void checkSignUpResponse(String res) throws JSONException {
        JSONObject jo = null;
        JSONArray jsonArray = null;
        String id = "";
        String image = "";
        String title = "";

        try {

            jo = new JSONObject(res);


            jsonArray = jo.getJSONArray("data");
            ArrayList<DummyDataModel> arrayList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                DummyDataModel dummyDataModel = new DummyDataModel();


                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject.has("booking_place_id")) {

                    id = jsonObject.getString("booking_place_id");
                    dummyDataModel.setBooking_place_id(id);
                }

                if (jsonObject.has("booking_place_image")) {

                    image = jsonObject.getString("booking_place_image");
                    dummyDataModel.setBooking_place_image(image);
                }

                if (jsonObject.has("booking_place_title")) {

                    title = jsonObject.getString("booking_place_title");
                    dummyDataModel.setBooking_place_title(title);
                }

                arrayList.add(dummyDataModel);
            }
            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), arrayList);
            viewPager.setAdapter(viewPagerAdapter);


        } catch (JSONException e) {
            Toast.makeText(this, "Server Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


}
