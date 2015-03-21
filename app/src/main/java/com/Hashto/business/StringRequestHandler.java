/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.business;

import android.content.Context;

import com.android.volley.Response;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class StringRequestHandler implements Response.Listener<String> {

    private Context context;

    public StringRequestHandler(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(String response) {
        ResponseHandler handler = new ResponseHandler(context);
        response = response.substring(response.indexOf("{"));
        handler.handleResponse(response);
    }
}
