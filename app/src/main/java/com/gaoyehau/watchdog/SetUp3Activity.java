package com.gaoyehau.watchdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.AbstractCollection;

/**
 * Created by 高业华 on 2016/7/18.
 */
public class SetUp3Activity extends SetUpBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup3);
    }

    @Override
    public void pre_activity() {
        Intent intent =new Intent(this,SetUp2Activity.class);
        startActivity(intent);
        finish();
        //执行平移界面效果
        overridePendingTransition(R.anim.setup_enter_pre,R.anim.setup_exit_pre);
    }

    @Override
    public void next_actvity() {
        //跳转到下一个界面
        Intent intent =new Intent(this,SetUp4Activity.class);
        startActivity(intent);
        finish();
        //执行平移界面效果
        overridePendingTransition(R.anim.setup_enter_next,R.anim.setup_exit_next);

    }
}

