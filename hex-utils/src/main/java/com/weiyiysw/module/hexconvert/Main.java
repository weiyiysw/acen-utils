package com.weiyiysw.module.hexconvert;

public class Main {
    public static void main(String[] args) {
        String iv = "abcdefghijkmlnop1";

//        byte[] bytes = HexConvertUtil.hexToBytes(iv);
        System.out.println(HexConvertUtil.bytesToHex(iv.getBytes()));
    }
}
