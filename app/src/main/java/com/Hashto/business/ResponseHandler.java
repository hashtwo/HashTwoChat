/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.business;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class ResponseHandler {
    private Context context;

    public ResponseHandler(Context context) {
        this.context = context;
    }

    public void handleResponse(String response) {
        try {
            JSONObject obj = new JSONObject(response);
            Service service = new Service(context);
            switch (obj.getString("Action")) {
                case "Registration":
                    service.registration(obj);
                    break;
                case "Authentification":
                    service.authentification(obj);
                    break;
                case "getFreinds":
                    service.getFriends(obj);
                    break;
                case "addEvent":
                    service.addEvent(obj);
                    break;
                case "addFreinds":
                    service.addFriends(obj);
                    break;
                case "addPictures":
                    service.addPictures(obj);
                    break;
                case "getPicture":
                    service.getPicture(obj);
                    break;
                case "sendMessage":
                    service.sendMessage(obj);
                    break;
                case "getNewMessages":
                    service.getNewMessages(obj);
                    break;
                case "getMessagesHistory":
                    service.getMessagesHistory(obj);
                    break;
                case "updatUser":
                    service.updatUser(obj);
                    break;
                case "removeUser":
                    service.removeUser(obj);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
