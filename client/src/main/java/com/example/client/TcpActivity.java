package com.example.client;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class TcpActivity extends AppCompatActivity {
    private LinearLayout messageLinearLayout;
    private Socket socket;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Context context;
    private EditText sendMessageText;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.obj != null) {
                TextView messageText = new TextView(context);
                messageText.setText(msg.obj.toString());
                messageLinearLayout.addView(messageText);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcp);
        context = getApplicationContext();
        messageLinearLayout = findViewById(R.id.tcp_LinearLayout);

        sendMessageText = findViewById(R.id.tcp_message);
        findViewById(R.id.tcp_send_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("tbq", "onCreate: " + isServiceWork(TcpActivity.this, "com.example.serviceapp.socket.SocketService"));
                if (out != null) {
                    if (!sendMessageText.getText().toString().trim().equals("")) {
                        Message message = new Message();
                        message.obj = sendMessageText.getText().toString().trim();
                        handler.sendMessage(message);
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                out.println(sendMessageText.getText().toString().trim());
                            }
                        }.start();
                    }
                }
            }
        });

//        Log.d("tbq", "onCreate: " + isServiceWork(this, "com.example.serviceapp.socket.SocketService"));
//        if (isServiceWork(this, "com.example.serviceapp.socket.SocketService")) {
        Intent intent = new Intent();
        intent.setPackage("com.example.serviceapp");
        intent.setAction("com.example.serviceapp.action.tcp");
        startService(intent);
//        }

        //连接
        new Thread() {
            @Override
            public void run() {
                super.run();
                ConnectTcpService();
            }
        }.start();


    }

    private void ConnectTcpService() {
        socket = null;
        while (socket == null) {
            try {
                socket = new Socket("localhost", 8688);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                Log.d("tbq", "ConnectTcpService: ===============>Success");

                //收消息
                while (!TcpActivity.this.isFinishing()) {
                    Message message = new Message();
                    message.obj = in.readLine();
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("tbq", "ConnectTcpService: ===============>IOException--->" + e.getMessage());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 判断某个服务是否正在运行的方法
     *
     * @param mContext
     * @param serviceName 是包名+服务的类名（例如：net.loonggg.testbackstage.TestService）
     * @return true代表正在运行，false代表服务没有正在运行
     */
    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(40);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }
}
