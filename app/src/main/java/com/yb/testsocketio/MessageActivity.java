package com.yb.testsocketio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import org.json.JSONObject;


import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Created by 12475 on 2018/5/14.
 */

public class MessageActivity extends AppCompatActivity {
    private Socket mSocket;
    private static final String MSG = "msg";
    private static final String LOGIN = "login";
    private static final String TAG = "MessageActivity";
    private String mUname;
    private Button mButton;
    private EditText mSendText;
    private String mMessage;
    private RecyclerView mRecyclerView;
    private MessageAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mButton = (Button) findViewById(R.id.btnSend);
        mSendText = (EditText) findViewById(R.id.editMessage);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MessageAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        try {
            mSocket = IO.socket("http://192.168.1.127:8888");
            Log.e(TAG, "---->" + mSocket);
            mSocket.on(Socket.EVENT_CONNECT, onConnect);
            mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect);
            mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
            mSocket.on(MSG, onMsg);
            mSocket.on(LOGIN, onLogin);
            mSocket.connect();


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        Intent intent = getIntent();
        mUname = intent.getStringExtra("uname");
        Log.e(TAG, "------>" + mUname);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });

    }

    private void send() {
        mMessage = mSendText.getText().toString();
        mSendText.setText("");
        Map<String, String> map2 = new HashMap<>();
        map2.put("msg", mMessage);
        JSONObject info = new JSONObject(map2);
        mSocket.emit(MSG, info);
        Log.e(TAG, "info---->" + info);
        Log.e(TAG, "Message------>" + mMessage);
        Log.e(TAG, "Message------>1");
        Log.e(TAG, "Message------>2");
        Log.e(TAG, "Message------>3");
        Log.e(TAG, "Message------>4");
        Log.e(TAG, "Message------>5");
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {

        @Override
        public void call(Object... args) {
            Log.e(TAG, "onConnect------>" + args);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Map<String, String> map = new HashMap<>();
                    map.put("uname", mUname);
                    JSONObject user = new JSONObject(map);
                    mSocket.emit(LOGIN, user);
                    Log.e(TAG, "---->" + user);
                }
            });
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private Emitter.Listener onMsg = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            String s = ((JSONObject) args[0]).toString();
            SocketBean bean = com.alibaba.fastjson.JSONObject.parseObject(s, SocketBean.class);
            if(bean.getAction()==1||bean.getAction()==3){
                if (mAdapter != null) {
                    mAdapter.insert(bean);
                }
            }
            Log.e(TAG, "onMsg----> " + s);
        }
    };

    private Emitter.Listener onLogin = new Emitter.Listener() {

        @Override
        public void call(final Object... args) {
            Log.e(TAG, "onLogin---->" + onLogin);

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSocket != null) {
            mSocket.close();
        }
    }
}
