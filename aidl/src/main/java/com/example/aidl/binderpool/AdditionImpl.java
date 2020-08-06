package com.example.aidl.binderpool;

import android.os.RemoteException;
import android.util.Log;

import com.example.aidl.IAddition;

public class AdditionImpl extends IAddition.Stub {

    @Override
    public int addition(int item1, int item2) throws RemoteException {
        Log.d("tbq", "addition: ============>");
        return item1 + item2;
    }
}
