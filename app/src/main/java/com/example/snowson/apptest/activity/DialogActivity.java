package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.adapter.MutilAdapter;
import com.example.snowson.apptest.bean.DataTypeGrid;
import com.example.snowson.apptest.bean.DataTypeOne;
import com.example.snowson.apptest.bean.DataTypeThree;
import com.example.snowson.apptest.bean.DataTypeTwo;
import com.example.snowson.apptest.dialog.CommonDialogFragment;

import java.util.ArrayList;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDialogShowBtn;
    private CommonDialogFragment cdf;
    private Button mBottomSheetShowBtn;
    private RecyclerView mBottomSheetRv;
    private int colorSet[] = {android.R.color.holo_green_light,
            android.R.color.holo_orange_light, android.R.color.holo_blue_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        mDialogShowBtn = findViewById(R.id.btn_show_dialog);
        mBottomSheetShowBtn = findViewById(R.id.btn_show_bottomsheet);
        mDialogShowBtn.setOnClickListener(this);
        mBottomSheetShowBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_dialog:
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
                break;
            case R.id.btn_show_bottomsheet:
                mBottomSheetRv = new RecyclerView(this);
                mBottomSheetRv.setLayoutManager(new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL, false));
                mBottomSheetRv.setItemAnimator(new DefaultItemAnimator());
                ArrayList<DataTypeOne> datas = new ArrayList<DataTypeOne>();
                for (int i = 0; i < 2; i++) {
                    DataTypeOne item = new DataTypeOne();
                    item.setColorTinyPic(colorSet[i % colorSet.length]);
                    item.setTinyName("tinyName" + i);
                    item.setLastMsg("lastMsg" + i);
                    datas.add(item);
                }
                ArrayList<String> headers = new ArrayList<String>();
                headers.add("Header" + 1);
                MutilAdapter adapter = new MutilAdapter(this);
                adapter.setData(datas, new ArrayList<DataTypeTwo>(), new ArrayList<DataTypeThree>(),
                        new ArrayList<DataTypeGrid>(), new ArrayList<DataTypeGrid>(), headers);
                mBottomSheetRv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                BottomSheetDialog bsDialog = new BottomSheetDialog(this);
                bsDialog.setContentView(mBottomSheetRv);
                bsDialog.show();
                break;
            default:
                break;
        }
    }
}
