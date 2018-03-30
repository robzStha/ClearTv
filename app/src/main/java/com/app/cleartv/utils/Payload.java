package com.app.cleartv.utils;

/**
 * Created by Dell on 3/8/2018.
 */

public class Payload {

    private static final String tempValue = "Empty";

    public static String applicantPhoto = "";
    public static String boxPhoto = "";
    public static String cardPhoto = "";
    public static String boxCardPhoto = "";
    public static String applicantSign = "";
    public static String fingerPrintRight = tempValue;
    public static String fingerPrintLeft = tempValue;
    public static String tncPhoto = tempValue;

    public static void reset() {
        applicantPhoto = "";
        boxPhoto = "";
        cardPhoto = "";
        boxCardPhoto = "";
        applicantSign = "";
        fingerPrintRight = tempValue;
        fingerPrintLeft = tempValue;
        tncPhoto = tempValue;
    }
}
