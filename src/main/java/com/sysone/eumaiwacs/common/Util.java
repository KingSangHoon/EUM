package com.sysone.eumaiwacs.common;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Util {

    public static Set<String> getStringToSet(String str) {
        StringTokenizer st = new StringTokenizer(str, "|");
        Set<String> stringSet = new HashSet<String>();

        while (st.hasMoreTokens()) {
            stringSet.add(st.nextToken());
        }

        return stringSet;
    }

    public static Set<Integer> getStringToIntegerSet(String str) {
        StringTokenizer st = new StringTokenizer(str, "|");
        Set<Integer> set = new HashSet<Integer>();

        while (st.hasMoreTokens()) {
            set.add(Integer.valueOf(st.nextToken()));
        }

        return set;
    }

    public static String utf8ToLatin1(String param) {
        String encodeParam = null;

        try {
            encodeParam = new String(param.getBytes("utf-8"), "latin1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return encodeParam;
    }

    public static String latin1ToUtf8(String encodingParam) {
        String decodeParam = null;

        try {
            decodeParam = new String(encodingParam.getBytes("latin1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return decodeParam;
    }

    public static long ipToLong(String ipAddress) {

        String[] ipAddressInArray = ipAddress.split("\\.");

        long result = 0;
        for (int i = 0; i < ipAddressInArray.length; i++) {
            int power = 3 - i;
            int ip = Integer.parseInt(ipAddressInArray[i]);
            result += ip * Math.pow(256, power);
        }
        return result;
    }

    public static String longToIp(long ip) {
        StringBuilder sb = new StringBuilder(15);

        for (int i = 0; i < 4; i++) {
            sb.insert(0, Long.toString(ip & 0xff));

            if (i < 3) {
                sb.insert(0, '.');
            }
            ip = ip >> 8;
        }
        return sb.toString();
    }

    public static Set<Integer> getDeviceSetInteger(String deviceAll) {
        StringTokenizer st = new StringTokenizer(deviceAll, "|");
        Set<Integer> deviceSet = new HashSet<Integer>();

        while (st.hasMoreTokens()) {
            deviceSet.add(Integer.valueOf(st.nextToken()));
        }

        return deviceSet;
    }

    public static String formatDateToStringDateFormat(LocalDateTime date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        return strDate;
    }

    public static String formatLocalDateToStringDateFormat(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatedNow = date.format(formatter);
        return formatedNow;
    }

    public static LocalDateTime formatStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        return dateTime;
    }

    public static BigDecimal formatStringToBigDecimal(String date) {
        long formatDate;
        BigDecimal bdDate = null;
        try {
            formatDate = new SimpleDateFormat("yyyyMMddHHmmss").parse(date).getTime()/1000L;
            bdDate = new BigDecimal(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bdDate;
    }

    public static String formatBigDecimalToString(BigDecimal bdDate) {
        Long lDate = bdDate.longValue();
        Date date = new Date(lDate*1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        return strDate;
    }

    public static List<String> convertCamelToSnake(List<String> strList){

        List<String> result = new ArrayList<>();

        for(String str : strList) {
            String regex = "([a-z])([A-Z])";
            String replacement = "$1_$2";
            String value = "";
            value = str.replaceAll(regex, replacement).toLowerCase();
            result.add(value);
        }
        return result;
    }

    public static String convertArrToStrBetweenComma(ArrayList<Integer> arrItem){
        StringBuilder resultStr = new StringBuilder("");
        HashSet<String> bufHashSet = new HashSet<>();

        for(int i=0; i<arrItem.size(); i++){
            bufHashSet.add(arrItem.get(i).toString());
        }
        for(int i=0; i<bufHashSet.size(); i++){
            if(i==0){ resultStr.append(arrItem.get(i).toString()); }
            else { resultStr.append("," + arrItem.get(i).toString()); }
        }

        return resultStr.toString();
    }

    public static Set<Integer> convertStringToSet(String idsSet){
        Set<Integer> idSet = new HashSet<>();

        String[] idsArr = idsSet.split(",");
        for(String id : idsArr){ idSet.add(Integer.parseInt(id)); }

        return idSet;
    }

}
