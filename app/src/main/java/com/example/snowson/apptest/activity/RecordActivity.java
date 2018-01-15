package com.example.snowson.apptest.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.czt.mp3recorder.MP3Recorder;
import com.czt.mp3recorder.bean.AudioStatus;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.utils.ScreenUtils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener {

    private String mFileName;
    private LinearLayout mPlayLlayout;
    private ImageView mPreviousIv;
    private ImageView mNextIv;
    private LinearLayout mStopLlayout;
    private ImageView mCancelIv;
    private static final String TAG = "RecordActivity";
    private ImageView mPlayIv;
    private TextView mAlertTv;
    private MainHandler mHandler = new MainHandler(this);
    private static final int MESSAGE_TIME_UPDATE = 0X001;
    private int curTime = 0;
    private TextView mRecordTime;
    private static final int RECORD_PERMISSION_CODE = 1;
    private String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private MP3Recorder mRecorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
        initEvent();
        initRecord();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, RECORD_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initEvent() {
        mPlayLlayout.setOnClickListener(this);
        mPreviousIv.setOnClickListener(this);
        mNextIv.setOnClickListener(this);
        mStopLlayout.setOnClickListener(this);
        mCancelIv.setOnClickListener(this);
    }

    private void initView() {
        mPlayLlayout = findViewById(R.id.llayout_play);
        mPlayIv = findViewById(R.id.iv_play);
        mPreviousIv = findViewById(R.id.iv_previous);
        mNextIv = findViewById(R.id.iv_next);
        mStopLlayout = findViewById(R.id.llayout_stop);
        mCancelIv = findViewById(R.id.iv_cancel);
        mAlertTv = findViewById(R.id.tv_alert);
        mRecordTime = findViewById(R.id.tv_record_time);
    }

    private void initHeader() {

    }

    private void initRecord() {
        mFileName = "result.mp3";
        mRecorder = new MP3Recorder(Environment
                .getExternalStorageDirectory().getAbsolutePath() + File.separator + "autoaudio", mFileName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llayout_play:
                switch (mRecorder.getCurrentStatus()) {
                    case AudioStatus.STATUS_START:
                        mHandler.removeMessages(MESSAGE_TIME_UPDATE);
                        mRecorder.pause();
                        mPlayIv.setImageResource(android.R.drawable.ic_btn_speak_now);
                        mAlertTv.setText("录音已暂停");
                        break;
                    case AudioStatus.STATUS_NO_READY:
                        Log.e(TAG, "record not ready, please check permission");
                        mAlertTv.setText("请检查是否未授予录音权限");
                        break;
                    default:
                        try {
                            mRecorder.start();
                            mPlayIv.setImageResource(android.R.drawable.ic_media_pause);
                            mAlertTv.setText("录音中...");
                            mHandler.sendEmptyMessageDelayed(MESSAGE_TIME_UPDATE, 1000);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                break;
            case R.id.iv_previous:
                break;
            case R.id.iv_next:
                break;
            case R.id.llayout_stop:
                if (!mRecorder.isRecording()) {
                    return;
                }
                mHandler.removeMessages(MESSAGE_TIME_UPDATE);
                mRecorder.stop();
                mAlertTv.setText("录音已停止");
                curTime = 0;
                mRecordTime.setText(ScreenUtils.timeFormat(curTime));
                mPlayIv.setImageResource(android.R.drawable.ic_btn_speak_now);
                break;
            case R.id.iv_cancel:
                if (!mRecorder.isRecording()) {
                    return;
                }
                mHandler.removeMessages(MESSAGE_TIME_UPDATE);
                mRecorder.cancel();
                mAlertTv.setText("录音已取消");
                curTime = 0;
                mRecordTime.setText(ScreenUtils.timeFormat(curTime));
                mPlayIv.setImageResource(android.R.drawable.ic_btn_speak_now);
                break;
        }
    }

    private static class MainHandler extends Handler {
        private SoftReference<RecordActivity> mContext;

        public MainHandler(RecordActivity context) {
            if (context != null) {
                mContext = new SoftReference<RecordActivity>(context);
            }
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_TIME_UPDATE:
                    RecordActivity curContext = mContext.get();
                    if (curContext != null) {
                        curContext.mRecordTime.setText(ScreenUtils.timeFormat(++curContext.curTime));
                        curContext.mHandler.sendEmptyMessageDelayed(MESSAGE_TIME_UPDATE, 1000);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRecorder != null) {
            mRecorder.release();
        }
    }
}
