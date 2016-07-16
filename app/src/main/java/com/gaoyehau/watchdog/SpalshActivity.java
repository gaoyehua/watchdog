package com.gaoyehau.watchdog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyehua.utils.StreamUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SpalshActivity extends Activity {



    protected static final int MSG_UPDATE_DIALOG = 1;
    protected static final int MSG_ENTER_HOME = 2;
    protected static final int MSG_SERVER_ERROR = 3;
    protected static final int MSG_URL_ERROR = 4;
    protected static final int MSG_IO_ERROR = 5;
    protected static final int MSG_JSON_ERROR = 6;
    private String code;
    private String apkurl;
    private String des;
    private TextView tv_spalsh_versionname;

    private Handler handler =new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_UPDATE_DIALOG:

                    showdialog();

                    break;
                case MSG_ENTER_HOME:

                    enterhome();

                    break;
                case MSG_SERVER_ERROR:
                    //连接失败,服务器出现异常
                    Toast.makeText(getApplicationContext(), "服务器异常", 0).show();
                    enterhome();
                    break;
                case MSG_IO_ERROR:
                    Toast.makeText(getApplicationContext(), "亲,网络没有连接..", 0).show();
                    enterhome();
                    break;
                case MSG_URL_ERROR:
                    //方便我们后期定位异常
                    Toast.makeText(getApplicationContext(), "错误号:"+MSG_URL_ERROR, 0).show();
                    enterhome();
                    break;
                case MSG_JSON_ERROR:
                    Toast.makeText(getApplicationContext(), "错误号:"+MSG_JSON_ERROR, 0).show();
                    enterhome();
                    break;

            }
        }

    };

    //弹出对话框
    protected void showdialog() {
        //显示对话框界面
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        //对话框不能消失
        builder.setCancelable(false);
        //对话框标题
        builder.setTitle("版本号："+code);
        builder.setIcon(R.mipmap.ic_launcher);
        //设置对话框的描述信息
        builder.setMessage(des);
        //设置升级取消按钮
        builder.setPositiveButton("升级",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                download();
            }

        });

            builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                //1.隐藏对话框
                dialog.dismiss();
                 //2.跳转到主界面
                 enterhome();
            }
        });

        builder.show();


    }
    protected void download() {

    }
    //进入主界面
    protected void enterhome() {

        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        //
        finish();

    }

    //提醒用户更新版本
    private void update() {
        /*
        4.0以后联网操作，耗时间长，不允许在主线程中执行，要放在子线程中执行
         */
        new Thread() {

            private int startTime;

          public void run() {

              Message message=Message.obtain();
              //在链接之前获取一个时间
              startTime= (int)System.currentTimeMillis();

              try {
                  //1.链接服务器
                  //2.设置路径
                  URL url =new URL("http://127.0.0.1/data.html");
                  //3.获取链接操作
                  HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                  //http协议
                  //4.设置超时时间
                  conn.setConnectTimeout(5000);//设置连接超时时间
                  //conn.setReadTimeout(5000) ;// 设置读取超时时间
                  //5.设置请求方式
                   conn.setRequestMethod("GET");
                  //6.获取服务器返回的状态码：200,404，，，
                  int responseCode =conn.getResponseCode();
                  if(responseCode == 200) {
                      //服务器连接成功：
                      //code: 新版本的版本号
                      //apkurl：新版本的下载连接
                      //des：   新版本的描述信息
                      //获取数据之前要先知道数据是怎么封装的 封装格式：xml，json
                      System.out.println("服务器连接成功...");
                      //获取服务器返回的流信息
                      InputStream in =conn.getInputStream();
                      //将获取的流信息转化为字符串
                      String json = StreamUtil.parserStreamUtil(in);
                      //解析json格式数据
                      JSONObject jsonObject =new JSONObject(json);
                      //获取数据
                       code = jsonObject.getString("code");
                       apkurl =jsonObject.getString("apkurl");
                       des =jsonObject.getString("des");
                      System.out.println("code:"+code+"apkurl:"+apkurl+"des:"+des);
                      //查看是否有最新版本
                      if(code.equals(getVersionName())) {
                          //跳转到其他界面
                          message.what =MSG_ENTER_HOME;
                      }
                      else{
                          //显示对话框
                          message.what =MSG_UPDATE_DIALOG;
                      }
                  }
                  else {
                          System.out.println("服务器连接失败...");
                          message.what =    MSG_SERVER_ERROR;
                      }

              } catch (MalformedURLException e) {
                  e.printStackTrace();
                  message.what =MSG_URL_ERROR;
              } catch (IOException e) {
                  e.printStackTrace();
                  message.what =MSG_IO_ERROR;

              } catch (JSONException e) {
                  e.printStackTrace();
                  message.what =MSG_JSON_ERROR ;
              }finally {

                  int endTime =(int) System.currentTimeMillis();
                  int dTime =endTime-startTime;
                  if(dTime<2000){
                      SystemClock.sleep(2000-dTime);
                  }
                  handler.sendMessage(message);

              }

          };



        }.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        TextView tv_spalsh_versionname= (TextView)
                findViewById(R.id.tv_splash_versionname);
        tv_spalsh_versionname.setText("版本号："+getVersionName());

        update();
    }
   /*
    *获取应用的版本号
   *
    */
    private String getVersionName() {
        /*
        包的管理者，获取包的信息
         */
        PackageManager pm= getPackageManager();
        try {
            PackageInfo packageInfo= pm.getPackageInfo(getPackageName(),0);
            //获取版本号
            String versionName =packageInfo.versionName;
            return versionName;

        } catch (PackageManager.NameNotFoundException e) {
            //异常：包名找不到
            e.printStackTrace();
        }

        return null;
    }
}
