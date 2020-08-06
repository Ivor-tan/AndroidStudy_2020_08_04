package com.example.aidl.binderpool;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.aidl.IBinderPool;

import java.util.concurrent.CountDownLatch;

public class BinderPool {
    public static final int BINDER_ADD = 1;
    public static final int BINDER_SUB = 2;

    private Context mContext;
    private IBinderPool mIBinderPool;
    private static volatile BinderPool sInstance;
    private CountDownLatch mCountDownLatch;


    public BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    public static BinderPool getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                sInstance = new BinderPool(context);
            }
        }
        return sInstance;
    }

    public IBinder queryBinder(int binderCode) {
        IBinder iBinder = null;
        try {
            iBinder = mIBinderPool.queryBinder(binderCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return iBinder;
    }

    private synchronized void connectBinderPoolService() {
        mCountDownLatch = new CountDownLatch(1);
        Intent intent = new Intent();
        intent.setPackage("com.example.serviceapp");
        intent.setAction("com.example.serviceapp.action.binderpool");
        mContext.bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
        Log.d("tbq",  "connectBinderPoolService: ============>");
        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mIBinderPool = IBinderPool.Stub.asInterface(iBinder);
            try {
                mIBinderPool.asBinder().linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d("tbq", "binderDied: ===========>");
            mIBinderPool.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mIBinderPool = null;
            connectBinderPoolService();
        }
    };

    public static class BinderPoolImpl extends IBinderPool.Stub {

        public BinderPoolImpl() {
            super();
        }

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            IBinder binder = null;
            switch (binderCode) {
                case BINDER_ADD:
                    binder = new AdditionImpl();
                    break;
                case BINDER_SUB:
                    binder = new SubtractionImpl();
                    break;

                default:

                    break;
            }
            return binder;
        }
    }
}
