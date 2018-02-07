package com.online.college.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class CommonUtil {

    public static String getUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String stringCap(String str){
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }

    public static byte[][] toArray(List<byte[]> list){
        byte[][] bytes = new byte[list.size()][];

        for(int i = 0; i < list.size() ;i++){
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    public static byte[] getFileBytes(File file) {
        byte[] ret = null;

        try {
            if (file == null) {
                return null;
            }
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
            byte[] b = new byte[4096];
            int n;

            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            in.close();
            out.close();
            ret = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

}
