package ru.sample.elestatte.test65apps.utility;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ru.sample.elestatte.test65apps.activity.MainActivityDelegate;

/**
 * Utility class with useful functions for convenience
 *
 * @author Shramko Alexey
 *         Date: 21.12.17
 */
public final class Utils {

    public static String getChecksum(Serializable object)
            throws IOException, NoSuchAlgorithmException {

        try (ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();
             ObjectOutputStream oOutStream = new ObjectOutputStream(bOutStream)) {

            oOutStream.writeObject(object);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(bOutStream.toByteArray());
            return new String(bytes, "UTF8");
        }
    }

    public static <T> List<T> convertSparseToList(SparseArray<T> sparseArray) {
        if (null == sparseArray) {
            return null;
        }
        List<T> arrayList = new ArrayList<>(sparseArray.size());

        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.valueAt(i));
        }

        return arrayList;
    }

    public static void replaceFragmentById(Activity activity, int id) {
        MainActivityDelegate delegate = (MainActivityDelegate) activity;
        if (null != delegate) {
            delegate.replaceFragmentById(id);
        }
    }

    public static void addFragment(Activity activity, Fragment fragment) {
        MainActivityDelegate delegate = (MainActivityDelegate) activity;
        if (null != delegate) {
            delegate.addFragment(fragment);
        }
    }

    public static String capitalize(String str) {
        if (null != str && !str.isEmpty()) {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
        return "";
    }

    public static String getAgeFromStrDate(String str) {
        if (null != str && !str.isEmpty()) {
            Date birthday = getDateFromString(str);
            if (null != birthday) {
                Calendar source = getCalendar(birthday);
                Calendar current = getCalendar(new Date());
                int diff = current.get(Calendar.YEAR) - source.get(Calendar.YEAR);
                if (source.get(Calendar.MONTH) > current.get(Calendar.MONTH) ||
                    (source.get(Calendar.MONTH) == current.get(Calendar.MONTH) &&
                     source.get(Calendar.DATE) > current.get(Calendar.DATE))) {
                    diff--;
                }
                if (0 < diff) {
                    return String.valueOf(diff);
                }
            }
        }
        return "";
    }

    private static Date getDateFromString(String str) {
        if (null != str && !str.isEmpty()) {
            DateFormat format1 = new SimpleDateFormat("yyyy-mm-dd", Locale.getDefault());
            DateFormat format2 = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault());
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = format1.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                date2 = format2.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (null != date1 && date1.getTime() > 0) {
                return date1;
            }
            if (null != date2 && date2.getTime() > 0) {
                return date2;
            }
        }
        return null;
    }

    private static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        return cal;
    }
}
