/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.fb;

import android.os.AsyncTask;
import android.os.Bundle;

import com.Hashto.business.FileActions;
import com.Hashto.common.Constants;
import com.Hashto.common.User;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class FbRequestListener extends Request {

    public FbRequestListener() {
        Session session = Session.getActiveSession();
        if (session != null && session.isOpened()) {
            new doItInBackground().doInBackground(session);
        }
    }

    private void newUser(JSONObject jsonObject) throws Exception {
        User u = new User();
        //Log.d("Request", jsonObject.toString());
        //Log.d("Request", jsonObject.getString("app_scoped_user_id"));
        u.setId(jsonObject.getInt("id"));
        u.setName(jsonObject.getString("first_name"));
        u.setLastName(jsonObject.getString("last_name"));
        u.setbDay(jsonObject.optString("birthday"));
        // Log.d("City", jsonObject.optJSONObject("city").toString());
        JSONObject location = jsonObject.optJSONObject("location");
        if (location != null) {
            u.setCity(location.getString("name"));
        } else
            u.setCity("not available");
        //u.setCity(jsonObject.optJSONObject("city").getString("title"));
        u.setSexe(jsonObject.getInt("gender"));
        String name = u.getName() + " " + u.getLastName() + ".jpg";
        //FileActions.downloadImage(jsonObject.getString("photo_max_orig"), name);
        // Log.d("url", jsonObject.getString("photo_max_orig"));
        u.setPictureName(Constants.folderPath + "userPics/" + name);
        FileActions.writeToFile(u.toString(), u.getId() + ".txt");
        // tv.setText(tv.getText() + " " + u.toString());
    }


    private class doItInBackground extends AsyncTask<Session, Integer, String> {

        @Override
        protected String doInBackground(final Session... sessions) {
            Session session = sessions[0];
            if (session.getState().isOpened()) {
                Bundle params = new Bundle();
                params.putBoolean("redirect", false);
                params.putString("height", "1024");
                params.putString("type", "normal");
                params.putString("width", "780");
                new Request(session, "/me/picture", params, HttpMethod.GET,
                        new Request.Callback() {
                            public void onCompleted(Response response) {
                                try {
                                    GraphObject graphObject = response
                                            .getGraphObject();
                                    JSONObject jsonObject = graphObject
                                            .getInnerJSONObject();
                                    JSONObject res = jsonObject
                                            .getJSONObject("data");
                                    FileActions.downloadImage(res.getString("url"),
                                            "profilepicture.jpg");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).executeAndWait();
                new Request(session, "/me/taggable_friends", params, HttpMethod.GET,
                        new Request.Callback() {
                            public void onCompleted(Response response) {
                                try {
                                    GraphObject graphObject = response
                                            .getGraphObject();
                                    JSONObject jsonObject = graphObject
                                            .getInnerJSONObject();

                                    JSONArray array = jsonObject
                                            .getJSONArray("data");
//										Log.d("Request", response.toString());
//										Log.d("Request", graphObject.toString());
//										Log.d("Request", jsonObject.toString());
                                    for (int i = 0; i < array.length(); i++) {
                                        JSONObject friend = array.getJSONObject(i);
                                        JSONObject pic = friend
                                                .getJSONObject("picture");
                                        String url = pic.getJSONObject("data")
                                                .getString("url");
                                        String id = url.substring(
                                                url.indexOf("_") + 1, url.indexOf(
                                                        "_", url.indexOf("_") + 1));
                                        String name = friend.getString("name");
                                        FileActions.downloadImage(url, id + ".jpg");
                                        FileActions.writeToFile(name + ", " + id, id + ".txt");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }).executeAndWait();
            }
            Request request = Request.newMeRequest(sessions[0],
                    new Request.GraphUserCallback() {
                        public void onCompleted(GraphUser user, Response response) {
                            if (sessions[0] == Session.getActiveSession()) {
                                if (user != null) {
                                    try {
                                        newUser(user.getInnerJSONObject());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            if (response.getError() != null) {

                            }
                        }
                    });
            request.executeAndWait();
            return "";
        }
    }
}
