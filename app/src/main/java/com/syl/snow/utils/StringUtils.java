package com.syl.snow.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Bright on 2018/12/8.
 *
 * @Describe
 * @Called
 */
public class StringUtils {
    /**
     * 输入流转化为String
     * @param inputStream 输入流
     * @return
     */
    public static String inputStream2String(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error==" + e.toString());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                System.out.println("Error==" + e.toString());
            }
        }
        return sb.toString();
    }
}
