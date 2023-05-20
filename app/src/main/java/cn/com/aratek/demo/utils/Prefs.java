package cn.com.aratek.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    public String SHARED_URLAPI = "MYAPI";
    public String URLAPI = "URLAPI";

    public String TOKEN = "TOKEN";
    public String SN = "SN";
    public SharedPreferences storage;

    public Prefs(Context context) {
        storage = context.getSharedPreferences(SHARED_URLAPI, 0);
    }

    public void clear(){
        SharedPreferences.Editor editor = storage.edit();
        editor.clear();
        editor.apply();
    }

    public void saveSN(String SNP){
        storage.edit().putString(SN, SNP).apply();
    }

    public String getSN(){
        return storage.getString(SN,"");
    }

    public void saveUrl(String url) {
        storage.edit().putString(URLAPI, url).apply();
    }

    public void saveTOKEN(String token){storage.edit().putString(TOKEN,token).apply();}

    public String getTOKEN(){
        return storage.getString(TOKEN,"");
    }

    public String getUrl() {
        return storage.getString(URLAPI, "https://api.larotechs.com/v1.0/api/");
    }
}
