package cn.small_qi.classselector.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Map;

import cn.small_qi.classselector.R;
import cn.small_qi.classselector.bean.MainItem;

/**
 * Created by chengqi_nong on 2018/1/17.
 */

public class MainClassAdapter extends RecyclerView.Adapter<MainClassAdapter.MainClassItemHolder> {
    private Context mContext;
    private Map<Integer, MainItem> mItems;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MainClassAdapter(Context context) {
        mContext = context;
    }

    @Override
    public MainClassItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainClassItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false));
    }

    @Override
    public void onBindViewHolder(MainClassItemHolder holder, int position) {
        holder.bindView(mItems.get(position));
        Log.i("TAG", "onBindViewHolder: " + mItems.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setDates(Map<Integer, MainItem> dates) {
        mItems = dates;
        notifyDataSetChanged();
    }

    public void setMainClassClick(String name) {
        int pos = -1;
        for (int i = 0; i < mItems.size(); i++) {
            MainItem item = mItems.get(i);
            //遍历把不是当前选中的条目的选中状态去掉
            if (item.getName().equals(name)) {
                if (item.isSelect()) {//当前选中的条目已经是选中状态，不用做任何更新
                    return;
                }
                pos = i;
            } else if (!item.getName().equals(name) && item.isSelect()) {
                mItems.put(i, new MainItem(item.getName(), false, 0, null));
                notifyDataSetChanged();
            }
        }
        mItems.put(pos, new MainItem(name, true, 0, null));
        notifyDataSetChanged();
    }


    //---holder
    public class MainClassItemHolder extends RecyclerView.ViewHolder {
        private TextView nameTv;
        private MainItem mMainItem;

        public MainClassItemHolder(View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.main_name_tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setMainClassClick(mMainItem.getName());
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onClick(mMainItem);
                    }
                }
            });


        }

        public void bindView(final MainItem item) {
            mMainItem = item;
            if (item.isSelect()) {
                nameTv.setText(item.getName());
                nameTv.setTextColor(Color.RED);
            } else {
                nameTv.setText(item.getName());
                nameTv.setTextColor(Color.BLACK);
            }
        }
    }

    public interface OnItemClickListener {
        void onClick(MainItem item);
    }

}
