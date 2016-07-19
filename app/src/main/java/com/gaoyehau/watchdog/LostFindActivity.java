package com.gaoyehau.watchdog;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 高业华 on 2016/7/18.
 */
public class LostFindActivity extends Activity {

    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp =getSharedPreferences("comfig",MODE_PRIVATE);
        //分为两个部分，1.第一次进入进行手机防盗的设置；2.之后的手机防盗界面

        //判断是否第一次进入手机防盗界面
        if(sp.getBoolean("first",true)){
            //第一次进入该界面

            Intent intent =new Intent(this,SetUp1Activity.class);
            startActivity(intent);
            finish();

        }else {
            //再次进入界面
            setContentView(R.layout.activity_lostfind);

        }

    }
    //重新进入手机防盗引导设置
    public void resetup(View view) {
        Intent intent =new Intent(this,SetUp1Activity.class);
        startActivity(intent);
        finish();
    }

}
