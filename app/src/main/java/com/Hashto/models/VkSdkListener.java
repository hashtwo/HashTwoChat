/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.models;

import android.app.AlertDialog;
import android.util.Log;

import com.Hashto.common.Constants;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKSdkListener;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.dialogs.VKCaptchaDialog;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class VkSdkListener extends VKSdkListener {
    private VkRequestListener requestListener;

    public VkSdkListener(VkRequestListener requestListener) {
        this.requestListener = requestListener;
    }

    @Override
    public void onCaptchaError(VKError captchaError) {
        new VKCaptchaDialog(captchaError).show();
    }

    @Override
    public void onTokenExpired(VKAccessToken expiredToken) {
        VKSdk.authorize(Constants.sMyScope);
    }

    @Override
    public void onAccessDenied(final VKError authorizationError) {
        new AlertDialog.Builder(VKUIHelper.getTopActivity()).setMessage(
                authorizationError.toString()).show();
    }

    // if the user logged in with his infos we're going to ask for the following
    // infos
    @Override
    public void onReceiveNewToken(VKAccessToken newToken) {
        //Log.d("Token", "Received new token !");
        VKRequest request = VKApi
                .users()
                .get(VKParameters
                        .from(VKApiConst.FIELDS,
                                "id,first_name,last_name,sex,bdate,city,country,photo_max_orig"));
        VKRequest request1 = VKApi
                .friends()
                .get(VKParameters
                        .from(VKApiConst.FIELDS,
                                "id,first_name,last_name,sex,bdate,city,photo_max_orig"));
        VKRequest request2 = VKApi
                .groups()
                .get(VKParameters
                        .from(VKApiConst.FIELDS,
                                "gid,name,type,photo_big,city,country,place,start_date,end_date"));
        // Problem with the group request
        request2.secure = false;
        request2.useSystemLanguage = false;
        request1.secure = false;
        request1.useSystemLanguage = false;
        request.secure = false;
        request.useSystemLanguage = false;

        // VKBatchRequest batch = new VKBatchRequest(request,request1);

        // Executing our requests thanks to the the requestListener
        request.executeWithListener(requestListener);
        request1.executeWithListener(requestListener);
        request2.executeWithListener(requestListener);

    }

    @Override
    public void onAcceptUserToken(VKAccessToken token) {
        Log.d("Token", "New token accepted !");
    }
}
