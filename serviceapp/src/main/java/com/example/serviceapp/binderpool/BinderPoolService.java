package com.example.serviceapp.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.aidl.binderpool.BinderPool;

public class BinderPoolService extends Service {

    private Binder mBinderPool = new BinderPool.BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("tbq", "BinderPoolService  ==============>onBind: ");
        return mBinderPool;
    }
}
