package ru.sample.elestatte.test65apps.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class with useful functions for convenience
 *
 * @author Shramko Alexey
 *         Date: 21.12.17
 */
@SuppressWarnings("unused")
public class Utils {

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

}
