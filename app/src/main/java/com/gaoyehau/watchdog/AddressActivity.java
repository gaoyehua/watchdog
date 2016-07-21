package com.gaoyehau.watchdog;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.LocationListener;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyehua.db.dao.addressDao;

/**
 * Created by gaoyehua on 2016/7/21.
 */
public class AddressActivity extends Activity {

    private  EditText et_address_queryphone;
    private  TextView et_address_location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
         et_address_queryphone =(EditText) findViewById(R.id.et_address_queryphone);
         et_address_location =(TextView) findViewById(R.id.tv_address_location);
    }
    /*
    查询号码归属地
     */
    public void query(View view) {
        //1.获取输入的号码
        String phone = et_address_queryphone.getText().toString().trim();
        //2.判断
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(getApplicationContext(),
                    "请输入要查询的号码！",Toast.LENGTH_SHORT).show();
            return;
        }
        //查询号码归属地
        String queryAddress = addressDao.querryAddress(phone,getApplicationContext());
        //判断查询到的号码是否为空
        if(!TextUtils.isEmpty(queryAddress)) {
            et_address_location.setText(queryAddress);
        }
    }
}
