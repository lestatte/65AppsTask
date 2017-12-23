package ru.sample.elestatte.test65apps.utility;

import android.app.Activity;
import android.util.SparseArray;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import ru.sample.elestatte.test65apps.activity.MainActivityDelegate;

/**
 * Utility class with useful functions for convenience
 *
 * @author Shramko Alexey
 *         Date: 21.12.17
 */
@SuppressWarnings("unused")
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
}
