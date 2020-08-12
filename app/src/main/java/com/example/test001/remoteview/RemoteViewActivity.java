package com.example.test001.remoteview;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.test001.R;

public class RemoteViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_view);

        findViewById(R.id.remoteView_textView001).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                initNotification();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initNotification() {
        Notification notification = new NotificationCompat.Builder(RemoteViewActivity.this, "1")
                .setSmallIcon(R.mipmap.ic_launcher).build();
//        notification.tickerText = "RemoteViewActivity";
//        notification.flags = Notification.FLAG_AUTO_CANCEL;
//        notification.when = System.currentTimeMillis();
//        notification.iconLevel = Notification.BADGE_ICON_SMALL;
//        notification.
//        Intent intent = new Intent(this, RemoteViewActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(RemoteViewActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        //ChannelId为"001",ChannelName为"my_channel"
        NotificationChannel channel = new NotificationChannel("1",
                "my_channel", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.GREEN); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(1, notification);

    }
}
