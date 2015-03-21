/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.internet;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.Hashto.R;
import com.Hashto.common.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class FriendListActivity extends ListActivity {
    private ArrayList<User> users;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users = (ArrayList<User>) getIntent().getExtras().get("users");

        List<String> names = new Vector<>();
        for (int i = 1; i < users.size(); i++) {
            names.add(users.get(i).getLastName() + " " + users.get(i).getName());
        }

        lv = getListView();

        ListAdapter adapter = new ArrayAdapter<>(this, R.layout.items, R.id.listText, names);
        lv.setAdapter(adapter);
    }

}
