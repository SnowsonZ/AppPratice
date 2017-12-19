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
import com.example.snowson.apptest.adapter.MutilAdapter;
import com.example.snowson.apptest.bean.DataTypeGrid;
import com.example.snowson.apptest.bean.DataTypeOne;
import com.example.snowson.apptest.bean.DataTypeThree;
import com.example.snowson.apptest.bean.DataTypeTwo;
import com.example.snowson.apptest.bean.MutilDataType;
import com.example.snowson.apptest.utils.ScreenUtils;
import com.example.snowson.apptest.view.Banner;
import com.example.snowson.apptest.viewholder.TypeAbstractViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MutilRecyclerViewActivity extends AppCompatActivity
        implements OnRefreshListener, OnLoadMoreListener {

    private RecyclerView rv_content;
    private List<MutilDataType> mData = new ArrayList<MutilDataType>();
    private int colorSet[] = {android.R.color.holo_green_light,
            android.R.color.holo_orange_light, android.R.color.holo_blue_light};
    private ArrayList<String> mImageUrl = new ArrayList<String>();
    private MutilAdapter adapter;
    private Banner mBanner;
    private SwipeToLoadLayout mSwipToLoadLayout;
    private ArrayList<DataTypeOne> data_one;
    private ArrayList<DataTypeTwo> data_two;
    private ArrayList<DataTypeThree> data_three;
    private ArrayList<DataTypeGrid> data_four;
    private ArrayList<DataTypeGrid> data_five;
    private ArrayList<String> data_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil_recycler_view);
        rv_content = findViewById(R.id.swipe_target);
        mBanner = new Banner(this);
        mSwipToLoadLayout = findViewById(R.id.swipToLoadLayout);
        mSwipToLoadLayout.setOnRefreshListener(this);
        mSwipToLoadLayout.setOnLoadMoreListener(this);
        ViewGroup.LayoutParams params = mBanner.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) ScreenUtils.dp2px(this, 200));
        }
        mBanner.setLayoutParams(params);
        mImageUrl.add("#ff33b5e5");
        mImageUrl.add("#ff99cc00");
        mImageUrl.add("#ffffbb33");
        mBanner.initData(mImageUrl);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        //根据item type判断item占据RecyclerView一行内容的具体大小,实现Grid,List混合布局
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = rv_content.getAdapter().getItemViewType(position);
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
        rv_content.setLayoutManager(gridLayoutManager);
        initData();
    }

    private void initData() {
        data_one = new ArrayList<DataTypeOne>();
        for (int i = 0; i < 10; i++) {
            DataTypeOne item = new DataTypeOne();
            item.setColorTinyPic(colorSet[i % colorSet.length]);
            item.setTinyName("TinyName" + i);
            item.setLastMsg("lastMst:" + i);
            data_one.add(item);
        }

        data_two = new ArrayList<DataTypeTwo>();
        for (int i = 0; i < 10; i++) {
            DataTypeTwo item = new DataTypeTwo();
            item.setColorTinyPic(colorSet[i % colorSet.length]);
            item.setTinyName("TinyName" + i);
            data_two.add(item);
        }

        data_three = new ArrayList<DataTypeThree>();
        for (int i = 0; i < 10; i++) {
            DataTypeThree item = new DataTypeThree();
            item.setColorTinyPic(colorSet[i % colorSet.length]);
            item.setTinyName("TinyName" + i);
            item.setColorBg(colorSet[i % colorSet.length]);
            data_three.add(item);
        }

        data_four = new ArrayList<DataTypeGrid>();
        for (int i = 0; i <= 10; i++) {
            DataTypeGrid item = new DataTypeGrid();
            item.setGoodsName("GoodsName" + i);
            item.setGoodsPic(colorSet[i % colorSet.length]);
            data_four.add(item);
        }
        data_five = new ArrayList<DataTypeGrid>();
        for (int i = 0; i < 10; i++) {
            DataTypeGrid item = new DataTypeGrid();
            item.setGoodsName("GoodsName" + i);
            item.setGoodsPic(colorSet[i % colorSet.length]);
            data_five.add(item);
        }

        data_header = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            data_header.add("Header" + i);
        }

        adapter = new MutilAdapter(this);
        adapter.setHeaderView(mBanner);
        adapter.setData(data_one, data_two, data_three, data_four, data_five, data_header);
        rv_content.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
        DataTypeGrid dtg = new DataTypeGrid();
        dtg.setGoodsName("Add Item By LoadMore");
        dtg.setGoodsPic(android.R.color.holo_purple);
        data_five.add(dtg);
        adapter.updateData(data_one, data_two, data_three, data_four, data_five, data_header);
        rv_content.post(new Runnable() {
            @Override
            public void run() {
                rv_content.scrollToPosition(adapter.getItemCount() - 1);
            }
        });
        mSwipToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRefresh() {
        Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
        DataTypeOne dto = new DataTypeOne();
        dto.setColorTinyPic(android.R.color.holo_purple);
        dto.setTinyName("Add Item By Refresh");
        dto.setLastMsg("new message");
        data_one.add(0, dto);
        adapter.updateData(data_one, data_two, data_three, data_four, data_five, data_header);
        mSwipToLoadLayout.setRefreshing(false);
    }
}
