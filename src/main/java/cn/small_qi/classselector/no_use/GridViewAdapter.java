package cn.small_qi.classselector.no_use;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.small_qi.classselector.R;
import cn.small_qi.classselector.bean.ClassItem;

/**
 * Created by chengqi_nong on 2018/1/17.
 */

public class GridViewAdapter extends BaseAdapter {

    List<ClassItem> mItems ; // 声明ImageView的对象

    private Context mContext;
    public GridViewAdapter(Context context) {
        mContext = context;
        mItems =new ArrayList<>();
    }

    public void setAllData(List<ClassItem> itemList){
        if (itemList==null) return;
        mItems.clear();
        mItems.addAll(itemList);
        notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sub_content,parent,false);
            holder=new ViewHolder();
            holder.mTextView = convertView.findViewById(R.id.textview);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(mItems.get(position).getLevelTwoName());
        return convertView;
    }

    /*
     * 功能：获得当前选项的ID
     *
     * @see android.widget.Adapter#getItemId(int)
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     * 功能：获得当前选项
     *
     * @see android.widget.Adapter#getItem(int)
     */
    @Override
    public ClassItem getItem(int position) {
        return mItems.get(position);
    }

    /*
     * 获得数量
     *
     * @see android.widget.Adapter#getCount()
     */
    @Override
    public int getCount() {
        return mItems.size();
    }

    class ViewHolder{
        private TextView mTextView;
    }
}
