package com.example.snowson.apptest.activity;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.dialog.CommonDialogFragment;

public class DialogActivity extends AppCompatActivity {

    private Button mDialogShowBtn;
    private CommonDialogFragment cdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mDialogShowBtn = findViewById(R.id.btn_show_dialog);
        mDialogShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cdf == null) {
                    cdf = new CommonDialogFragment.Builder()
                            .setWidth((int)
                                    (getWindowManager().getDefaultDisplay().getWidth() * 0.8))
                            .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setContentView(R.layout.dialog_fragment)
                            .setStyle(DialogFragment.STYLE_NO_TITLE)
                            .setAnimation(R.style.DialogAnimScale)
                            .build();
                }
                cdf.show(getSupportFragmentManager(), CommonDialogFragment.class.getName());
            }
        });
    }


}
