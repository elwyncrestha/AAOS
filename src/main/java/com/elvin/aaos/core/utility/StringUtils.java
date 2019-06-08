package com.elvin.aaos.core.utility;

import java.security.SecureRandom;

public class StringUtils {

    private static final String seed = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();

    public static String generate(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i ++){
            sb.append(seed.charAt(rnd.nextInt(seed.length())));
        }
        return sb.toString();
    }

}
