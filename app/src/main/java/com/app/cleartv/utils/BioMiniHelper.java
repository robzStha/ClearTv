package com.app.cleartv.utils;

import com.suprema.BioMiniAndroid;

/**
 * Created by FISHEYE on 3/17/2018.
 */

public class BioMiniHelper {

    public static void setParams(BioMiniAndroid mBioMiniHandle, BioMiniAndroid.ECODE ufa_res) {
        // set sensitivity parameter 0~7 [7:default]
        ufa_res = mBioMiniHandle.UFA_SetParameter(BioMiniAndroid.PARAM.SENSITIVITY, 7);
        // set security level parameter : 1~7 [4:default]
        ufa_res = mBioMiniHandle.UFA_SetParameter(BioMiniAndroid.PARAM.SECURITY_LEVEL, 4);
        //set timeout parameter : 0~ [10000:default]
        ufa_res = mBioMiniHandle.UFA_SetParameter(BioMiniAndroid.PARAM.TIMEOUT, 10 * 1000);
        // set fast mode parameter : 1 or 0 [1:default]
        ufa_res = mBioMiniHandle.UFA_SetParameter(BioMiniAndroid.PARAM.FAST_MODE, 1);

    }

}
