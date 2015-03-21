/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.business;

import android.content.Context;
import android.content.Intent;

import com.Hashto.common.Constants;
import com.Hashto.common.Friend;
import com.Hashto.common.Message;
import com.Hashto.internet.ChatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class Service {
    private Context context;

    public Service(Context context) {
        this.context = context;
    }

    public void registration(JSONObject jsonObject) throws Exception {
        //Log.d("Request", response);
        if (jsonObject.get("Result").equals("true")) {
            Constants.user.setId(jsonObject.getInt("id_hashtwo"));
            JSONArray array = jsonObject.getJSONArray("friends_id");

            for (int i = 0; i < array.length(); i++) {
                for (int j = 0; j < Constants.friends.size(); j++) {
                    if (array.getJSONObject(i).getInt("id") == Constants.friends.get(j).getId()) {
                        Constants.friends.get(j).setId(array.getJSONObject(i).getInt("heloo_id"));
                        break;
                    }
                }
            }
            //FileActions.writeToFile(user.toString(), user.getId() + ".txt");
            //for (int i = 0; i < friends.size(); i++) {
            //FileActions.writeToFile(friends.get(i).toString(), friends.get(i).getId() +".txt");
            //}

            Intent intent = new Intent(context,
                    ChatActivity.class);
            intent.putExtra("User", Constants.user);
            intent.putExtra("Freinds", Constants.friends);
            //intent.putExtra("events", events);
            context.startActivity(intent);
        }
    }

    public void authentification(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
            Constants.user.setId(jsonObject.getLong("id"));
            Constants.user.setName(jsonObject.getString("fname"));
            Constants.user.setLastName(jsonObject.getString("lname"));
            Constants.user.setSexe(jsonObject.getInt("gender"));
            Constants.user.setbDay(jsonObject.getString("bday"));
            Constants.user.setCity(jsonObject.getString("location"));
            Constants.user.setEmail(jsonObject.getString("email"));
            Intent intent = new Intent(context,
                    ChatActivity.class);
            intent.putExtra("User", Constants.user);
            intent.putExtra("Friends", Constants.friends);
            //intent.putExtra("events", events);
            context.startActivity(intent);
        }
    }

    public void getFriends(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
            Constants.friends.clear();
            JSONArray arrayOfFriends = jsonObject.getJSONArray("friends");
            for (int i = 0; i < arrayOfFriends.length(); i++) {
                JSONObject obj = arrayOfFriends.getJSONObject(i);
                Friend friend = new Friend();
                friend.setId(obj.getLong("id"));
                friend.setName(obj.getString("fname"));
                friend.setLastName(obj.getString("lname"));
                Constants.friends.add(friend);
            }
        }
    }

    public void addEvent(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
        }
    }

    public void addFriends(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
        }
    }

    public void addPictures(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
        }
    }

    public void getPicture(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
            JSONArray array = jsonObject.getJSONArray("pictures");
            List<String> pictures = new LinkedList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                pictures.add(obj.getString("picture"));
            }
        }
    }


    public void sendMessage(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
        }
    }

    public void getNewMessages(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
            List<Message> messages = new LinkedList<>();
            JSONArray array = jsonObject.getJSONArray("messages");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                messages.add(new Message(obj.getString("message"), obj.getLong("from")));
            }
        }
    }

    public void getMessagesHistory(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
        }
    }

    public void updatUser(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
        }
    }

    public void removeUser(JSONObject jsonObject) throws Exception {
        if (jsonObject.get("Result").equals("true")) {
        }
    }

}
