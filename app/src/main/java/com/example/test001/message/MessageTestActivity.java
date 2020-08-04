package com.example.test001.message;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MessageTestActivity extends AppCompatActivity {
    private Messenger messenger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MessengerService.class);
        Bundle bundle = new Bundle();
        bundle.putString("tt", "88888888");
        intent.putExtras(bundle);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private static final Messenger mMessenger = new Messenger(new MyMessageHandler());

    private static class MyMessageHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.d("tbq", "handleMessage: ===============>" + msg.getData().getString("tttt"));
//            Messenger messenger = msg.replyTo;
//            Message message = Message.obtain(null, 10001);
//            Bundle bundle = new Bundle();
//            bundle.putString("tttt", "987654321");
//            try {
//                messenger.send(message);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
            Message message = Message.obtain(null, 10000);
            Bundle bundle = new Bundle();
            bundle.putString("ttt", "123456789");
            message.setData(bundle);
            message.replyTo = mMessenger;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
