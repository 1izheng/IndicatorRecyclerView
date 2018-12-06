package com.yjz.indicatorrecyclerview;

import android.content.Context;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页类别 hori adapter
 *
 * @author yjz
 *         created at 2018/11/20 下午4:22
 */

public class HomeHoriTypeAdapter extends CommonAdapter<String> {


    public HomeHoriTypeAdapter(Context context, List<String> datas) {
        super(context, R.layout.adapter_type_item, datas);
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    protected void convert(ViewHolder holder, String bean, int position) {
    }


    public void addDatas(List<String> datas) {
        int oldSize = getDatas().size();
        getDatas().addAll(datas);
        notifyDataSetChanged();
    }

    public void setDatas(List<String> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.mDatas.clear();
        this.mDatas.addAll(datas);
        notifyDataSetChanged();
    }
}
