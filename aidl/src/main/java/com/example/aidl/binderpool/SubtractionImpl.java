package com.example.aidl.binderpool;

import android.os.RemoteException;
import android.util.Log;

import com.example.aidl.ISubtraction;

public class SubtractionImpl extends ISubtraction.Stub {
    @Override
    public int subtraction(int item1, int item2) throws RemoteException {
        Log.d("tbq", "subtraction: ============>");
        return item1 - item2;
    }
}
