package com.example.snowson.apptest.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by snowson on 18-1-12.
 */

public class AudioFileUtils {
    private static String rootPath = "audiorecord";
    //原始文件(不能播放)
    private final static String AUDIO_PCM_BASEPATH = "/" + rootPath + "/pcm/";
    //可播放的高质量音频文件
    private final static String AUDIO_MP3_BASEPATH = "/" + rootPath + "/mp3/";

    private static void setRootPath(String rootPath) {
        rootPath = rootPath;
    }

    public static String getPcmFileAbsolutePath(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            throw new NullPointerException("fileName isEmpty");
        }
        if (!isSdcardExit()) {
            throw new IllegalStateException("sd card no found");
        }
        String mAudioRawPath = "";
        if (isSdcardExit()) {
            if (!fileName.endsWith(".pcm")) {
                fileName = fileName + ".pcm";
            }
            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_PCM_BASEPATH;
            File file = new File(fileBasePath);
            //创建目录
            if (!file.exists()) {
                file.mkdirs();
            }
            mAudioRawPath = fileBasePath + fileName;
        }

        return mAudioRawPath;
    }

    public static String getWavFileAbsolutePath(String fileName) {
        if (fileName == null) {
            throw new NullPointerException("fileName can't be null");
        }
        if (!isSdcardExit()) {
            throw new IllegalStateException("sd card no found");
        }

        String mAudioWavPath = "";
        if (isSdcardExit()) {
            if (!fileName.endsWith(".wav")) {
                fileName = fileName + ".wav";
            }
            String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_MP3_BASEPATH;
            File file = new File(fileBasePath);
            //创建目录
            if (!file.exists()) {
                file.mkdirs();
            }
            mAudioWavPath = fileBasePath + fileName;
        }
        return mAudioWavPath;
    }

    /**
     * 判断是否有外部存储设备sdcard
     *
     * @return true | false
     */
    public static boolean isSdcardExit() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    /**
     * 获取全部pcm文件列表
     *
     * @return
     */
    public static List<File> getPcmFiles() {
        List<File> list = new ArrayList<>();
        String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_PCM_BASEPATH;

        File rootFile = new File(fileBasePath);
        if (!rootFile.exists()) {
        } else {

            File[] files = rootFile.listFiles();
            for (File file : files) {
                list.add(file);
            }
        }
        return list;
    }

    /**
     * 获取全部wav文件列表
     *
     * @return
     */
    public static List<File> getWavFiles() {
        List<File> list = new ArrayList<>();
        String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_MP3_BASEPATH;

        File rootFile = new File(fileBasePath);
        if (!rootFile.exists()) {
        } else {
            File[] files = rootFile.listFiles();
            for (File file : files) {
                list.add(file);
            }
        }
        return list;
    }

    public static void deleteAllPCMFiles() {
        String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_PCM_BASEPATH;
        File rootFile = new File(fileBasePath);
        if (rootFile.exists()) {
            for (File item : rootFile.listFiles()) {
                if (item.getName().equals("result.pcm")) {
                    continue;
                }
                item.delete();
            }
        }
    }

    public static void deleteAllMP3Files() {
        String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_MP3_BASEPATH;
        File rootFile = new File(fileBasePath);
        if (rootFile.exists()) {
            for (File item : rootFile.listFiles()) {
                item.delete();
            }
        }
    }

    public static File getPCMFileDirectory() {
        String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_PCM_BASEPATH;
        File rootFile = new File(fileBasePath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        return rootFile;
    }

    public static File getMP3FileDirectory() {
        String fileBasePath = Environment.getExternalStorageDirectory().getAbsolutePath() + AUDIO_MP3_BASEPATH;
        File rootFile = new File(fileBasePath);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        return rootFile;
    }
}
