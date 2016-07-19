package com.gaoyehau.watchdog;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

/**
 * Created by 高业华 on 2016/7/18.
 */
public class SetUp4Activity extends SetUpBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup4);
    }

    @Override
    public void pre_activity() {
        Intent intent =new Intent(this,SetUp3Activity.class);
        startActivity(intent);
        finish();
        //执行平移界面效果
        overridePendingTransition(R.anim.setup_enter_pre,R.anim.setup_exit_pre);
    }

    @Override
    public void next_actvity() {
        //跳转到手机防盗界面
        SharedPreferences.Editor editor =sp.edit();
        editor.putBoolean("first",false);
        editor.commit();
        //跳转到手机防盗界面
        Intent intent =new Intent(this,LostFindActivity.class);
        startActivity(intent);
        finish();

    }
}
