package com.example.snowson.apptest.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.snowson.apptest.R;

import java.io.File;

/**
 * Created by snowson on 18-3-22.
 */

public class FileProviderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

    }

    public void onClick(View view) {
        File imagePath = new File(Environment.getExternalStorageDirectory(), "fivestar");
        File newFile = new File(imagePath, "test.apk");
        Uri contentUri = FileProvider.getUriForFile(this,
                "com.example.snowson.apptest.fileprovider", newFile);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (getPackageManager().queryIntentActivities(intent, 0).size() > 0) {
            startActivity(intent);
        }
    }
}
