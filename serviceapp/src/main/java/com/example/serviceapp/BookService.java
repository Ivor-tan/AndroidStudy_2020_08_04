package com.example.serviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.LongDef;
import androidx.annotation.Nullable;

import com.example.aidl.Book;
import com.example.aidl.IBookManger;
import com.example.aidl.IOnNewBookListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookService extends Service {

    private CopyOnWriteArrayList<Book> mBookList = new CopyOnWriteArrayList<Book>();
    private RemoteCallbackList<IOnNewBookListener> iOnNewBookListeners = new RemoteCallbackList<IOnNewBookListener>();

    private IBinder mIBinder = new IBookManger.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
//            Log.d("tbq", "BookService     getBookList:===========> " + mBookList.get(0).getName());
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
//            Log.d("tbq", "BookService     addBook:===========> " + book.getName());
            mBookList.add(book);
        }

        @Override
        public void registerNewBookListener(IOnNewBookListener IOnNewBookListener) throws RemoteException {
            iOnNewBookListeners.register(IOnNewBookListener);
            Log.d("tbq", "IOnNewBookListener: ==============>");
        }

        @Override
        public void unregisterNewBookListener(IOnNewBookListener IOnNewBookListener) throws RemoteException {
            iOnNewBookListeners.unregister(IOnNewBookListener);
            Log.d("tbq", "unregisterNewBookListener: ==============>");

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("tbq", "BookService     onCreate:===========> ");
        mBookList.add(new Book("name=======>111111"));
        mBookList.add(new Book("name=======>22222222"));
        mBookList.add(new Book("name=======>33333333"));
        mBookList.add(new Book("name=======>444444444"));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(15000);
                        Book book = new Book("name=======>" + mBookList.size());

                        Log.d("tbq", "run: ================>addBook==========>" + mBookList.size());

                        mBookList.add(book);
                        int N = iOnNewBookListeners.beginBroadcast();
                        for (int i = 0; i < N; i++) {
                            iOnNewBookListeners.getBroadcastItem(i).onNewBookArrived(book);
                        }
                        iOnNewBookListeners.finishBroadcast();
                    }
                } catch (InterruptedException | RemoteException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }
}
