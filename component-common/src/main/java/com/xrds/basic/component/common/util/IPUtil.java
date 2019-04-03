/**
 * 
 * 坤普 Copyright (c) 2016-2017 KunPu,Inc.All Rights Reserved.
 */
package com.xrds.basic.component.common.util;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author liukai
 * @version $Id: IPUtil.java, v 0.1 2017年9月5日 上午11:46:47 liukai Exp $
 */
public class IPUtil {

    private static final Pattern IPV4_PATTERN =
            Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    private static final Pattern IPV6_STD_PATTERN = Pattern
            .compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");

    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN =
            Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");

    public static boolean isIPv4Address(String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }

    public static boolean isIPv6StdAddress(String input) {
        return IPV6_STD_PATTERN.matcher(input).matches();
    }

    /**
     * 校验IP合法性
     * 
     * @param addr
     * @return
     */
    public static boolean isIP(String addr) {
        return (isIPv4Address(addr) || isIPv6StdAddress(addr));
    }



    /** ip转整形 **/
    public static int ip2Int(String ipAddress) {
        int result = 0;
        String[] ipAddressInArray = ipAddress.split("\\.");

        for (int i = 3; i >= 0; i--) {
            long ip = Integer.parseInt(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);
        }
        return result;
    }

    /**
     * 整型转ip
     * 
     * @param ip
     * @return
     */
    public static String int2Ip(int ip) {
        return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + ((ip >> 8) & 0xFF) + "."
                + (ip & 0xFF);
    }

    /**
     * 
     * 
     * @param ipv6
     * @return
     */
    public static BigInteger ipv6toInt(String ipv6) {

        int compressIndex = ipv6.indexOf("::");
        if (compressIndex != -1) {
            String part1s = ipv6.substring(0, compressIndex);
            String part2s = ipv6.substring(compressIndex + 1);
            BigInteger part1 = ipv6toInt(part1s);
            BigInteger part2 = ipv6toInt(part2s);
            int part1hasDot = 0;
            char ch[] = part1s.toCharArray();
            for (char c : ch) {
                if (c == ':') {
                    part1hasDot++;
                }
            }
            // ipv6 has most 7 dot
            return part1.shiftLeft(16 * (7 - part1hasDot)).add(part2);
        }
        String[] str = ipv6.split(":");
        BigInteger big = BigInteger.ZERO;
        for (int i = 0; i < str.length; i++) {
            // ::1
            if (str[i].isEmpty()) {
                str[i] = "0";
            }
            big =
                    big.add(BigInteger.valueOf(Long.valueOf(str[i], 16)).shiftLeft(
                            16 * (str.length - i - 1)));
        }
        return big;
    }

    public static String int2ipv6(BigInteger big) {
        String str = "";
        BigInteger ff = BigInteger.valueOf(0xffff);
        for (int i = 0; i < 8; i++) {
            str = big.and(ff).toString(16) + ":" + str;

            big = big.shiftRight(16);
        }
        // the last :
        str = str.substring(0, str.length() - 1);

        return str.replaceFirst("(^|:)(0+(:|$)){2,8}", "::");
    }


    /**
     * 将字符串形式的ip地址转换为BigInteger
     * 
     * @param ipInString 字符串形式的ip地址
     * @return 整数形式的ip地址
     */
    public static BigInteger StringToBigInt(String ipInString) {
        ipInString = ipInString.replace(" ", "");
        byte[] bytes;
        if (ipInString.contains(":"))
            bytes = ipv6ToBytes(ipInString);
        else
            bytes = ipv4ToBytes(ipInString);
        return new BigInteger(bytes);
    }

