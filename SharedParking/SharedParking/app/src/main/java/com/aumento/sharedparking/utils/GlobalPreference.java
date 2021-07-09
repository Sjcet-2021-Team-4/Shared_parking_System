package com.aumento.sharedparking.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MyPc on 09-09-2017.
 */

public class GlobalPreference {


    private SharedPreferences prefs;
    private Context context;
    SharedPreferences.Editor editor;

    public GlobalPreference(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void addIP(String ip) {
        editor.putString(Constants.IP, ip);
        editor.apply();
    }

    public String RetriveIP() {
        return prefs.getString(Constants.IP, "");
    }

    public void addUID(String uid) {
        editor.putString(Constants.UID, uid);
        editor.apply();
    }

    public String RetriveUID() {
        return prefs.getString(Constants.UID, "");
    }

    public void addCropResponse(String cropListResponse) {
        editor.putString(Constants.CRes, cropListResponse);
        editor.apply();
    }

    public String RetriveCrop() {
        return prefs.getString(Constants.CRes, "");
    }



    public void setLoginStatus(Boolean status)     {
        editor.putBoolean(Constants.LOGIN_STATUS, status);
        editor.apply();
    }

    public Boolean getLoginStatus()
    {
        return prefs.getBoolean(Constants.LOGIN_STATUS,false);
    }

}