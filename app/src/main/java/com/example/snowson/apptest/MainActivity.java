package com.example.snowson.apptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.snowson.apptest.view.RoundImageButton;

public class MainActivity extends AppCompatActivity {

    private RoundImageButton mRibDisplay;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutil_type_button);
        mRadioGroup = findViewById(R.id.rg_test);
        mRibDisplay = findViewById(R.id.rib_display);
        mRibDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        mRadioGroup.check(mRadioGroup.getChildAt(0).getId());
//        startActivity(new Intent(this, MutilRecyclerViewActivity.class));
//        startActivity(new Intent(this, ImageShapeActivity.class));
    }
}
