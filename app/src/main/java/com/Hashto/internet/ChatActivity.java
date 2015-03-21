/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.internet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.Hashto.R;
import com.Hashto.business.CommunicationHandler;
import com.Hashto.common.User;

import org.json.JSONObject;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class ChatActivity extends Activity implements OnClickListener {

    private EditText msg, chat;
    private Button send, friends;
    private ProgressDialog pDialog;
    private CommunicationHandler handler;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        msg = (EditText) findViewById(R.id.msg);
        chat = (EditText) findViewById(R.id.chat);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
        friends = (Button) findViewById(R.id.btn_friends);
        friends.setOnClickListener(this);

        //handler = new CommunicationHandler(this);
        user = (User) getIntent().getExtras().get("user");

        //Log.d("Request", users.get(0).toString());

    }

    @Override
    public void onClick(View v) {
        if (v == send) {
            /*
             * pDialog = new ProgressDialog(this);
			 * pDialog.setMessage("Please wait...");
			 * pDialog.setCancelable(false);
			 */
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Action", "message");
                jsonObject.put("from", user.getId());
                jsonObject.put("to", "3");
                jsonObject.put("message", msg.getText().toString());
                chat.getText().append("\n" + msg.getText().toString());
                handler.sendRequest(jsonObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v == friends) {
            Intent intent = new Intent(this, FriendListActivity.class);
            //intent.putExtra("friends", friendsList);
            startActivity(intent);
        }
    }
}
