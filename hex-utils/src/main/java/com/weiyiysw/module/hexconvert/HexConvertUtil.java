package com.weiyiysw.module.hexconvert;

public class HexConvertUtil {
    public static String bytesToHex(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            String hv = Integer.toHexString(src[i] & 0xFF);
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }

    // hex length must be even
    // throw NumberFormatException
    public static byte[] hexToBytes(String hex) {
        if (hex.length() % 2 != 0) {
            return null;
        }
        byte[] result = new byte[hex.length()/2];
        for (int i = 0; i < hex.length(); i+=2) {
            result[i/2] = Byte.parseByte(hex.substring(i, i+2), 16);
        }
        return result;
    }
}
