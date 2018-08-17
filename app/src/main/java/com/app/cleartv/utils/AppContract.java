package com.app.cleartv.utils;

public class AppContract {
    private AppContract() {
    }


    public class Preferences {
        public final static String ACCESS_TOKEN = "access_token";//Logged user access token
        public final static String APPLICANT_ID = "applicant_id"; //Applicant id
        public final static String USER_ID = "user_id"; //Logged user id
        public final static String EXPIRES_IN = "expires_in";
        public final static String REFRESH_TOKEN = "refresh_token";

    }

    public static class Extras {
        public final static String DATA = "data";
        public static final String SHOW_CROP_VIEW = "show_crop_view";
        public static final String COMPRESS_IMAGE = "compress_image";

    }

    public class Errors {
        public static final String IMAGE_ERROR = "Image not found";
        public static final String BOX_CARD = "Either primary box# or child box1# of applicant is needed.";
        public static final String BOX = "Please take a photo for box number";
        public static final String CARD = "Please take a photo for card number";
        public static final String APPLICANT_PHOTO = "Please take a photo for applicant";
        public static final String FINGERPRINT = "Both right & left fingerprint of applicant is needed.";
        public static final String SIGNATURE = "Please provide applicant's signatures";
        public static final String EMAIL = "Please enter a valid email";
        public static final String TOLE_STREET = "Please enter the tole / street name of applicant";
        public static final String TITLE = "Please enter the title";
        public static final String APPLICANT_NAME = "Please enter the applicant's name";
        public static final String NATIONALITY = "Please enter the applicant's nationality";
        public static final String IDENTIFICATION = "Please enter the identification detail of applicant";
        public static final String CONTACT = "Please enter the contact number of applicant.";
    }

    public class Permission {
        public static final int CAMERA = 0;
        public static final int GALLERY = 1;
        public static final int LOCATION = 2;
        public static final int CALL_PHONE = 3;
    }

    public class RequestCode {
        public static final int CAMERA = 100;
        public static final int GALLERY = 101;
        public static final int SIGNATURE = 102;
        public static final int BOX_CODE = 103;
        public static final int CARD_CODE = 104;
        public static final int CARD_CODE2 = 105;
    }

    public class PARAMS {
        public static final String USB_DEVICE_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
        public static final String USB_DEVICE_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    }


    public class AppMode {
        public static final int ALL = 0;
        public static final int NO_BARCODE = 1;
        public static final int NO_SIGNATURE = 2;
        public static final int NO_BARCODE_NO_SIGNATURE = 3;
    }
//    public class FileType {
//        public static final int COVER_LETTER = 1;
//        public static final int RESUME = 2;
//        public static final int FILE = 3;
//    }
//
//    public class OpenType {
//        public static final int AFTER_LOGIN = 1;
//        public static final int FORCE_COMPLETE = 2;
//        public static final int SINGLE_PAGE = 3;
//    }

}
