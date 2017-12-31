package com.example.snowson.apptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.adapter.MutilAdapter;
import com.example.snowson.apptest.bean.DataTypeGrid;
import com.example.snowson.apptest.bean.DataTypeOne;
import com.example.snowson.apptest.bean.DataTypeThree;
import com.example.snowson.apptest.bean.DataTypeTwo;
import com.example.snowson.apptest.dialog.CommonDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: snowson
 * created on: 17-12-31 下午10:18
 * description:
 */

public class PageDialogFragment extends BaseFragment {

    private AppCompatSpinner mDialogAnimSpn;
    private List<String> mAnimsSrc;
    private List<String> mAnims;
    private List<String> mDialogTypes;
    private AppCompatSpinner mDialogTypeSpn;
    private Button mDialogShowBtn;
    private int mCurDialogType = 0;

    private CommonDialogFragment mDialogSimply;
    private RecyclerView mBottomSheetRv;
    private int colorSet[] = {android.R.color.holo_green_light,
            android.R.color.holo_orange_light, android.R.color.holo_blue_light};
    private BottomSheetDialog bsDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mTitle = arguments.getString("pageTitle");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dialog, container, false);
        initView(rootView);
        initData();
        return rootView;
    }

    private void initView(View container) {
        mDialogTypeSpn = container.findViewById(R.id.spinner_dialog_type);
        mDialogAnimSpn = container.findViewById(R.id.spinner_anim);
        mDialogShowBtn = container.findViewById(R.id.btn_show_dialog);
    }

    private void initData() {
        mDialogTypes = new ArrayList<>();
        mDialogTypes.add("DialogSimplely");
        mDialogTypes.add("BottomSheet");
        mDialogTypes.add("PopupWindow");
        mDialogTypeSpn.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, mDialogTypes));
        mDialogTypeSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    if(mAnims.size() <= 0) {
                        mAnims.addAll(mAnimsSrc);
                    }
                    mDialogAnimSpn.setEnabled(true);
                }else {
                    mAnims.clear();
                    mDialogAnimSpn.setEnabled(false);
                }
                ((ArrayAdapter)mDialogTypeSpn.getAdapter()).notifyDataSetChanged();
                mCurDialogType = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mAnimsSrc = new ArrayList<>();
        mAnims = new ArrayList<>();
        mAnimsSrc.add("anim_top_to_center");
        mAnimsSrc.add("anim_bottom_to_center");
        mAnimsSrc.add("anim_right_to_center");
        mAnimsSrc.add("anim_left_to_center");
        mAnimsSrc.add("scale_and_alpha");
        mAnims.addAll(mAnimsSrc);
        mDialogAnimSpn.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, mAnims));
        mDialogAnimSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mDialogSimply = createDialog(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mDialogShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mCurDialogType) {
                    case 0:
                        if (mDialogSimply != null) {
                            mDialogSimply.show(getFragmentManager(),
                                    CommonDialogFragment.class.getName());
                        }
                        break;
                    case 1:
                        if(mBottomSheetRv == null || bsDialog == null) {
                            mBottomSheetRv = new RecyclerView(getActivity());
                            mBottomSheetRv.setLayoutManager(new LinearLayoutManager(getActivity(),
                                    LinearLayoutManager.VERTICAL, false));
                            mBottomSheetRv.setItemAnimator(new DefaultItemAnimator());
                            ArrayList<DataTypeOne> datas = new ArrayList<DataTypeOne>();
                            for (int i = 0; i < 10; i++) {
                                DataTypeOne item = new DataTypeOne();
                                item.setColorTinyPic(colorSet[i % colorSet.length]);
                                item.setTinyName("tinyName" + i);
                                item.setLastMsg("lastMsg" + i);
                                datas.add(item);
                            }
                            ArrayList<String> headers = new ArrayList<String>();
                            headers.add("Header" + 1);
                            MutilAdapter adapter = new MutilAdapter(getActivity());
                            adapter.setData(datas, new ArrayList<DataTypeTwo>(),
                                    new ArrayList<DataTypeThree>(),
                                    new ArrayList<DataTypeGrid>(), new ArrayList<DataTypeGrid>(),
                                    headers);
                            mBottomSheetRv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            bsDialog = new BottomSheetDialog(getActivity());
                            bsDialog.setContentView(mBottomSheetRv);
                        }
                        bsDialog.show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "wait...", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private CommonDialogFragment createDialog(int index) {
        int animId = 0;
        switch (index) {
            case 0:
                animId = R.style.DialogAnimT2B;
                break;
            case 1:
                animId = R.style.DialogAnimB2T;
                break;
            case 2:
                animId = R.style.DialogAnimR2L;
                break;
            case 3:
                animId = R.style.DialogAnimL2R;
                break;
            case 4:
                animId = R.style.DialogAnimScale;
                break;
        }
        CommonDialogFragment fragment = new CommonDialogFragment.Builder()
                .setWidth((int) (getActivity().getWindowManager()
                        .getDefaultDisplay().getWidth() * 0.8))
                .setHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setContentView(R.layout.dialog_fragment)
                .setStyle(DialogFragment.STYLE_NO_TITLE)
                .setAnimation(animId)
                .build();
        return fragment;
    }

}
