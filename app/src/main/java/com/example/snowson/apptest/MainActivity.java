package com.example.snowson.apptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.snowson.apptest.view.RoundImageButton;

public class MainActivity extends AppCompatActivity {

    private RoundImageButton mRibDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_button);
        mRibDisplay = findViewById(R.id.rib_display);
        mRibDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "click successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
//        startActivity(new Intent(this, MutilRecyclerViewActivity.class));
    }
}
