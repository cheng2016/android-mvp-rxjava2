package com.chengzj.app.util;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by chengzj on 2018/6/21.
 */
public class DeviceUtils {

    /**
     * 获取设置 imei 号
     * @param context
     * @return
     */
    public static String getDeviceIMEI(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        return imei;
    }
}
