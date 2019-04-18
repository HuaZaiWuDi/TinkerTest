package com.wesmarclothing.tinkertest;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * @Package com.wesmarclothing.tinkertest
 * @FileName IPCMessengerDemo
 * @Date 2019/3/21 16:47
 * @Author JACK
 * @Describe TODO多进程通信
 * @Project TinkerTest
 */
public class IPCMessengerDemo {

    public static final int MSG_FROM_CLIENT = 0;
    public static final int MSG_FROM_SERVICE = 1;
    public static final String MSG_KEY = "MSG_KEY";

    class MessengerService extends Service {

        private Messenger mMessenger = new Messenger(new MessengerHandler());

        private class MessengerHandler extends Handler {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_FROM_CLIENT:
                        Messenger replyTo = msg.replyTo;
                        Message obtain = Message.obtain(null, MSG_FROM_CLIENT);
                        Bundle bundle = new Bundle();
                        bundle.putString(MSG_KEY, "我已经收到你的消息了");
                        obtain.setData(bundle);

                        try {
                            replyTo.send(obtain);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }

                        break;
                    default:
                        super.handleMessage(msg);
                }

            }
        }

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return mMessenger.getBinder();
        }


    }


    //客户端
    class MainActivity extends AppCompatActivity {
        private final String TAG = MainActivity.class.getSimpleName();
        private Messenger mGetReplyMessenger = new Messenger(new MessageHandler());
        private Messenger mService;

        private class MessageHandler extends Handler {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_FROM_SERVICE:
                        Log.d(TAG, "received msg form service: msg = [" + msg.getData().getString(MSG_KEY) + "]");
                        Toast.makeText(MainActivity.this, "received msg form service: msg = [" + msg.getData().getString(MSG_KEY) + "]", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        }

        public void bindService(View v) {
            Intent mIntent = new Intent(this, MessengerService.class);
            bindService(mIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
        }

        public void sendMessage(View v) {
            Message msg = Message.obtain(null, MSG_FROM_CLIENT);
            Bundle data = new Bundle();
            data.putString(MSG_KEY, "Hello! This is client.");
            msg.setData(data);
            msg.replyTo = mGetReplyMessenger;
            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onDestroy() {
            unbindService(mServiceConnection);
            super.onDestroy();
        }

        private ServiceConnection mServiceConnection = new ServiceConnection() {
            /**
             * @param name
             * @param service
             */
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mService = new Messenger(service);
                Message msg = Message.obtain(null, MSG_FROM_CLIENT);
                Bundle data = new Bundle();
                data.putString(MSG_KEY, "Hello! This is client.");
                msg.setData(data);
                //
                msg.replyTo = mGetReplyMessenger;
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            /**
             * @param name
             */
            @Override
            public void onServiceDisconnected(ComponentName name) {


            }
        };
    }


}