    /**
     * 将整数形式的ip地址转换为字符串形式
     * 
     * @param ipInBigInt 整数形式的ip地址
     * @return 字符串形式的ip地址
     */
    public static String BigIntToString(BigInteger ipInBigInt) {
        byte[] bytes = ipInBigInt.toByteArray();
        byte[] unsignedBytes = Arrays.copyOfRange(bytes, 1, bytes.length);
        // 去除符号位
        try {
            String ip = InetAddress.getByAddress(unsignedBytes).toString();
            return ip.substring(ip.indexOf('/') + 1).trim();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ipv6地址转有符号byte[17]
     */
    private static byte[] ipv6ToBytes(String ipv6) {
        byte[] ret = new byte[17];
        ret[0] = 0;
        int ib = 16;
        boolean comFlag = false;// ipv4混合模式标记
        if (ipv6.startsWith(":")) // 去掉开头的冒号
            ipv6 = ipv6.substring(1);
        String groups[] = ipv6.split(":");
        for (int ig = groups.length - 1; ig > -1; ig--) {// 反向扫描
            if (groups[ig].contains(".")) {
                // 出现ipv4混合模式
                byte[] temp = ipv4ToBytes(groups[ig]);
                ret[ib--] = temp[4];
                ret[ib--] = temp[3];
                ret[ib--] = temp[2];
                ret[ib--] = temp[1];
                comFlag = true;
            } else if ("".equals(groups[ig])) {
                // 出现零长度压缩,计算缺少的组数
                int zlg = 9 - (groups.length + (comFlag ? 1 : 0));
                while (zlg-- > 0) {// 将这些组置0
                    ret[ib--] = 0;
                    ret[ib--] = 0;
                }
            } else {
                int temp = Integer.parseInt(groups[ig], 16);
                ret[ib--] = (byte) temp;
                ret[ib--] = (byte) (temp >> 8);
            }
        }
        return ret;
    }

    /**
     * ipv4地址转有符号byte[5]
     */
    private static byte[] ipv4ToBytes(String ipv4) {
        byte[] ret = new byte[5];
        ret[0] = 0;
        // 先找到IP地址字符串中.的位置
        int position1 = ipv4.indexOf(".");
        int position2 = ipv4.indexOf(".", position1 + 1);
        int position3 = ipv4.indexOf(".", position2 + 1);
        // 将每个.之间的字符串转换成整型
        ret[1] = (byte) Integer.parseInt(ipv4.substring(0, position1));
        ret[2] = (byte) Integer.parseInt(ipv4.substring(position1 + 1, position2));
        ret[3] = (byte) Integer.parseInt(ipv4.substring(position2 + 1, position3));
        ret[4] = (byte) Integer.parseInt(ipv4.substring(position3 + 1));
        return ret;
    }


    /*
     * 字节数组转16进制字符串
     */
    public static String bytes2HexString(byte[] b) {
        String r = "";

        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            r += hex.toUpperCase();
        }

        return r;
    }



    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        String ipv6 = "2409:8805:8393:bbbf:9f93:6a8b:465e:723";
        List<String> list = new ArrayList<String>();
        // list.add(ipv6);
        list.add("2409:8805:8290:3760:486d:cdea:5dd9:d6de");
        // list.add("2409:880b:40:b7f8:dc68:366a:e39c:f74e");
        // list.add("2409:880b:9380:799b:ca0b:a72e:bbb2:7650");
        // list.add("2409:8809:8331:fc60:be35:eb4b:494d:9c70");
        // list.add("2409:8805:8522:16fb:c476:dc1a:3d23:7684");
        // list.add("2409:8805:8600:e76a:d4a0:7f2a:6cff:975b");
        // list.add("2409:8809:83d1:d80a:780d:19f4:1ee1:5362");
        // list.add("2409:8809:8361:b85c:86fe:28d0:d9d7:d059");
        // list.add("2409:8805:8638:5118:b86:a3:4c8c:442d");
        // list.add("2409:880b:8102:e842:7ad3:9781:91ac:7991");
        // list.add("2409:8807:a0a1:548b:27bd:714d:fda8:e7e3");
        for (String strTmp : list) {

            System.out.println(bytes2HexString(ipv6ToBytes(strTmp)));
            // BigInteger tmp = ipv6toInt(strTmp);
            // System.out.println("" + tmp);
            //
            // System.out.println(int2ipv6(tmp));



        }



    }

}
