// 
// 
// 

package com.util;

import java.security.MessageDigest;

public class Md5
{
    private static final String[] hexDigits;
    
    static {
        hexDigits = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
    }
    
    private static String byteArrayToHexString(final byte[] b) {
        final StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; ++i) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    private static String byteToHexString(final byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        final int d1 = n / 16;
        final int d2 = n % 16;
        return String.valueOf(Md5.hexDigits[d1]) + Md5.hexDigits[d2];
    }
    
    public static String MD5HexEncode(final String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            final MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }
        catch (Exception ex) {}
        return resultString;
    }
}
