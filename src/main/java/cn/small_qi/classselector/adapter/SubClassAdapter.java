package cn.small_qi.classselector.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.List;

import cn.small_qi.classselector.R;
import cn.small_qi.classselector.bean.ClassItem;
import cn.small_qi.classselector.bean.MainItem;

/**
 * Created by chengqi_nong on 2018/1/17.
 */

public class SubClassAdapter extends RecyclerView.Adapter<BaseHolder> {
    private Context mContext;
    private List<MainItem> mItems;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public SubClassAdapter(Context context) {
        mContext = context;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SubClassItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_sub, parent, false), mContext);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bindView(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setDates(List<MainItem> dates) {
        mItems = dates;
        notifyDataSetChanged();
    }


    //--holder
    public class SubClassItemHolder extends BaseHolder {
        private GridRecyclerAdapter mAdapter;
        private TextView nameTv;
        private  RecyclerView mGridView;;

        public SubClassItemHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            nameTv = itemView.findViewById(R.id.sub_name_tv);
            mAdapter = new GridRecyclerAdapter(context);
            mGridView= itemView.findViewById(R.id.gridView);
            mGridView.setLayoutManager(new GridLayoutManager(context,4, LinearLayoutManager.VERTICAL,false));
            mGridView.setAdapter(mAdapter);
            if (mOnItemClickListener != null) {
                mAdapter.setOnItemClickListener(new GridRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(ClassItem item) {
                        mOnItemClickListener.onClick(item);
                    }
                });
            }

        }

        public void bindView( MainItem classItem) {
            nameTv.setText(classItem.getName());
            mAdapter.setAllData(classItem.getSubItems());

        }
    }

    public interface OnItemClickListener {
        void onClick(ClassItem position);
    }

}
