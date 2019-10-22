package com.lv.cloud.etrack.client.context;

import java.util.UUID;

/**
 * @author xiaoyulin
 * @description
 * @date 2019-10-22
 */
public class ETrackContext {
    public static final String TRACENUMBER = "_trackNumber";
    static final ThreadLocal<String> trackNumberContext = new ThreadLocal();
    static final ThreadLocal<String> parentAppNameContext = new ThreadLocal();
    public static String appName;

    public ETrackContext() {
    }

    public static void setTrackNumber(String trackNumber) {
        trackNumberContext.set(trackNumber);
    }

    public static void setParentAppName(String parentAppName) {
        parentAppNameContext.set(parentAppName);
    }

    public static String getTrackNumber() {
        return (String)trackNumberContext.get();
    }

    public static String getParentAppName() {
        return (String)parentAppNameContext.get();
    }

    public static void remove() {
        try {
            trackNumberContext.remove();
        } catch (Exception var2) {
        }

        try {
            parentAppNameContext.remove();
        } catch (Exception var1) {
        }

    }

    public static void initTrackNumber() {
        trackNumberContext.set(getRandom18String());
    }

    private static String random() {
        return UUID.randomUUID().toString();
    }

    public static String getRandom18String() {
        Long millis = System.currentTimeMillis();
        Integer n = (int)(Math.random() * 100000.0D);
        int zeroNumber = 5 - n.toString().length();
        String pre = "";

        for(int i = 0; i < zeroNumber; ++i) {
            pre = pre + "0";
        }

        String r = millis.toString();
        if (!pre.equals("")) {
            r = r + pre + n.toString();
        } else {
            r = r + n.toString();
        }

        return Long.toHexString(Long.parseLong(r));
    }
}
