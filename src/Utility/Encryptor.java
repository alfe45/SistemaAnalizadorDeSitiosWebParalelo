package Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    public static String MD2 = "MD2";
    public static String MD5 = "MD5";
    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String SHA384 = "SHA-384";
    public static String SHA512 = "SHA-512";

    public static String encrypt(String mensaje, String algoritmo) throws NoSuchAlgorithmException {
        byte[] digest = null;
        byte[] buffer = mensaje.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance(algoritmo);
        messageDigest.reset();
        messageDigest.update(buffer);
        digest = messageDigest.digest();
        return toHexadecimal(digest);
    }//encrypt
    
    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }//toHexadecimal

    

}//class
