/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Hashto.common.Constants;
import com.Hashto.fb.FbRequestListener;
import com.Hashto.models.VkRequestListener;
import com.Hashto.models.VkSdkListener;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.vk.sdk.VKSdk;
import com.vk.sdk.VKUIHelper;
import com.vk.sdk.util.VKUtil;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by HashTwo on 21/03/2015.
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

    private VkSdkListener sdkListener;
    private VkRequestListener requestListener;
    private Button vk;
    private UiLifecycleHelper uiHelper;
    private boolean isResumed = false;
    private Button location;
    private Button recoBtn;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        VKUIHelper.onCreate(this); // Instanciation of the VK
        String[] fingerprint = VKUtil.getCertificateFingerprint(this,
                this.getPackageName()); // retrieving the certificateFingerPrint
        // in order to use the VK
        // Log.d("Fingerprint", fingerprint[0]);

        recoBtn = (Button) findViewById(R.id.recobtn);
        recoBtn.setOnClickListener(this);

        location = (Button) findViewById(R.id.location);
        location.setOnClickListener(this);

        vk = (Button) findViewById(R.id.send); // VK Button
        LoginButton authButton = (LoginButton) findViewById(R.id.button2);
        authButton.setReadPermissions(Arrays.asList("email", "user_birthday",
                "user_location", "user_friends", "user_likes", "user_events",
                "user_groups", "user_relationships"));
        vk.setOnClickListener(this); // Setting the action Listener for Vk
        if (android.os.Build.VERSION.SDK_INT > 9) { // enabling the security in
            // order to download files
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        VKUIHelper.onResume(this); // Resume VK with the app
        uiHelper.onResume();
        isResumed = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        VKUIHelper.onDestroy(this); // Destroy VK with the app
        uiHelper.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
        isResumed = false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKUIHelper.onActivityResult(this, requestCode, resultCode, data); // Calling
        // the
        // OnActivityResult
        // for
        // VK
        // to
        // retreive
        // results
        uiHelper.onActivityResult(requestCode, resultCode, data);
        for (Iterator<String> iterator = data.getExtras().keySet().iterator(); iterator
                .hasNext(); ) {
            String s = (String) iterator.next();
            Log.d("Data", s);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == vk) {
            try {
                new doItInBackground().execute("vk"); // Execute the Vk Servcice
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v == location) {
            // Intent intent = new Intent(this, MonitoringActivity.class);

            // startActivity(intent);
        }
    }

    private void onSessionStateChange(Session session, SessionState state,
                                      Exception exception) {
        // Only make changes if the activity is visible
        if (isResumed) {
            FragmentManager manager = getSupportFragmentManager();
            // Get the number of entries in the back stack
            int backStackSize = manager.getBackStackEntryCount();
            // Clear the back stack
            for (int i = 0; i < backStackSize; i++) {
                manager.popBackStack();
            }
            if (state.isOpened()) {
                // If the session state is open:
                // Show the authenticated fragment
                new doItInBackground().execute("com/heloo/fb");
            } else if (state.isClosed()) {
                // If the session state is closed:
                // Show the login fragment
                // showFragment(SPLASH, false);
            }
        }
    }

    // Private class in order to call the Vk and Fb Listeners to log in a
    // different thread
    private class doItInBackground extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            if (params[0].equals("vk")) { // If we clicked on Vk this will
                // happen
                requestListener = new VkRequestListener(MainActivity.this); // Instanciate
                // the
                // Vk
                // request
                sdkListener = new VkSdkListener(requestListener); // Giving the
                // VK
                // Listener
                // a request
                // to
                // execute
                VKSdk.initialize(sdkListener, "4592090");
                VKSdk.authorize(Constants.sMyScope, true, false);
            } else if (params[0].equals("com/heloo/fb")) {
                Session session = Session.getActiveSession();
                if (session != null && session.isOpened()) {
                    // Log.d("session", "1�re session entr�");
                    new FbRequestListener();
                }
            }
            return "";
        }

    }
}
