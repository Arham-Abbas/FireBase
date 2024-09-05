package com.arham.firebase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class PasswordEncryptor {
    public static String encrypt(String password) {
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] p = messageDigest.digest(password.getBytes());
            StringBuilder hexStringBuilder = new StringBuilder();
            for(byte b: p)
            {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length()==1)
                    hexStringBuilder.append('0');
                hexStringBuilder.append(hex);
            }
            return hexStringBuilder.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.getSuppressed();
            return password;
        }
    }
}