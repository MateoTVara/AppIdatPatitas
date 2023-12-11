package pe.edu.idat.appidatpatitas.util;

import android.content.SharedPreferences;

public class SharePreferencesManager {

    private static final String APP_SETTINGS_FILE = "APP_SETTINGS";

    public SharePreferencesManager() {
    }

    public static SharedPreferences getSharedPreferences(){
        return MiApp.getContext().getSharedPreferences(APP_SETTINGS_FILE,
                MiApp.MODE_PRIVATE);
    }
    public static void setStringValue(String clave, String valor){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(clave, valor);
        editor.commit();
    }

    public static void setBooleanValue(String clave, Boolean valor){
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(clave, valor);
        editor.commit();
    }

    public static String getStringValue(String clave){
        return getSharedPreferences().getString(clave, "");
    }

    public static Boolean getBooleanValue(String clave){
        return getSharedPreferences().getBoolean(clave, true);
    }
}
