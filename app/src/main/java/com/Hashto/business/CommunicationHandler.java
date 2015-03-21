/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.business;

import android.content.Context;
import android.util.Log;

import com.Hashto.internet.VolleySingleton;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class CommunicationHandler {
    private VolleySingleton singleton;
    private Context context;

    public CommunicationHandler(Context context) {
        singleton = VolleySingleton.getInstance(context
                .getApplicationContext());
        this.context = context;
    }


    public void sendRequest(JSONObject jsonObject) {
        String req = "http://hashtwo.rhcloud.com/Controller?Request="
                + jsonObject.toString();
        //Log.d("Request", req);

        StringRequest request = new StringRequest(req, new StringRequestHandler(context), new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Request", error.getMessage());
            }
        });

        request.setRetryPolicy(new DefaultRetryPolicy(
                DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        singleton.getRequestQueue().add(request);
    }

}
