package com.duia.commodity.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by wangsongpeng on 2016/1/21.
 */
public class MD5 {
    public MD5() {
    }

    public static String getMD5(String s) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] e = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(e);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for (int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            return null;
        }
    }


    /**
     * @param bytes
     * @return 将二进制转换为十六进制字符输出
     */

    private static String hexStr = "0123456789ABCDEF"; //全局

    public static String BinaryToHexString(byte[] bytes) {
        String result = "";
        String hex = "";
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex;
        }
        return result;
    }


    public static byte[] getMD5(byte[] data) {
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(data);
            hash = digest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }


    public static void main(String[] args) {

//        String st = "27CEB7530B40795C4ED0DA3D505A6008app_key1CC7AE41623B68AA4DA91B1A83DA34BAformatjsonmethodkingdee.items.getpage_no1page_size100partner_idKingdeeOrder100session2B26B89094D7AB9A9500DFCFB3C3A7770shopid10000001statusonsaletimestamp2018-01-17 14:26:18v1.027CEB7530B40795C4ED0DA3D505A6008";
        String st = "27CEB7530B40795C4ED0DA3D505A6008app_key1CC7AE41623B68AA4DA91B1A83DA34BAformatjsonmethodkingdee.items.getpage_no1page_size100partner_idKingdeeOrder100session2B26B89094D7AB9A9500DFCFB3C3A7770shopid10000001statusonsaletimestamp2018-01-17 16:49:07v1.027CEB7530B40795C4ED0DA3D505A6008";
        try {
            byte[] bytes = getMD5(st.getBytes("UTF-8"));
            String a = BinaryToHexString(bytes);
            System.out.println(a);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


}