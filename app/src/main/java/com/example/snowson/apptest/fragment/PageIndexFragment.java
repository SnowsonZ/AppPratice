package com.example.snowson.apptest.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
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

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: snowson
 * created on: 17-12-31 下午10:03
 * description:
 */

public class PageIndexFragment extends BaseFragment
        implements OnRefreshListener, OnLoadMoreListener {

    private RecyclerView mContentRv;
    private List<TypesBlock> mData;
    private int colorSet[] = {android.R.color.holo_green_light,
            android.R.color.holo_orange_light, android.R.color.holo_blue_light};
    private ArrayList<String> mImageUrl;
    private MultiAdapter adapter;
    private Banner mBanner;
    private SwipeToLoadLayout mSwipToLoadLayout;
    //    private ArrayList<DataTypeOne> mDataOne;
//    private ArrayList<DataTypeTwo> mDataTwo;
//    private ArrayList<DataTypeThree> mDataThree;
//    private ArrayList<DataTypeGrid> mDataFour;
//    private ArrayList<DataTypeGrid> mDataFive;
//    private ArrayList<String> mDataHeader;
    private View mRootView;
    private Disposable disposable;

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
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_index, container, false);
            initView(mRootView);
        }
        disposable = Observable.create(new ObservableOnSubscribe<ArrayList<TypesBlock>>() {
            @Override
            public void subscribe(ObservableEmitter<ArrayList<TypesBlock>> emitter)
                    throws Exception {
                emitter.onNext(JsonUtil.fromJsonReader(new InputStreamReader(getResources()
                        .getAssets().open("index.json"))));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<TypesBlock>>() {
                    @Override
                    public void accept(ArrayList<TypesBlock> typesBlocks) throws Exception {
                        mData = typesBlocks;
                        adapter = new MultiAdapter(getActivity());
                        adapter.setHeaderView(mBanner);
                        adapter.setData(mData);
                        mContentRv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                });
        return mRootView;
    }

    private void initView(View container) {
        mContentRv = container.findViewById(R.id.swipe_target);
        mBanner = new Banner(getActivity());
        mSwipToLoadLayout = container.findViewById(R.id.swipToLoadLayout);
        mSwipToLoadLayout.setOnRefreshListener(this);
        mSwipToLoadLayout.setOnLoadMoreListener(this);
        ViewGroup.LayoutParams params = mBanner.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) ScreenUtils.dp2px(getActivity(), 200));
        }
        mBanner.setLayoutParams(params);
        mImageUrl = new ArrayList<String>();
        mImageUrl.add("#ff33b5e5");
        mImageUrl.add("#ff99cc00");
        mImageUrl.add("#ffffbb33");
        mImageUrl.add("#ffaa66cc");
        mImageUrl.add("#ff00ddff");
        mBanner.initData(mImageUrl);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        //根据item type判断item占据RecyclerView一行内容的具体大小,实现Grid,List混合布局
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mContentRv.getAdapter().getItemViewType(position);
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
        mContentRv.setLayoutManager(gridLayoutManager);
    }

//    private void initData() {
//        mDataOne = new ArrayList<DataTypeOne>();
//        for (int i = 0; i < 10; i++) {
//            DataTypeOne item = new DataTypeOne();
//            item.setColorTinyPic(colorSet[i % colorSet.length]);
//            item.setTinyName("TinyName" + i);
//            item.setLastMsg("lastMst:" + i);
//            mDataOne.add(item);
//        }
//
//        mDataTwo = new ArrayList<DataTypeTwo>();
//        for (int i = 0; i < 10; i++) {
//            DataTypeTwo item = new DataTypeTwo();
//            item.setColorTinyPic(colorSet[i % colorSet.length]);
//            item.setTinyName("TinyName" + i);
//            mDataTwo.add(item);
//        }
//
//        mDataThree = new ArrayList<DataTypeThree>();
//        for (int i = 0; i < 10; i++) {
//            DataTypeThree item = new DataTypeThree();
//            item.setColorTinyPic(colorSet[i % colorSet.length]);
//            item.setTinyName("TinyName" + i);
//            item.setColorBg(colorSet[i % colorSet.length]);
//            mDataThree.add(item);
//        }
//
//        mDataFour = new ArrayList<DataTypeGrid>();
//        for (int i = 0; i <= 10; i++) {
//            DataTypeGrid item = new DataTypeGrid();
//            item.setGoodsName("GoodsName" + i);
//            item.setGoodsPic(colorSet[i % colorSet.length]);
//            mDataFour.add(item);
//        }
//        mDataFive = new ArrayList<DataTypeGrid>();
//        for (int i = 0; i < 10; i++) {
//            DataTypeGrid item = new DataTypeGrid();
//            item.setGoodsName("GoodsName" + i);
//            item.setGoodsPic(colorSet[i % colorSet.length]);
//            mDataFive.add(item);
//        }
//
//        mDataHeader = new ArrayList<String>();
//        for (int i = 0; i < 5; i++) {
//            mDataHeader.add("Header" + i);
//        }
//
//        adapter = new MultiAdapter(getActivity());
//        adapter.setHeaderView(mBanner);
//        adapter.setData(mDataOne, mDataTwo, mDataThree, mDataFour, mDataFive, mDataHeader);
//        mContentRv.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }

    @Override
    public void onLoadMore() {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), "加载成功", Toast.LENGTH_SHORT).show();
        }
        DataTypeGrid dtg = new DataTypeGrid();
        dtg.setGoodsName("Add Item By LoadMore");
        dtg.setGoodsPic(android.R.color.holo_purple);
        //TODO loadMore add data
//        mDataFive.add(dtg);
//        adapter.updateData(mDataOne, mDataTwo, mDataThree, mDataFour, mDataFive, mDataHeader);
        mContentRv.post(new Runnable() {
            @Override
            public void run() {
                mContentRv.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
        mSwipToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
        }
        DataTypeOne dto = new DataTypeOne();
        dto.setColorTinyPic(android.R.color.holo_purple);
        dto.setTinyName("Add Item By Refresh");
        dto.setLastMsg("new message");
        //TODO refresh add data
//        mDataOne.add(0, dto);
//        adapter.updateData(mDataOne, mDataTwo, mDataThree, mDataFour, mDataFive, mDataHeader);
        mSwipToLoadLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
