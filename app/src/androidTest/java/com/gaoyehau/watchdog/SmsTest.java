package com.gaoyehau.watchdog;

import android.test.AndroidTestCase;

import com.gaoyehua.engine.SmsEngine;

/**
 * Created by gaoyehua on 2016/7/29.
 */
public class SmsTest extends AndroidTestCase {

    public void TestSms(){

        SmsEngine.getAllSms(getContext());
    }
}
