package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.example.aidl.Book;
import com.example.aidl.IBookManger;
import com.example.aidl.IOnNewBookListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private IBookManger iBookManger;
    private int number = 1;
    private int book_id = 4;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.textView001).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    List<Book> list = iBookManger.getBookList();
                    for (int i = 0; i < list.size(); i++) {
                        Log.d("tbq", "getBookList: =========>" + i + list.get(i).getName());
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.textView002).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    iBookManger.addBook(new Book("======================>name===>" + number++));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        findViewById(R.id.textView003).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.serviceapp.book.provider/book");
                ContentValues values = new ContentValues();
                values.put("_id", book_id++);
                values.put("name", "book_test00" + book_id);
                getContentResolver().insert(uri, values);
            }
        });
        findViewById(R.id.textView004).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.serviceapp.book.provider/book");
                Cursor cursor = getContentResolver().query(uri, new String[]{"_id", "name"}, null, null, null);
                while (cursor.moveToNext()) {
                    Log.d("tbq", "Cursor   book  ======> " + cursor.getInt(0));
                    Log.d("tbq", "Cursor   book  ======> " + cursor.getString(1));
                }
                cursor.close();
            }
        });

        findViewById(R.id.textView005).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setPackage("com.example.serviceapp");
                intent.setAction("com.example.serviceapp.action");
                bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.textView006).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TcpActivity.class));
            }
        });

        findViewById(R.id.textView007).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BinderPoolActivity.class));
            }
        });
    }

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d("tbq", "binderDied: ==============>");
//            if (iBookManger != null) {
//                iBookManger.asBinder().unlinkToDeath(deathRecipient, 0);
//                iBookManger = null;
//            }
        }
    };

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                iBookManger = IBookManger.Stub.asInterface(iBinder);
                iBinder.linkToDeath(deathRecipient, 0);
                iBookManger.registerNewBookListener(iOnNewBookListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d("tbq", "onServiceConnected: ========>");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("tbq", "onServiceDisconnected: ========>");
//            iBookManger = null;
        }
    };

    private IOnNewBookListener iOnNewBookListener = new IOnNewBookListener.Stub() {
        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            Log.d("tbq", "iOnNewBookListener: ========>" + book.getName());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iBookManger != null && iBookManger.asBinder().isBinderAlive()) {
            try {
                iBookManger.unregisterNewBookListener(iOnNewBookListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(mServiceConnection);
    }
}
