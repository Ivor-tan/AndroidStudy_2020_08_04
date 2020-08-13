package com.example.test001.remoteview;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test001.R;

public class RemoteViewTest002Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_view_test002);
        findViewById(R.id.remote_view_test001_button001).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendView();
            }
        });

        findViewById(R.id.remote_view_test001_button002).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOtherView();
//                remoteViews.setTextViewText(R.id.remote_view_text001, "change====>");
//                remoteViews.reapply(RemoteViewTest002Activity.this,);
            }
        });

        findViewById(R.id.remote_view_test001_button003).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void sendView() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote_view);
        remoteViews.setTextViewText(R.id.remote_view_text001, "ProcessId=====>" + Process.myPid());
        PendingIntent pendingIntent = PendingIntent.getActivity(RemoteViewTest002Activity.this
                , 1000, new Intent(this, RemoteViewTest002Activity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, RemoteViewTest001Activity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.remote_view_icon_001, pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.remote_view_text001, openActivity2PendingIntent);
        Intent intent = new Intent("MyConstants.REMOTE_ACTION");
        intent.putExtra("EXTRA_REMOTE_VIEWS", remoteViews);
        sendBroadcast(intent);
    }

    private void sendOtherView() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.remote_view);
        remoteViews.setTextViewText(R.id.remote_view_text001, "ProcessUid=====>" + Process.myUid());
        PendingIntent pendingIntent = PendingIntent.getActivity(RemoteViewTest002Activity.this
                , 1000, new Intent(this, RemoteViewTest002Activity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent openActivity2PendingIntent = PendingIntent.getActivity(
                this, 0, new Intent(this, RemoteViewTest001Activity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.remote_view_icon_001, pendingIntent);
        remoteViews.setOnClickPendingIntent(R.id.remote_view_text001, openActivity2PendingIntent);
        Intent intent = new Intent("MyConstants.REMOTE_ACTION");
        intent.putExtra("EXTRA_REMOTE_VIEWS", remoteViews);
        sendBroadcast(intent);
    }

}
