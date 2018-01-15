package com.example.snowson.apptest;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.text.TextUtils;
import android.util.Log;

import com.czt.mp3recorder.MP3Recorder;
import com.czt.mp3recorder.util.LameUtil;
import com.czt.mp3recorder.bean.AudioStatus;
import com.example.snowson.apptest.utils.AudioFileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snowson on 18-1-12.
 */

public class AudioRecordManager {
    private static final String TAG = "AudioRecordManager";
    private static byte[] mMp3Buffer;
    private AudioRecord mAudioRecord;
    private static AudioRecordManager mInstance;
    private static final int bufferSize = 256;
    //音频输入-麦克风
    private final static int AUDIO_INPUT = MediaRecorder.AudioSource.MIC;
    //采用频率
    //44100是目前的标准，但是某些设备仍然支持22050，16000，11025
    //采样频率一般共分为22.05KHz、44.1KHz、48KHz三个等级
    private final static int AUDIO_SAMPLE_RATE = 16000;
    //声道 单声道
    private final static int AUDIO_CHANNEL = AudioFormat.CHANNEL_IN_MONO;
    //编码
    private final static int AUDIO_ENCODING = AudioFormat.ENCODING_PCM_16BIT;
    // 缓冲区字节大小
    private int bufferSizeInBytes = 0;
    //录音状态
    private int status = AudioStatus.STATUS_NO_READY;

    private String mFileName;
    private Context mContext;

    //录音文件
    private List<String> mFilesName = new ArrayList<>();
    private static MP3Recorder mRecorder;

