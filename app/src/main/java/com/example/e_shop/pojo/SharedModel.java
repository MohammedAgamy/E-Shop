package com.example.e_shop.pojo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.e_shop.ui.fragment.SplashFragment;

import java.util.HashMap;

public class SharedModel {


    SharedPreferences mShared;
    SharedPreferences.Editor mEditor;
    Context mContext ;

    public static final String File_Name = "E-Shop";
    public static final String Key_Name = "UserName";
    public static final String Key_Emil = "Email";
    public static final String Key_Phone = "Phone";
    public static final String Key_status = "status";


    //Create File to save data
    public SharedModel(Context mContext) {
        this.mContext = mContext;
        mShared = mContext.getSharedPreferences(File_Name, Context.MODE_PRIVATE);
        mEditor = mShared.edit();
    }


    //Create method to save data
    public void SaveData(String name, String email, String Phone, boolean status) {
        mEditor.putString(Key_Name, name);
        mEditor.putString(Key_Emil, email);
        mEditor.putString(Key_Emil, Phone);
        mEditor.putBoolean(Key_status, status);
        mEditor.commit();

    }

    //Create method load data hashMap
    public HashMap<String, String> loadData() {
        HashMap<String, String> user = new HashMap<>();
        user.put(Key_Name, mShared.getString(Key_Name, null));
        user.put(Key_Emil, mShared.getString(Key_Emil, null));
        user.put(Key_Phone, mShared.getString(Key_Phone, null));
        return user;
    }

    //check if user log in
    public boolean isLogin() {
        return mShared.getBoolean(Key_status, false);
    }

    ///log out
    public void logOut() {
        mEditor.clear();
        mEditor.remove(Key_Emil);
        mEditor.remove(Key_Name);
        mEditor.apply();
        mContext.startActivity(new Intent(mContext, SplashFragment.class));

    }

}
