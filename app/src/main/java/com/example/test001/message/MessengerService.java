package com.example.test001.message;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengerService extends Service {

    public MessengerService() {
        super();
    }

    private static class MyMessageHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.d("tbq", "handleMessage: ===============>" + msg.getData().getString("ttt"));
            Messenger messenger = msg.replyTo;
            Message message = Message.obtain(null, 10001);
            Bundle bundle = new Bundle();
            message.setData(bundle);
            bundle.putString("tttt", "987654321");
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    private static final Messenger mMessenger = new Messenger(new MyMessageHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("tbq", "onBind: ================>" + intent.getExtras().getString("tt"));
        return mMessenger.getBinder();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
