package com.gaoyehau.watchdog;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.gaoyehua.ui.SettingView;

import java.util.AbstractCollection;

/**
 * Created by 高业华 on 2016/7/17.
 */
public class SettingActivity extends Activity {

    private SettingView  sv_setting_update;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //保存文件设置

        sp =getSharedPreferences("config",MODE_PRIVATE);
        sv_setting_update =(SettingView) findViewById(R.id.sv_setting_update);
        //初始化自定义控件
        sv_setting_update.setTitle("提示更新");

        if(sp.getBoolean("update",true)) {
            sv_setting_update.setDes("打开提示信息");
            sv_setting_update.setChecked(true);
        }else {
            sv_setting_update.setDes("关闭提示信息");
            sv_setting_update.setChecked(false);
        }
        //设置自定义控件的点击事件

        sv_setting_update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = sp.edit();
                //更改状态
                //根据checkbox之前的状态来改变checkbox的状态
                if (sv_setting_update.isChecked()) {
                    //关闭提示更新
                    sv_setting_update.setDes("关闭提示更新");
                    sv_setting_update.setChecked(false);
                    //保存状态
                    edit.putBoolean("update", false);
                    //edit.apply();//保存到文件中,但是仅限于9版本之上,9版本之下保存到内存中的
                }else{
                    //打开提示更新
                    sv_setting_update.setDes("打开提示更新");
                    sv_setting_update.setChecked(true);
                    //保存状态
                    edit.putBoolean("update", true);
                }
                edit.commit();
            }
        });
    }

}
