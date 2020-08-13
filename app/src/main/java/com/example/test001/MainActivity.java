package com.example.test001;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Observable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test001.message.MessageTestActivity;
import com.example.test001.remoteview.RemoteViewActivity;
import com.example.test001.remoteview.RemoteViewTest001Activity;
import com.example.test001.remoteview.RemoteViewTest002Activity;

public class MainActivity extends AppCompatActivity {

    Test001 test001 = new Test001();
    Boolean test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("tbq", "onCreate: =============savedInstanceState==========" + (savedInstanceState == null));
        findViewById(R.id.textView001).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                finish();
                startActivity(new Intent(MainActivity.this, GridViewActivity.class));
//                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        });
//    <action android:name="android.vehicle.intent.action.BLUETOOTH_SETTINGS" />
//    <action android:name="android.vehicle.intent.action.WIFI_SETTINGS" />
//    <action android:name="android.vehicle.intent.action.DISPLAY_SETTINGS" />
//    <action android:name="android.vehicle.intent.action.AUDIO_SETTINGS" />
//    <action android:name="android.vehicle.intent.action.SYSTEM_SETTINGS" />
//    <action android:name="android.vehicle.intent.action.CLEAN_SETTINGS" />
//    <action android:name="android.vehicle.intent.action.RESET_SETTINGS" />
//    <action android:name="android.vehicle.intent.action.ABOUT_SETTINGS" />
//        test001.setString("testasasasasasasasasasa");
//        findViewById(R.id.textView002).setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//            @Override
//            public void onClick(View view) {
////                Log.d("tbq", "onCreate: ======1111111111");
//                try {
//                    Field fields = Test001.class.getDeclaredField("string");
//                    fields.setAccessible(true);
//                    Log.d("tbq", "createHasConfigDialog: ==========>" + fields.get(test001));
//                } catch (NoSuchFieldException | IllegalAccessException e) {
//                    e.printStackTrace();
//                    Log.d("tbq", "createHasConfigDialog: catch ==========>" + e.getMessage());
//                }
////                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            }
//        });
        findViewById(R.id.textView002).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.oushang.systemsettings", "com.oushang.systemsettings.view.AppMainActivity"));
                startActivity(intent);

            }
        });

        findViewById(R.id.textView003).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.vehicle.intent.action.AUDIO_SETTINGS");
                intent.setComponent(new ComponentName("com.oushang.systemsettings", "com.oushang.systemsettings.view.AppMainActivity"));
                startActivity(intent);
            }
        });
        findViewById(R.id.textView004).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.vehicle.intent.action.SYSTEM_SETTINGS");
                intent.setComponent(new ComponentName("com.oushang.systemsettings", "com.oushang.systemsettings.view.AppMainActivity"));
                startActivity(intent);
            }
        });
        findViewById(R.id.textView005).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.vehicle.intent.action.ABOUT_SETTINGS");
                intent.setComponent(new ComponentName("com.oushang.systemsettings", "com.oushang.systemsettings.view.AppMainActivity"));
                startActivity(intent);
            }
        });
        findViewById(R.id.textView006).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MessageTestActivity.class));
            }
        });
        findViewById(R.id.textView007).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RemoteViewActivity.class));
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("savedInstanceState", "savedInstanceState");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}