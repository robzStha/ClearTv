package com.app.cleartv.utils;

import com.slmyldz.random.Randoms;

/**
 * Created by Dell on 3/8/2018.
 */

public class Payload {

    // to ignore validation the value sent to server must have some text so currently a temporary variable is set.
    private static final String tempValue = "Empty";

    public static String applicantPhoto = "";
    public static String boxPhoto = "";
    public static String cardPhoto = "";
    public static String boxCardPhoto = "";
    public static String applicantSign = "";
    public static String fingerPrintRight = tempValue;
    public static String fingerPrintLeft = tempValue;
    public static String tncPhoto = tempValue;
    public static String identityPhoto = tempValue;

    public static void reset() {
        applicantPhoto = "";
        boxPhoto = "";
        cardPhoto = "";
        boxCardPhoto = "";
        applicantSign = "";
        fingerPrintRight = tempValue;
        fingerPrintLeft = tempValue;
        tncPhoto = tempValue;
        identityPhoto = tempValue;
    }

    public static void fakeInit(String image) {
        applicantPhoto = image;
        boxPhoto = Randoms.alphaNumericString(10);
        cardPhoto = Randoms.alphaNumericString(10);
        boxCardPhoto = image;
        applicantSign = image;
        fingerPrintRight = image;
        fingerPrintLeft = image;
        tncPhoto = image;
        identityPhoto = image;
    }
}
