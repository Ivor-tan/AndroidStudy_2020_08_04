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
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.test001.MainActivity;
import com.example.test001.R;

import java.util.Random;

public class RemoteViewActivity extends AppCompatActivity {

    private RemoteViews remoteViews;
    private NotificationManager manager;
    private Notification notification;

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
        findViewById(R.id.remoteView_textView002).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RemoteViewActivity.this, RemoteViewTest001Activity.class));
            }
        });
        findViewById(R.id.remoteView_textView003).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remoteViews.setTextViewText(R.id.remote_view_text001, "==========" + new Random().nextInt(10));

//                remoteViews.reapply();
//                TextView textView = new TextView(RemoteViewActivity.this);
//                remoteViews.reapply(RemoteViewActivity.this, textView);
//                manager.notify();
                manager.notify(1, notification);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initNotification() {
        remoteViews = new RemoteViews(getPackageName(), R.layout.remote_view);
        notification = new NotificationCompat.Builder(RemoteViewActivity.this, "1")
                .setCustomContentView(remoteViews)
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
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(1, notification);

    }
}
