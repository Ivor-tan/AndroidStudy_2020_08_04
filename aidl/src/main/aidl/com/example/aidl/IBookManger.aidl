// IBookManger.aidl
package com.example.aidl;

// Declare any non-default types here with import statements
import com.example.aidl.Book;
import com.example.aidl.IOnNewBookListener;
interface IBookManger {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
        List<Book> getBookList();

        void addBook(in Book book);

        void registerNewBookListener(IOnNewBookListener IOnNewBookListener);

        void unregisterNewBookListener(IOnNewBookListener IOnNewBookListener);
}
