package com.app.cleartv.interfaces;

import studios.codelight.smartloginlibrary.users.SmartUser;
import studios.codelight.smartloginlibrary.util.SmartLoginException;

public interface SmartLoginCallbacks {
        void onLoginSuccess(SmartUser user);
        void onLoginFailure(SmartLoginException e);
        SmartUser doCustomLogin();
        SmartUser doCustomSignup();
    }