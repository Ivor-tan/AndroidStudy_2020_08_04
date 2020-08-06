package com.example.serviceapp.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class SocketService extends Service {
    private boolean isDestroy = false;
    private String[] mMessage = new String[]{
            "SocketService============>111111",
            "SocketService============>222222",
            "SocketService============>333333",
            "SocketService============>444444",
            "SocketService============>555555",
            "SocketService============>666666"
    };

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new TcpService()).start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDestroy = true;
    }

    private class TcpService implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(8688);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            while (!isDestroy) {
                try {
                    final Socket socket = serverSocket.accept();
                    Log.d("tbq", "run: ============>responseClient");
                    responseClient(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void responseClient(Socket socket) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            out.println("first==========================>");
            while (!isDestroy) {
                String s = in.readLine();
                Log.d("tbq", "responseClient: =========>GetClientMessage------>" + s);
                if (s == null) {
                    break;
                }
                out.println(mMessage[new Random().nextInt(mMessage.length)]);
            }
            in.close();
            out.close();
            socket.close();
//            stopSelf();
        } catch (IOException e) {
            Log.d("tbq", "responseClient: =============IOException  " + e.getMessage());
        }

    }
}
