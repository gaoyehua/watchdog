package com.gaoyehau.watchdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by gaoyehua on 2016/7/21.
 */
public class AtoolsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
    }

    public void tools(View view){
        //跳转到查询号码归属地的界面
        Intent intent =new Intent(this,AddressActivity.class);
        startActivity(intent);
    }
}
