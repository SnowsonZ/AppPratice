package com.example.snowson.apptest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.snowson.apptest.R;
import com.example.snowson.apptest.bean.DataTypeGrid;
import com.example.snowson.apptest.bean.DataTypeOne;
import com.example.snowson.apptest.bean.DataTypeThree;
import com.example.snowson.apptest.bean.DataTypeTwo;
import com.example.snowson.apptest.bean.MutilDataType;
import com.example.snowson.apptest.viewholder.TypeAbstractViewHolder;
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

public class MutilAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MutilDataType> mData = new ArrayList<MutilDataType>();
    private List<DataTypeOne> mDataOne = new ArrayList<DataTypeOne>();
    private List<DataTypeTwo> mDataTwo = new ArrayList<DataTypeTwo>();
    private List<DataTypeThree> mDataThree = new ArrayList<DataTypeThree>();
    private List<DataTypeGrid> mDataFour = new ArrayList<DataTypeGrid>();
    private List<DataTypeGrid> mDataFive = new ArrayList<DataTypeGrid>();
    //记录全数据中当前item的类型
    private ArrayList<Integer> types = new ArrayList<Integer>();
    //记录各个类型的type的在总的数据中的起始下标
    private SparseArray<Integer> mRealPosition = new SparseArray<Integer>();
    private ArrayList<String> mHeader = new ArrayList<String>();


    private LayoutInflater lInflater;

    public MutilAdapter(Context context) {
        lInflater = LayoutInflater.from(context);
    }

    public void setData(List<MutilDataType> data) {
        mData.addAll(data);
    }

    public void setData(ArrayList<DataTypeOne> dataOne,
                        ArrayList<DataTypeTwo> dataTwo,
                        ArrayList<DataTypeThree> dataThree,
                        ArrayList<DataTypeGrid> dataFour,
                        ArrayList<DataTypeGrid> dataFive,
                        ArrayList<String> headers) {
        mDataOne.addAll(dataOne);
        mDataTwo.addAll(dataTwo);
        mDataThree.addAll(dataThree);
        mDataFour.addAll(dataFour);
        mDataFive.addAll(dataFive);
        mHeader.addAll(headers);

        addDataByType(TypeAbstractViewHolder.TYPE_ONE, dataOne);
        addDataByType(TypeAbstractViewHolder.TYPE_TWO, dataTwo);
        addDataByType(TypeAbstractViewHolder.TYPE_THREE, dataThree);
        addDataByType(TypeAbstractViewHolder.TYPE_FOUR, dataFour);
        addDataByType(TypeAbstractViewHolder.TYPE_FIVE, dataFive);
    }

    private void addDataByType(int type, ArrayList data) {
        //记录
        types.add(TypeAbstractViewHolder.TYPE_HEADER);
        mRealPosition.append(type, types.size());
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
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int realPosition = 0;
        Integer type = types.get(position);
        if (type != TypeAbstractViewHolder.TYPE_HEADER) {
            realPosition = position - mRealPosition.get(type);
        }else {
            realPosition = types.get(position + 1) - 1;
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
