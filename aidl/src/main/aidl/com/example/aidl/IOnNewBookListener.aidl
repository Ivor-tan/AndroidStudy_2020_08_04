// IOnNewBookListener.aidl
package com.example.aidl;

// Declare any non-default types here with import statements
import com.example.aidl.Book;

interface IOnNewBookListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
    void onNewBookArrived(in Book book);
}
