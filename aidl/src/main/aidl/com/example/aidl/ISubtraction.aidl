// IAddition.aidl
package com.example.aidl;

// Declare any non-default types here with import statements

interface ISubtraction {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

      int subtraction(int item1 ,int item2);
}
