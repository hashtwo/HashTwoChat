/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.common;

import android.os.Environment;

import com.vk.sdk.VKScope;

import java.util.ArrayList;

/**
 * Created by HashTwo on 21/03/2015.
 */
public final class Constants {
    public final static String folderPath = Environment
            .getExternalStorageDirectory() + "/HashTwo/";
    public static String[] sMyScope = new String[]{VKScope.FRIENDS,
            VKScope.WALL, VKScope.PHOTOS, VKScope.NOHTTPS, VKScope.GROUPS};

    public static User user = new User();
    public static ArrayList<Friend> friends = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();
}
