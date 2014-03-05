package edu.ua.caps.commonlyusedutils.commonlyusedutilslib;

/**
 * Copyright (c) 2012 The Board of Trustees of The University of Alabama
 * 	All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the University nor the names of the contributors
 * may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class CommonlyUsedUtils {

    private static final long PRESS_BACK_BUTTON_TWICE_TIMER = 2000;
    private boolean doubleBackToExitPressedOnce = false;

    private Context mContext;
    private SharedPreferences mPrefs;
    private SharedPreferences.Editor editor;
    private FragmentManager fragmentManager;


    // Get ScreenDimensions >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    /**
     * <p>
     * This method takes in the WindowManager and returns the Screen Dimensions in pixels.</br></br>
     * </p>
     * <p>To get width (getWindowDisplaySize(wm).x) or height (getWindowDisplaySize(wm).y)</p>
     *
     * @return WindowDisplaySize
     */
    public static Point getWindowDisplaySize(WindowManager wm) {
        Display display;
        Point WindowDisplaySize = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            display = wm.getDefaultDisplay();
            display.getSize(WindowDisplaySize);
        } else {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            WindowDisplaySize.set(width, height);
        }
        return WindowDisplaySize;
    }

    // SharedPreferences >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public SharedPreferences getmPrefs() {
        return mPrefs;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }

    public CommonlyUsedUtils(Context cxt) {
        mContext = cxt;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = mPrefs.edit();
        try {
            fragmentManager = ((FragmentActivity) mContext)
                    .getSupportFragmentManager();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }


    // int
    public void setIntPref(int number) {
        editor.putInt("int", number);
        editor.commit();
    }

    public int getIntPref() {
        return mPrefs.getInt("int", 0);
    }

    // String
    public void setStringPref(String string) {
        editor.putString("string", string);
        editor.commit();
    }

    public String getStringPref() {
        return mPrefs.getString("string", "");
    }

    // boolean
    public void setBooleanPref(boolean mbool) {
        editor.putBoolean("bool", mbool);
        editor.commit();
    }

    public boolean getBooleanPref() {
        return mPrefs.getBoolean("bool", false);
    }

    // Custom Object
    public void setCustomObjectPref(Object obj) {
        editor.putString("custom obj", new Gson().toJson(obj));
        editor.commit();
    }

    public Object getCustomObjectPref() {
        try {
            return new Gson().fromJson(mPrefs.getString("custom obj", ""),
                    Object.class);
        } catch (NullPointerException e) {
            return null;
        }
    }

    // ect....

    // Clear
    public void clearSharedPrefs() {
        editor.clear();
        editor.commit();
    }

    // Different Intents >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Goes to the Activity with animation slide in right slide out left if
     * bundle != null adds bundle plus key to intent.
     * <p/>
     * to get bundle from intent be sure to call
     * getIntent().getBundleExtra(bundleKey) then get whatever from the bundle.
     *
     * @param clazz
     * @param bundle
     * @param bundleKey
     */
    public void goToActivityWithStyle(Class<?> clazz, Bundle bundle,
                                      String bundleKey) {
        Intent i = new Intent(mContext, clazz);
        if (bundle != null) {
            i.putExtra(bundleKey, bundle);
        }
        mContext.startActivity(i);
        try {
            ((Activity) mContext).overridePendingTransition(
                    R.anim.slide_in_right, R.anim.slide_out_left);
        } catch (Exception e) {

        }
    }

    /**
     * All you need to do is type in a phone number. Ex. (205)111-2222
     * (205)1112222 2051112222 205-111-2222 ect...
     * <p/>
     * Android will launch the phone dialer on the device.
     *
     * @param phoneNumber
     */
    public void callAPhone(String phoneNumber) {
        Uri number = Uri.parse("tel:" + phoneNumber);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        mContext.startActivity(callIntent);
    }

    /**
     * Put in an address and it will take you to that location in your favorite
     * map app.
     *
     * @param Address
     */
    public void GetDirectionsTo(String Address) {
        Address = Address.replaceAll(" ", "+");
        Uri location = Uri.parse("geo:0?q=" + Address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        mContext.startActivity(mapIntent);
    }

    /**
     * Put in a website URL and it will take you to that site in your favorite
     * browser app.
     *
     * @param websiteURL
     */
    public void GoToWebsite(String websiteURL) {
        Uri webpage = Uri.parse(websiteURL);
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        mContext.startActivity(webIntent);
    }

    public void CreateEmail(String[] recipients, String Title, String Text) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // The intent does not have a URI, so declare the "text/plain" MIME
        // type
        emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, Title);
        emailIntent.putExtra(Intent.EXTRA_TEXT, Text);
        mContext.startActivity(emailIntent);
    }

    /**
     * <p>Takes in a Video id from youtube and then sends you to your favorite youtube app for that video </p>
     * <p>
     * Youtube url = http://www.youtube.com/watch?v=<b>0Gb8Cx5vHCI</b></br>
     * </p>
     * <p>
     * The YouTubeUrlVideoId is <b>0Gb8Cx5vHCI</b>
     * </p>
     *
     * @param YouTubeUrlVideoId
     */
    public void PlayYouTubeVideo(String YouTubeUrlVideoId) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + YouTubeUrlVideoId));
        mContext.startActivity(i);
    }

    private static boolean isAppInstalled(String uri, Context context) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    // Fragment Utils >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Switches fragment without adding to back stack;
     *
     * @param frag
     */
    public void switchFragmentsWithStyle(int frame, Fragment frag) {
        // 4097 is equal to TRANSIT_FRAGMENT_OPEN in fragmentTransaction and
        // creates interesting animation
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(frame, frag).setTransition(
                4097);
        fragmentTransaction.commit();
    }

    /**
     * Switches fragment with adding to back stack;
     *
     * @param frag
     * @param backStackString
     */
    public void switchFragmentsWithStyle(int frame, Fragment frag, String backStackString) {
        // 4097 is equal to TRANSIT_FRAGMENT_OPEN in fragmentTransaction and
        // creates interesting animation
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(frame, frag).setTransition(
                4097);
        fragmentTransaction.addToBackStack(backStackString);
        fragmentTransaction.commit();
    }

    // Keyboard Utils >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * This is a function that is used to go to the settings activity because I
     * lost the other function. This takes in the mEntity parameter and is
     * passed to the Activity
     *
     * @param window
     */

    public void showKeyboard(Window window) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    /**
     * Shows keyboard
     *
     * @param view
     */
    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) mContext
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /**
     * hides keyboard
     *
     * @param view
     */
    public void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    // Dialog Utils >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public AlertDialog AlertMessage(int Drawable, int Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(Drawable).setMessage(Message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public AlertDialog AlertMessage(int Drawable, int Title, int Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(Drawable).setTitle(Title).setMessage(Message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public AlertDialog AlertMessage(int Drawable, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(Drawable).setMessage(Message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public AlertDialog AlertMessage(int Drawable, String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(Drawable).setTitle(Title).setMessage(Message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    /**
     * Refreshes ArrayAdapter with objects given.
     *
     * @param aa
     * @param objs
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void refreshArrayAdapterWithNewObjects(ArrayAdapter aa, ArrayList objs, Boolean clearWhatisInAdapter) {
        try {
            for (int i = 0; i < objs.size(); i++) {
                if(clearWhatisInAdapter){
                    aa.clear();
                }
                aa.add(objs.get(i));
            }
            aa.notifyDataSetChanged();
        } catch (NullPointerException e) {

            Toast.makeText(mContext,
                    "No data please check internet conntection",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This function Finishes the Activity if the back button is pressed in 2
     * Seconds
     */
    public void pressBackButtonTwiceToExit() {
        if (doubleBackToExitPressedOnce) {
            ((Activity) mContext).finish();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(mContext, "Please click BACK again to exit",
                Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;

            }
        }, PRESS_BACK_BUTTON_TWICE_TIMER);

    }

    private String dateFormat;

    // Date Formatting >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public String longDateStringStandardTime(Date date) {
        String dateStr = "";
        try {
            dateFormat = "MMM d, yyyy h:mm:ss a";
            SimpleDateFormat ft = new SimpleDateFormat(dateFormat,
                    Locale.ENGLISH);
            dateStr = ft.format(date);
        } catch (NullPointerException e) {
            dateStr = "";
        }

        return dateStr;
    }

    public String longDateStringMilitaryTime(Date date) {
        String dateStr = "";
        try {
            dateFormat = "MMM d, yyyy HH:mm:ss";
            SimpleDateFormat ft = new SimpleDateFormat(dateFormat,
                    Locale.ENGLISH);
            dateStr = ft.format(date);
        } catch (NullPointerException e) {
            dateStr = "";
        }

        return dateStr;
    }

    /**
     * Send the View that you would be looking for if it was in tablet configuration. </br></br>
     *
     * @param FrameIdYouAreLookingFor
     * @return
     * @Activity ex.</br>
     * isTabletByView(findViewById(R.id.content_frame)</br></br>
     * @Fragment ex.</br>
     * isTabletByView(v.findViewById(R.id.content_frame)**</br></br>
     * **v being the view that you inflated to get the views inside the fragment
     */
    public boolean isTabletByView(View FrameIdYouAreLookingFor) {
        if (FrameIdYouAreLookingFor == null) {
            return false;
        } else {
            return true;
        }
    }


    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * Checks if the device is currently online, works for both wifi and mobile networks.
     */
    public static boolean isOnline(Context context) {
        if (context == null)
            return false;
        boolean state = false;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null)
            state = wifiNetwork.isConnectedOrConnecting();
        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null)
            state = mobileNetwork.isConnectedOrConnecting();
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null)
            state = activeNetwork.isConnectedOrConnecting();
        return state;
    }


    // BITMAP PROCESSING >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * Takes in a bitmap and The format you wish to use and returns a Base64 Encoded string for transport.
     *
     * @param bm
     * @param format
     * @return
     */
    public String BitmapToBase64(Bitmap bm, Bitmap.CompressFormat format) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(format, 100, baos); // bm is the bitmap
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }


    // File Processing >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    /**
     * IF THE DIRECTORY IS NOT EMPTY . USE THIS FUNCTION
     */
    public static boolean deleteNon_EmptyDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteNon_EmptyDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

}
