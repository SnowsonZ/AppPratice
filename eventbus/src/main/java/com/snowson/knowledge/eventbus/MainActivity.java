package com.snowson.knowledge.eventbus;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.snowson.knowledge.eventbus.event.MessageEvent;
import com.snowson.knowledge.eventbus.fragment.MainFragment;

import org.greenrobot.eventbus.EventBus;

/**
 * EventBus
 1. pubic static class MessageEvent {}

 2. 定义订阅事件
 @Subscribe(threadMode=ThreadMode.MAIN)
 public void onMessageEvent(MessageEvent event);

 3.	注册，注销
 in Fragment or Activity

 EventBus.getDefault().register(this);

 EventBus.getDefault().register(this);

 4. 发送事件
 EventBuS.GetDefault().post(new MessageEvent()));

 ThradMode.MAIN_ORDERED : 维持主线程的消息队列， eg: 两个消息发送给主线程，1消息来自工作线程，2来自主线程。默认情况2
 先取出并执行。该标志可保持1消息处理完毕后，再处理2消息
 *
 */
public class MainActivity extends AppCompatActivity {

    private EditText mToastEt;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToastEt = findViewById(R.id.et_toast);

        mToastEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                msg = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                MessageEvent message = new MessageEvent();
                message.setMessage(msg);
                EventBus.getDefault().post(message);
            }
        });
        showFragment();
    }

    private void showFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        MainFragment fragment = MainFragment.getInstance(new Bundle());
        transaction.add(R.id.layout_content, fragment, MainFragment.class.getName());
        transaction.show(fragment);
        transaction.commit();
    }
}
