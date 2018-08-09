package com.example.snowson.apptest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.snowson.apptest.R;
import com.example.snowson.apptest.adapter.MultiAdapter;
import com.example.snowson.apptest.bean.DataTypeGrid;
import com.example.snowson.apptest.bean.DataTypeOne;
import com.example.snowson.apptest.bean.DataTypeThree;
import com.example.snowson.apptest.bean.DataTypeTwo;
import com.example.snowson.apptest.bean.TypesBlock;
import com.example.snowson.apptest.utils.JsonUtil;
import com.example.snowson.apptest.utils.ScreenUtils;
import com.example.snowson.apptest.view.Banner;
import com.example.snowson.apptest.viewholder.TypeAbstractViewHolder;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MutilRecyclerViewActivity extends AppCompatActivity
        implements OnRefreshListener, OnLoadMoreListener {

    private RecyclerView mContextRv;
    private List<TypesBlock> mDatas = new ArrayList<TypesBlock>();
    private int[] colorSet = {android.R.color.holo_green_light,
            android.R.color.holo_orange_light, android.R.color.holo_blue_light};
    private ArrayList<String> mImageUrl = new ArrayList<String>();
    private MultiAdapter adapter;
    private Banner mBanner;
    private SwipeToLoadLayout mSwipeToLoadLayout;
    private ArrayList<DataTypeOne> mDataOne;
    private ArrayList<DataTypeTwo> mDataTwo;
    private ArrayList<DataTypeThree> mDataThree;
    private ArrayList<DataTypeGrid> mDataFour;
    private ArrayList<DataTypeGrid> mDataFive;
    private ArrayList<String> mDataHeader;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil_recycler_view);
        mContextRv = findViewById(R.id.swipe_target);
        mBanner = new Banner(this);
        mSwipeToLoadLayout = findViewById(R.id.swipToLoadLayout);
        mSwipeToLoadLayout.setOnRefreshListener(this);
        mSwipeToLoadLayout.setOnLoadMoreListener(this);
        ViewGroup.LayoutParams params = mBanner.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) ScreenUtils.dp2px(this, 200));
        }
        mBanner.setLayoutParams(params);
        mImageUrl.add("#ff33b5e5");
        mImageUrl.add("#ff99cc00");
        mImageUrl.add("#ffffbb33");
        mImageUrl.add("#ffaa66cc");
        mImageUrl.add("#ff00ddff");
        mBanner.initData(mImageUrl);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        //根据item type判断item占据RecyclerView一行内容的具体大小,实现Grid,List混合布局
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mContextRv.getAdapter().getItemViewType(position);
                switch (viewType) {
                    case TypeAbstractViewHolder.TYPE_ONE:
                    case TypeAbstractViewHolder.TYPE_TWO:
                    case TypeAbstractViewHolder.TYPE_THREE:
                    case TypeAbstractViewHolder.TYPE_HEADER:
                    case TypeAbstractViewHolder.TYPE_BANNER:
                        return gridLayoutManager.getSpanCount();
                    case TypeAbstractViewHolder.TYPE_FOUR:
                        return gridLayoutManager.getSpanCount() / 2;
                    default:
                        return 1;
                }
            }
        });
        mContextRv.setLayoutManager(gridLayoutManager);
        disposable = Observable.create(new ObservableOnSubscribe<ArrayList<TypesBlock>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<TypesBlock>> emitter) throws Exception {
                emitter.onNext(getData());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<TypesBlock>>() {
                    @Override
                    public void accept(ArrayList<TypesBlock> typesBlocks) throws Exception {
                        mDatas = typesBlocks;
                        adapter = new MultiAdapter(MutilRecyclerViewActivity.this);
                        adapter.setHeaderView(mBanner);
                        adapter.setData(mDatas);
                        mContextRv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                });
//        initData();
    }

    private ArrayList<TypesBlock> getData() {
        try {
            return JsonUtil.fromJsonReader(new InputStreamReader(getResources().getAssets()
                    .open("index.json")));
        } catch (IOException e) {
            e.printStackTrace();
            Logger.e("解析错误");
        }
        return null;
    }

    private void initData() {
        mDataOne = new ArrayList<DataTypeOne>();
        for (int i = 0; i < 10; i++) {
            DataTypeOne item = new DataTypeOne();
            item.setColorTinyPic(colorSet[i % colorSet.length]);
            item.setTinyName("TinyName" + i);
            item.setLastMsg("lastMst:" + i);
            mDataOne.add(item);
        }

        mDataTwo = new ArrayList<DataTypeTwo>();
        for (int i = 0; i < 10; i++) {
            DataTypeTwo item = new DataTypeTwo();
            item.setColorTinyPic(colorSet[i % colorSet.length]);
            item.setTinyName("TinyName" + i);
            mDataTwo.add(item);
        }

        mDataThree = new ArrayList<DataTypeThree>();
        for (int i = 0; i < 10; i++) {
            DataTypeThree item = new DataTypeThree();
            item.setColorTinyPic(colorSet[i % colorSet.length]);
            item.setTinyName("TinyName" + i);
            item.setColorBg(colorSet[i % colorSet.length]);
            mDataThree.add(item);
        }

        mDataFour = new ArrayList<DataTypeGrid>();
        for (int i = 0; i <= 10; i++) {
            DataTypeGrid item = new DataTypeGrid();
            item.setGoodsName("GoodsName" + i);
            item.setGoodsPic(colorSet[i % colorSet.length]);
            mDataFour.add(item);
        }
        mDataFive = new ArrayList<DataTypeGrid>();
        for (int i = 0; i < 10; i++) {
            DataTypeGrid item = new DataTypeGrid();
            item.setGoodsName("GoodsName" + i);
            item.setGoodsPic(colorSet[i % colorSet.length]);
            mDataFive.add(item);
        }

        mDataHeader = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            mDataHeader.add("Header" + i);
        }

        adapter = new MultiAdapter(this);
        adapter.setHeaderView(mBanner);
        adapter.setData(mDataOne, mDataTwo, mDataThree, mDataFour, mDataFive, mDataHeader);
        mContextRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
        DataTypeGrid dtg = new DataTypeGrid();
        dtg.setGoodsName("Add Item By LoadMore");
        dtg.setGoodsPic(android.R.color.holo_purple);
        mDataFive.add(dtg);
        adapter.updateData(mDataOne, mDataTwo, mDataThree, mDataFour, mDataFive, mDataHeader);
        mContextRv.post(new Runnable() {
            @Override
            public void run() {
                mContextRv.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
        mSwipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
        DataTypeOne dto = new DataTypeOne();
        dto.setColorTinyPic(android.R.color.holo_purple);
        dto.setTinyName("Add Item By Refresh");
        dto.setLastMsg("new message");
        mDataOne.add(0, dto);
        adapter.updateData(mDataOne, mDataTwo, mDataThree, mDataFour, mDataFive, mDataHeader);
        mSwipeToLoadLayout.setRefreshing(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
