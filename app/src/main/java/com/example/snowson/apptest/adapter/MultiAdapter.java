package com.example.snowson.apptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.DataTypeGrid;
import com.example.snowson.apptest.bean.DataTypeOne;
import com.example.snowson.apptest.bean.DataTypeThree;
import com.example.snowson.apptest.bean.DataTypeTwo;
import com.example.snowson.apptest.bean.MultiTypeBase;
import com.example.snowson.apptest.bean.TypesBlock;
import com.example.snowson.apptest.viewholder.TypeAbstractViewHolder;
import com.example.snowson.apptest.viewholder.TypeBannerViewHolder;
import com.example.snowson.apptest.viewholder.TypeGridViewHolder;
import com.example.snowson.apptest.viewholder.TypeHeaderViewHolder;
import com.example.snowson.apptest.viewholder.TypeOneViewHolder;
import com.example.snowson.apptest.viewholder.TypeThreeViewHolder;
import com.example.snowson.apptest.viewholder.TypeTwoViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by snowson on 17-12-14.
 */

public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TypesBlock> mData = new ArrayList<TypesBlock>();
    private List<DataTypeOne> mDataOne = new ArrayList<DataTypeOne>();
    private List<DataTypeTwo> mDataTwo = new ArrayList<DataTypeTwo>();
    private List<DataTypeThree> mDataThree = new ArrayList<DataTypeThree>();
    private List<DataTypeGrid> mDataFour = new ArrayList<DataTypeGrid>();
    private List<DataTypeGrid> mDataFive = new ArrayList<DataTypeGrid>();
    //记录全数据中当前item的类型
    private ArrayList<Integer> types = new ArrayList<Integer>();
    //记录各个类型的type的在总的数据中的起始下标
    private SparseIntArray mRealPosition = new SparseIntArray();
    private SparseIntArray mHeaderIndex = new SparseIntArray();
    private ArrayList<String> mHeader = new ArrayList<String>();
    private View mHeaderView = null;


    private LayoutInflater lInflater;

    public MultiAdapter(Context context) {
        lInflater = LayoutInflater.from(context);
    }

    //如果需要headerView, 请在设置数据前添加
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
//        updateData(mDataOne, mDataTwo, mDataThree, mDataFour, mDataFive, mHeader);
    }

    public void setData(List<TypesBlock> data) {
        mData.addAll(data);
    }

    public void updateData(List<DataTypeOne> dataOne,
                           List<DataTypeTwo> dataTwo,
                           List<DataTypeThree> dataThree,
                           List<DataTypeGrid> dataFour,
                           List<DataTypeGrid> dataFive,
                           List<String> headers) {
        mDataOne.clear();
        mDataTwo.clear();
        mDataThree.clear();
        mDataFour.clear();
        mDataFive.clear();
        mHeader.clear();
        types.clear();
        mRealPosition.clear();
        mHeaderIndex.clear();
        recordHeaderIndex = 0;

        setData(dataOne, dataTwo, dataThree, dataFour, dataFive, headers);
        notifyDataSetChanged();
    }

    public void setData(List<DataTypeOne> dataOne,
                        List<DataTypeTwo> dataTwo,
                        List<DataTypeThree> dataThree,
                        List<DataTypeGrid> dataFour,
                        List<DataTypeGrid> dataFive,
                        List<String> headers) {
        mDataOne.addAll(dataOne);
        mDataTwo.addAll(dataTwo);
        mDataThree.addAll(dataThree);
        mDataFour.addAll(dataFour);
        mDataFive.addAll(dataFive);
        mHeader.addAll(headers);

        if (mHeaderView != null) {
            types.add(0, TypeAbstractViewHolder.TYPE_BANNER);
        }

        addDataByType(TypeAbstractViewHolder.TYPE_ONE, dataOne);
        addDataByType(TypeAbstractViewHolder.TYPE_TWO, dataTwo);
        addDataByType(TypeAbstractViewHolder.TYPE_THREE, dataThree);
        addDataByType(TypeAbstractViewHolder.TYPE_FOUR, dataFour);
        addDataByType(TypeAbstractViewHolder.TYPE_FIVE, dataFive);
    }

    private int recordHeaderIndex = 0;
    private void addDataByType(int type, List data) {
        //记录
        if (data.size() > 0) {
            types.add(TypeAbstractViewHolder.TYPE_HEADER);
            mHeaderIndex.append(types.size() - 1, recordHeaderIndex++);
            mRealPosition.append(type, types.size());
        }
        for (int i = 0; i < data.size(); i++) {
            types.add(type);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TypeAbstractViewHolder.TYPE_ONE:
                return new TypeOneViewHolder(
                        lInflater.inflate(R.layout.item_type_one, parent, false));
            case TypeAbstractViewHolder.TYPE_TWO:
                return new TypeTwoViewHolder(
                        lInflater.inflate(R.layout.item_type_two, parent, false));
            case TypeAbstractViewHolder.TYPE_THREE:
                return new TypeThreeViewHolder(
                        lInflater.inflate(R.layout.item_type_three, parent, false));
            case TypeAbstractViewHolder.TYPE_FOUR:
                return new TypeGridViewHolder(
                        lInflater.inflate(R.layout.item_grid_one, parent, false));
            case TypeAbstractViewHolder.TYPE_FIVE:
                return new TypeGridViewHolder(
                        lInflater.inflate(R.layout.item_grid_one, parent, false));
            case TypeAbstractViewHolder.TYPE_HEADER:
                return new TypeHeaderViewHolder(
                        lInflater.inflate(R.layout.item_type_header, parent, false));
            case TypeAbstractViewHolder.TYPE_BANNER:
                return new TypeBannerViewHolder(mHeaderView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int realPosition = 0;
        //获取当前Item的type
        Integer type = types.get(position);
        if (type == TypeAbstractViewHolder.TYPE_HEADER) {
            realPosition = mHeaderIndex.get(position);
        } else if (type == TypeAbstractViewHolder.TYPE_BANNER) {
            return;
        } else {
            realPosition = position - mRealPosition.get(type);
        }
        switch (type) {
            case TypeAbstractViewHolder.TYPE_ONE:
                ((TypeOneViewHolder) holder).bind(mDataOne.get(realPosition));
                return;
            case TypeAbstractViewHolder.TYPE_TWO:
                ((TypeTwoViewHolder) holder).bind(mDataTwo.get(realPosition));
                return;
            case TypeAbstractViewHolder.TYPE_THREE:
                ((TypeThreeViewHolder) holder).bind(mDataThree.get(realPosition));
                return;
            case TypeAbstractViewHolder.TYPE_FOUR:
                ((TypeGridViewHolder) holder).bind(mDataFour.get(realPosition));
                return;
            case TypeAbstractViewHolder.TYPE_FIVE:
                ((TypeGridViewHolder) holder).bind(mDataFive.get(realPosition));
                return;
            case TypeAbstractViewHolder.TYPE_HEADER:
                ((TypeHeaderViewHolder) holder).bind(mHeader.get(realPosition));
                return;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return types.get(position);
    }

    @Override
    public int getItemCount() {
        return types.size();
    }
}
