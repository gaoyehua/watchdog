package com.gaoyehau.watchdog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by 高业华 on 2016/7/16.
 */
public class HomeActivity extends Activity {

    private GridView gv_home_gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局
        setContentView(R.layout.activity_home);
         gv_home_gridview = (GridView) findViewById(R.id.gv_home_gridview);
        gv_home_gridview.setAdapter(new Myadapter());
    }

    private  class Myadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 9;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView =new TextView(getApplicationContext());
            textView.setText("第"+position+"个条目");

            return textView;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }
}
