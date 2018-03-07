package com.app.cleartv;

public class AppContract {
    private AppContract() {
    }


    public class Preferences {
        public final static String IS_LOGGED_IN = "is_logged_in";
        public final static String AUTHORIZATION_KEY = "authorization_key";// token generated after basic getAuthenticationToken
        public final static String ACCESS_TOKEN = "access_token";//Logged user access token
        public final static String CANDIDATE_ID = "candidate_id";
        public final static String EXPIRES_IN = "expires_in";
        public final static String REFRESH_TOKEN = "refresh_token";
        public final static String TOKEN_TYPE = "token_type";
        public final static String FIRST_NAME = "first_name";
        public final static String LAST_NAME = "last_name";
        public final static String EMAIL = "email";

    }

    public static class Extras {
        public final static String DATA = "data";
        public static final String SHOW_CROP_VIEW = "show_crop_view";
        public static final String COMPRESS_IMAGE = "compress_image";

    }

    public class Errors {
        public static final String IMAGE_ERROR = "Image not found";
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
    }

    public class FileType {
        public static final int COVER_LETTER = 1;
        public static final int RESUME = 2;
        public static final int FILE = 3;
    }

    public class OpenType {
        public static final int AFTER_LOGIN = 1;
        public static final int FORCE_COMPLETE = 2;
        public static final int SINGLE_PAGE = 3;
    }
}
