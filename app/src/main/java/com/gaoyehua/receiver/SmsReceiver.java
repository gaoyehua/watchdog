package com.gaoyehua.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by 高业华 on 2016/7/20.
 */
public class SmsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //接受解析短信
        //70汉字一条短信,71汉字两条短信
        Object[] objs = (Object[]) intent.getExtras().get("pdus");
        for (Object obj : objs) {
            //解析成SmsMessage
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) obj);
            String body = smsMessage.getMessageBody();//获取短信的内容
            String sender;//获取发件人
            sender = smsMessage.getOriginatingAddress();
             Log.i("SmsReceiver", "发件人:" + sender + "  短信内容:" + body);
            //判断短信是哪个指令
            if ("#*location*#".equals(body)) {
                //GPS追踪
                Log.i("SmsReceiver","GPS追踪");
                //拦截短信
                abortBroadcast();//拦截操作,原生android系统,国产深度定制系统中屏蔽,比如小米
            }else if("#*alarm*#".equals(body)){
                //播放报警音乐
                Log.i("SmsReceiver","播放报警音乐");
                System.out.println("播放报警音乐");
                abortBroadcast();//拦截操作,原生android系统,国产深度定制系统中屏蔽,比如小米
            }else if("#*wipedata*#".equals(body)){
                //远程删除数据
                Log.i("SmsReceiver","远程删除数据");
                abortBroadcast();//拦截操作,原生android系统,国产深度定制系统中屏蔽,比如小米
            }else if("#*lockscreen*#".equals(body)){
                //远程锁屏
                Log.i("SmsReceiver","远程锁屏");
                abortBroadcast();//拦截操作,原生android系统,国产深度定制系统中屏蔽,比如小米
            }
        }
    }
}