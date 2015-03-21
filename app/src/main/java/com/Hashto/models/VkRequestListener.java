/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.Hashto.business.CommunicationHandler;
import com.Hashto.business.FileActions;
import com.Hashto.common.Constants;
import com.Hashto.common.Friend;
import com.Hashto.common.User;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKRequest.VKRequestListener;
import com.vk.sdk.api.VKResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class VkRequestListener extends VKRequestListener {

    private Context context;

    public VkRequestListener(Context context) {
        this.context = context;
    }

    @Override
    public void onComplete(VKResponse response) {
        new doItInBackground().execute(response);
    }

    private void newUser(JSONObject jsonObject) throws Exception { // Retreiving
        // infos,
        // setting
        // them,
        // downloading
        // images,
        // writing
        // files
        Constants.user = new User();
        Constants.user.setId(jsonObject.getInt("id"));
        Constants.user.setName(jsonObject.getString("first_name"));
        Constants.user.setLastName(jsonObject.getString("last_name"));
        Constants.user.setbDay(jsonObject.optString("bdate"));
        Constants.user.setCity(jsonObject.optJSONObject("city").getString("title"));
        Constants.user.setSexe(jsonObject.getInt("sex"));
        Constants.user.setPictureName(jsonObject.getString("photo_max_orig"));
        Constants.user.setTypeOfConnextion("VK");
        // String name = user.getName() + user.getLastName() + ".jpg";
        // FileActions.downloadImage(jsonObject.getString("photo_max_orig"),
        // name);
        // user.setPictureName(Constants.folderPath + "userPics/" + name);

        // FileActions.writeToFile(user.toString(), user.getId() + ".txt");
    }

    private void newFriend(JSONObject jsonObject) throws Exception {
        Friend f = new Friend();
        f.setId(jsonObject.getInt("id"));
        f.setName(jsonObject.getString("first_name"));
        f.setLastName(jsonObject.getString("last_name"));
        String name = f.getName() + f.getLastName() + ".jpg";
        FileActions.downloadImage(jsonObject.getString("photo_max_orig"), name);
        Constants.friends.add(f);
    }

    @Override
    public void onError(VKError error) {
        try {
            FileActions.writeToFile(error.toString(), "error.txt");
        } catch (Exception e) {
            Log.d("File", "Erreur :" + error.toString());
        }
    }

    @Override
    public void onProgress(VKRequest.VKProgressType progressType,
                           long bytesLoaded, long bytesTotal) {
    }

    @Override
    public void attemptFailed(VKRequest request, int attemptNumber,
                              int totalAttempts) {
    }

    // Executing fetching in background with Asynctask
    private class doItInBackground extends
            AsyncTask<VKResponse, Integer, String> {

        @Override
        protected String doInBackground(VKResponse... params) {
            try {
                JSONObject json = params[0].json;
                Object obj = json.get("response");
                if (obj instanceof JSONArray) { // Case if the response of the
                    // current user infos
                    JSONArray jsonArray = (JSONArray) obj;
                    // Log.d("Request","--" + jsonArray.toString());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        newUser(jsonObject);
                    }
                } else if (obj instanceof JSONObject) { // Case if the response
                    // of the user's friends
                    JSONObject jsonObject = (JSONObject) obj;
                    Log.d("Request", jsonObject.toString());
                    int count = jsonObject.getInt("count");
                    for (int i = 0; i < count; i++) {
                        JSONArray jsonArray = jsonObject.getJSONArray("items");
                        for (int j = 0; j < jsonArray.length(); j++) {
                            JSONObject jsonObj = jsonArray.getJSONObject(i);
                            newFriend(jsonObj);
                        }
                    }
                }
                // Log.d("telechargement", "finis");
            } catch (Exception e) {
                Log.d("File", "Erreur d'écriture " + e.getMessage());
                e.printStackTrace();
            }
            CommunicationHandler handler = new CommunicationHandler(context);
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("Action", "Registration");
                jsonObject.put("user", Constants.user.toString());
                // users.remove(0);
                jsonObject.put("friends", Constants.friends);
                //jsonObject.put("events ", events);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // jsonObject.put("login", users.get(0).get);
            handler.sendRequest(jsonObject);
            return "";
        }
    }
}
