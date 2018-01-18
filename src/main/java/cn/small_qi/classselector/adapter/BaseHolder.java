package cn.small_qi.classselector.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.small_qi.classselector.bean.ClassItem;
import cn.small_qi.classselector.bean.MainItem;

/**
 * Created by chengqi_nong on 2018/1/17.
 */

public abstract class BaseHolder extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
    }
    public abstract void bindView(MainItem item);
}
