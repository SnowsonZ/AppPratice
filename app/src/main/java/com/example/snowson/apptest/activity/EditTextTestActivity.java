package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.snowson.apptest.R;

/**
 * EditText自动弹出键盘 在清单文件中配置windowSoftInputMode ： stateVisible|adjustPan
 * EditText  setFocusable表示可获取非触摸事件的焦点   setFocusableInTouchMode 可获取触摸事件的焦点
 *
 */
public class EditTextTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text_test);
        EditText et_test = findViewById(R.id.et_test);
        et_test.setFocusableInTouchMode(true);
        if(et_test.hasFocus()) {
            Toast.makeText(this, "EditText获得焦点", Toast.LENGTH_SHORT).show();
        }
        et_test.setCursorVisible(false);
    }
}
