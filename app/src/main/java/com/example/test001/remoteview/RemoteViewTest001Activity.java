package com.example.test001.remoteview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test001.R;

public class RemoteViewTest001Activity extends AppCompatActivity {
    private LinearLayout linearLayout;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras() != null) {
                RemoteViews remoteViews = intent.getExtras().getParcelable("EXTRA_REMOTE_VIEWS");
                if (remoteViews != null) {
//                    int layoutId = getResources().getIdentifier("remote_view", "layout", getPackageName());
//                    View view = getLayoutInflater().inflate(layoutId, linearLayout, false);
//                    remoteViews.reapply(RemoteViewTest001Activity.this,view);
                    View view = remoteViews.apply(RemoteViewTest001Activity.this, linearLayout);

                    linearLayout.addView(view);
                }
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remote_view_test001);
        linearLayout = findViewById(R.id.remote_view_test001_linerLayout);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MyConstants.REMOTE_ACTION");
        registerReceiver(broadcastReceiver, intentFilter);

        findViewById(R.id.remote_view_start002).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RemoteViewTest001Activity.this, RemoteViewTest002Activity.class));
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }


}
