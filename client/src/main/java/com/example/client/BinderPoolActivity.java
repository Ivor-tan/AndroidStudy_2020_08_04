package com.example.client;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aidl.IAddition;
import com.example.aidl.ISubtraction;
import com.example.aidl.binderpool.AdditionImpl;
import com.example.aidl.binderpool.BinderPool;
import com.example.aidl.binderpool.SubtractionImpl;

public class BinderPoolActivity extends AppCompatActivity {
    private TextView result;
    private BinderPool mBinderPool;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binderpool);
        result = findViewById(R.id.binder_pool_result);

        new Thread() {
            @Override
            public void run() {
                super.run();
                mBinderPool = BinderPool.getInstance(BinderPoolActivity.this);
            }
        }.start();


        findViewById(R.id.binder_pool_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IAddition iAddition = AdditionImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_ADD));
                try {
                    result.setText("iAddition：" + iAddition.addition(5, 6));
//                    Log.d("tbq", "onClick:=============== iAddition>" + iAddition.addition(5, 6));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.binder_pool_sub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ISubtraction iSubtraction = SubtractionImpl.asInterface(mBinderPool.queryBinder(BinderPool.BINDER_SUB));
                try {
                    result.setText("iSubtraction：" + iSubtraction.subtraction(15, 5));
//                    Log.d("tbq", "onClick:=============== iSubtraction>" + iSubtraction.subtraction(15, 5));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
