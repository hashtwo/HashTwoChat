/*
 * Copyright (c) 2015. HashTwo
 */

package com.Hashto.business;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import com.Hashto.common.Constants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by HashTwo on 21/03/2015.
 */
public final class FileActions {

    public static void downloadImage(String path, String name) { // Downloading images and put them into the
        try {
            URL imageurl = new URL(path);
            Bitmap bitmap = BitmapFactory.decodeStream(imageurl.openConnection().getInputStream());
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

            File folder = new File(Constants.folderPath + "userPics/");
            if (!folder.exists())
                folder.mkdir();

            File f = new File(Constants.folderPath + "userPics/" + name);
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());

            fo.close();
        } catch (Exception e) {
            Log.e("ImageDownload",
                    "Error while downloading image " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void writeToFile(String data, String fileName) // Writing the texte files
            throws IOException {
        File folder = new File(Constants.folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        File uInfo = new File(Constants.folderPath + fileName);
        FileWriter fw = new FileWriter(uInfo);
        fw.write(data);
        fw.close();
    }

    public static String imageToByteArrayString(String path) {
        try {
            File f = new File(path);

            byte[] bytes;
            byte[] buffer = new byte[(int) f.length()];
            int bytesRead;

            InputStream is = new FileInputStream(f);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = is.read(buffer)) != -1)
                output.write(buffer, 0, bytesRead);
            bytes = output.toByteArray();
            is.close();
            return Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isExternalStorageAvailableAndWriteable() { // Testing if enough space
        boolean externalStorageAvailable = false;
        boolean externalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            externalStorageAvailable = externalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            externalStorageAvailable = true;
            externalStorageWriteable = false;
        } else {
            externalStorageAvailable = externalStorageWriteable = false;
        }
        return externalStorageAvailable && externalStorageWriteable;
    }

}
