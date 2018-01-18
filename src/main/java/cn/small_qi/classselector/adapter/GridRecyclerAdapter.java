package cn.small_qi.classselector.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.small_qi.classselector.R;
import cn.small_qi.classselector.bean.ClassItem;

/**
 * Created by chengqi_nong on 2018/1/18.
 */

public class GridRecyclerAdapter extends RecyclerView.Adapter<GridRecyclerAdapter.GridViewHolder>{
    private List<ClassItem> mItems ;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public GridRecyclerAdapter(Context context) {
        mContext = context;
        mItems =new ArrayList<>();
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GridViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_sub_content,parent,false));
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
            holder.bindView(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setAllData(List<ClassItem> subItems) {
        mItems.clear();
        mItems.addAll(subItems);
        notifyDataSetChanged();
    }

    class GridViewHolder extends RecyclerView.ViewHolder{
        private TextView mClassName;
        private ClassItem curItem;
        public GridViewHolder(View itemView) {
            super(itemView);
            mClassName=itemView.findViewById(R.id.textview);
            if (mOnItemClickListener!=null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mOnItemClickListener.onClick(curItem);
                    }
                });
            }
        }
        public void bindView(ClassItem item){
            curItem = item;
            mClassName.setText(item.getLevelTwoName());
        }
    }

    public interface OnItemClickListener{
        void onClick(ClassItem item);
    }
}
