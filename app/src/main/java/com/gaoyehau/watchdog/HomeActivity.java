package com.gaoyehau.watchdog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
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
        gv_home_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //更具位置设置
                switch (position) {
                    case 8: //设置中心
                        Intent intent =new Intent(HomeActivity.this,SettingActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private  class Myadapter extends BaseAdapter {
        int[] imageId = { R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app,
                R.drawable.taskmanager, R.drawable.netmanager, R.drawable.trojan,
                R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings };
        String[] names = { "手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理",
                "高级工具", "设置中心" };
        //设置条目个数
        @Override
        public int getCount() {
            return 9;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //TextView textView =new TextView(getApplicationContext());
            //textView.setText("第"+position+"个条目");
            //将布局文件转化成View对象
            View view =View.inflate(getApplicationContext(),R.layout.item_home,null);

            //每个条目的样式都不一样，需要初始化样式

            ImageView iv_itemhome_icon =   (ImageView) view.findViewById(R.id.iv_itemhome_icon);
            TextView  tv_item_text =  (TextView)  view.findViewById(R.id.tv_itemhome_text);
            //逐个设置图片文本

            iv_itemhome_icon.setImageResource(imageId[position]);
            tv_item_text.setText(names[position]);

            return view;
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
