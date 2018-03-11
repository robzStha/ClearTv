package com.app.cleartv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import studios.codelight.smartloginlibrary.SmartLoginCallbacks;
import studios.codelight.smartloginlibrary.SmartLoginConfig;
import studios.codelight.smartloginlibrary.users.SmartUser;
import studios.codelight.smartloginlibrary.util.SmartLoginException;

public class Login extends AppCompatActivity implements SmartLoginCallbacks{

    private SmartLoginConfig config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        config = new SmartLoginConfig(this, this);
    }


    @Override
    public void onLoginSuccess(SmartUser user) {

    }

    @Override
    public void onLoginFailure(SmartLoginException e) {

    }

    @Override
    public SmartUser doCustomLogin() {
        return null;
    }

    @Override
    public SmartUser doCustomSignup() {
        return null;
    }
}
