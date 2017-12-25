package ru.sample.elestatte.test65apps.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Utility class for work with preferences
 *
 * @author Shramko Alexey
 *         Date: 23.12.17
 */
public final class PrefManager {

    private static final String KEY = "catalog_checksum";

    private static SharedPreferences getSettings(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    private static SharedPreferences.Editor getSettingsEditor(Context context) {
        return getSettings(context).edit();
    }

    @SuppressWarnings("SameParameterValue")
    private static String readStrPref(Context context, String key, String def) {
        return getSettings(context).getString(key, def);
    }

    private static void writeStrPref(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSettingsEditor(context);
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Read value from checksum preference.
     *
     * @param context Context from which read setting
     * @return value of checksum preference
     */
    public static String readChecksum(Context context) {
        return readStrPref(context, KEY, "");
    }

    /**
     * Write value to checksum preference.
     *
     * @param context Context from which change setting
     * @param value new value for checksum
     */
    public static void writeChecksum(Context context, String value) {
        writeStrPref(context, KEY, value);
    }
}