    public static AudioRecordManager newInstance() {
        if (mInstance == null) {
            synchronized (AudioRecordManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioRecordManager();
                    LameUtil.init(AUDIO_SAMPLE_RATE, 1, AUDIO_SAMPLE_RATE, 32, 7);
//                    mRecorder = new MP3Recorder(new File(AudioFileUtils.getMP3FileDirectory(), "test.mp3"));
                }
            }
        }
        return mInstance;
    }

    public int getCurrentStatus() {
        if (mAudioRecord == null) {
            Log.e(TAG, "AudioRecord is null");
            return AudioStatus.STATUS_NO_READY;
        }
        return status;
    }

    /**
     * 创建录音对象
     */
    public void createAudio(String fileName, int audioSource, int sampleRateInHz, int channelConfig, int audioFormat) {
        // 获得缓冲区字节大小
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz,
                channelConfig, audioFormat);
        mAudioRecord = new AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes);
        this.mFileName = fileName;
    }

    /**
     * 创建默认的录音对象
     *
     * @param fileName 文件名
     */
    public void createDefaultAudio(Context context, String fileName) {
        mContext = context;
        // 获得缓冲区字节大小
        bufferSizeInBytes = AudioRecord.getMinBufferSize(AUDIO_SAMPLE_RATE,
                AUDIO_CHANNEL, AUDIO_ENCODING);
        mMp3Buffer = new byte[(int) (7200 + (bufferSizeInBytes * 2 * 1.25))];
        mAudioRecord = new AudioRecord(AUDIO_INPUT, AUDIO_SAMPLE_RATE, AUDIO_CHANNEL, AUDIO_ENCODING, bufferSizeInBytes);
        this.mFileName = fileName;
        status = AudioStatus.STATUS_READY;
    }

    /**
     * 开始录音
     *
     * @param listener 音频流的监听
     */
    public void startRecord(final RecordStreamListener listener) {

        if (status == AudioStatus.STATUS_NO_READY || TextUtils.isEmpty(mFileName)) {
            throw new IllegalStateException("录音尚未初始化,请检查是否禁止了录音权限~");
        }
        if (status == AudioStatus.STATUS_START) {
            throw new IllegalStateException("正在录音");
        }
        Log.d("AudioRecorder", "===startRecord===" + mAudioRecord.getState());
        AudioFileUtils.deleteAllMP3Files();
        try {
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        mAudioRecord.startRecording();


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                writeDataToFile(listener);
//            }
//        }).start();
    }

    /**
     * 暂停录音
     */
    public void pauseRecord() {
        Log.d("AudioRecorder", "===pauseRecord===");
        if (status != AudioStatus.STATUS_START) {
            throw new IllegalStateException("没有在录音");
        } else {
            mAudioRecord.stop();
            status = AudioStatus.STATUS_PAUSE;
        }
    }

    /**
     * 停止录音
     */
    public void stopRecord() {
        mRecorder.stop();
//        Log.d("AudioRecorder", "===stopRecord===");
//        if (status == AudioStatus.STATUS_NO_READY || status == AudioStatus.STATUS_READY) {
//            throw new IllegalStateException("录音尚未开始");
//        } else {
//            mAudioRecord.stop();
//            status = AudioStatus.STATUS_STOP;
//            if (mFilesName.size() > 0) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String result = "result.mp3";
//                        for (File file : AudioFileUtils.getPCMFileDirectory().listFiles()) {
//                            FileInputStream fis = null;
//                            FileOutputStream fos = null;
//                            try {
//                                fis = new FileInputStream(file);
//                                File outFile = new File(AudioFileUtils.getMP3FileDirectory(), result);
//                                fos = new FileOutputStream(outFile, true);
//                                pcmToMp3(fis, fos);
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            } finally {
//                                try {
//                                    fis.close();
//                                    fos.close();
//                                    mFilesName.clear();
//                                    AudioFileUtils.deleteAllPCMFiles();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }
//                    }
//                }).start();
//            }
//        }
    }

    public void pcmToMp3(FileInputStream stream, FileOutputStream fos) throws IOException {
        byte[] buffer = new byte[bufferSize];
        stream.read(buffer, 0, buffer.length);
        short[] shorts = toShortArray(buffer);
        LameUtil.encode(shorts, shorts, shorts.length, mMp3Buffer);
        fos.write(mMp3Buffer, 0, mMp3Buffer.length);
        fos.flush();
    }

    private short[] toShortArray(byte[] src) {

        int count = src.length >> 1;
        short[] dest = new short[count];
        for (int i = 0; i < count; i++) {
            dest[i] = (short) (src[i * 2] << 8 | src[2 * i + 1] & 0xff);
        }
        return dest;
    }

    /**
     * 释放资源
     */
    public void release() {
        Log.d("AudioRecorder", "===release===");
        //假如有暂停录音
        try {
            if (mFilesName.size() > 0) {
                List<String> filePaths = new ArrayList<>();
                for (String fileName : mFilesName) {
                    filePaths.add(AudioFileUtils.getPcmFileAbsolutePath(fileName));
                }
                //清除
                mFilesName.clear();
                //将多个pcm文件转化为wav文件
//                mergePCMFilesToWAVFile(filePaths);

            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage());
        }

        if (mAudioRecord != null) {
            mAudioRecord.release();
            mAudioRecord = null;
        }
        status = AudioStatus.STATUS_NO_READY;
    }

    /**
     * 取消录音
     */
    public void cancel() {
        mFilesName.clear();
//        mFileName = null;
//        if (mAudioRecord != null) {
//            mAudioRecord.release();
//            mAudioRecord = null;
//        } = AudioStatus.STATUS_READY;
        status = AudioStatus.STATUS_READY;
        AudioFileUtils.deleteAllPCMFiles();
    }

    public boolean isRecording() {
        if (status == AudioStatus.STATUS_START || status == AudioStatus.STATUS_PAUSE) {
            return true;
        }
        return false;
    }

    /**
     * 将音频信息写入文件
     *
     * @param listener 音频流的监听
     */
    private void writeDataToFile(RecordStreamListener listener) {
        // new一个byte数组用来存一些字节数据，大小为缓冲区大小
        byte[] audiodata = new byte[bufferSizeInBytes];

        FileOutputStream fos = null;
        int readsize = 0;
        try {
            String currentFileName = mFileName;
//            if (status == AudioStatus.STATUS_PAUSE) {
//                //假如是暂停录音 将文件名后面加个数字,防止重名文件内容被覆盖
//                currentFileName += mFilesName.size();
//            }
            File file = new File(AudioFileUtils.getPcmFileAbsolutePath(currentFileName));
            if (!file.exists()) {
                mFilesName.add(currentFileName);
            }
//            if (file.exists()) {
//                file.delete();
//            }
            fos = new FileOutputStream(file, true);// 建立一个可存取字节的文件
        } catch (IllegalStateException e) {
            Log.e("AudioRecorder", e.getMessage());
            throw new IllegalStateException(e.getMessage());
        } catch (FileNotFoundException e) {
            Log.e("AudioRecorder", e.getMessage());
        }
        //将录音状态设置成正在录音状态
        status = AudioStatus.STATUS_START;
        while (status == AudioStatus.STATUS_START) {
            readsize = mAudioRecord.read(audiodata, 0, bufferSizeInBytes);
            if (AudioRecord.ERROR_INVALID_OPERATION != readsize && fos != null) {
                try {
                    fos.write(audiodata);
                    if (listener != null) {
                        //用于拓展业务
                        listener.recordOfByte(audiodata, 0, audiodata.length);
                    }
                } catch (IOException e) {
                    Log.e("AudioRecorder", e.getMessage());
                }
            }
        }
        try {
            if (fos != null) {
                fos.close();// 关闭写入流
            }
        } catch (IOException e) {
            Log.e("AudioRecorder", e.getMessage());
        }
    }

    /**
     * Created by HXL on 16/8/11.
     * 获取录音的音频流,用于拓展的处理
     */
    public interface RecordStreamListener {
        void recordOfByte(byte[] data, int begin, int end);
    }
}
