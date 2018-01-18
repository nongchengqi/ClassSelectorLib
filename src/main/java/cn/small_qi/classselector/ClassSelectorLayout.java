package cn.small_qi.classselector;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.small_qi.classselector.adapter.MainClassAdapter;
import cn.small_qi.classselector.adapter.SubClassAdapter;
import cn.small_qi.classselector.bean.MainItem;

/**
 * Created by chengqi_nong on 2018/1/17.
 */

public class ClassSelectorLayout extends LinearLayout {

    private List<MainItem> mDates;
    private Map<Integer,MainItem> mMainDates;

    private RecyclerView mMainClassRv;
    private RecyclerView mSubClassRv;

    private MainClassAdapter mMainClassAdapter;
    private SubClassAdapter mSubClassAdapter;

    private LinearLayoutManager mSubLayoutManager;

    private boolean isClickSelect;

    public ClassSelectorLayout(Context context) {
        this(context,null);
    }

    public ClassSelectorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClassSelectorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflatLayout();
    }

    private void inflatLayout() {
        LayoutInflater.from(getContext()).inflate(R.layout.selector_layout,this,true);
        mMainClassRv=findViewById(R.id.n_class_selector_main_rv);
        mMainClassRv.setLayoutManager(new LinearLayoutManager(getContext()));

        mSubClassRv=findViewById(R.id.n_class_selector_sub_rv);
        mSubLayoutManager=new LinearLayoutManager(getContext());
        mSubClassRv.setLayoutManager(mSubLayoutManager);


    }

    public ClassSelectorLayout setClassDates(List<MainItem> list){
            mDates = list;
            generateMainItems();
            return this;
    }

    private void generateMainItems() {
        mMainDates = new HashMap<>();
        for (int i=0;i<mDates.size();i++){
            mMainDates.put(i,new MainItem(mDates.get(i).getName(),mDates.get(i).isSelect(), 0, null));
        }
    }
    public void init(){
        mMainClassAdapter = new MainClassAdapter(getContext());
        mSubClassAdapter = new SubClassAdapter(getContext());

        mMainClassRv.setAdapter(mMainClassAdapter);
        mSubClassRv.setAdapter(mSubClassAdapter);

        mMainClassAdapter.setDates(mMainDates);
        mSubClassAdapter.setDates(mDates);
       // mSubClassRv.setNestedScrollingEnabled(true);
       listenerScrollAndClick();

    }

    private void listenerScrollAndClick() {
        mMainClassAdapter.setOnItemClickListener(new MainClassAdapter.OnItemClickListener() {
            @Override
            public void onClick(MainItem item) {
                for (int position:mMainDates.keySet()) {
                    if (mMainDates.get(position).getName().equals(item.getName())){
                        isClickSelect=true;
                        moveToPosition(mSubClassRv,position);
                        //如果主分类大于屏幕高度，则被选中的主分类条目滑动至屏幕可见范围
                        moveToPositionIfNoCompletelyVisible(mMainClassRv,position);
                        moveToPositionIfNoCompletelyVisible(mMainClassRv,position);
                        break;
                    }
                }
            }
        });

        //监听子类滑动联动更新主类选中状态
        mSubClassRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&isClickSelect){
                    isClickSelect =false;
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isClickSelect){return;}
                //改变选中状态
                mMainClassAdapter.setMainClassClick(mDates.get(mSubLayoutManager.findFirstCompletelyVisibleItemPosition()).getName());
                //如果主分类大于屏幕高度，则被选中的主分类条目滑动至屏幕可见范围
                moveToPositionIfNoCompletelyVisible(mMainClassRv,mSubLayoutManager.findLastCompletelyVisibleItemPosition());
                moveToPositionIfNoCompletelyVisible(mMainClassRv,mSubLayoutManager.findFirstCompletelyVisibleItemPosition());

            }
        });
    }


 /*   private void moveToPosition(int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        int firstItem = mSubLayoutManager.findFirstCompletelyVisibleItemPosition();
        int lastItem = mSubLayoutManager.findLastCompletelyVisibleItemPosition();
        //区分情况
        if (n <= firstItem ){
            //此情况可以直接移动
            mSubClassRv.smoothScrollToPosition(n);
        }else if ( n <= lastItem ){
            //当要置顶的项已经在屏幕上显示时
            int top = mSubClassRv.getChildAt(n - firstItem).getTop();
            mSubClassRv.smoothScrollBy(0, top);
        }else{
            mSubClassRv.smoothScrollToPosition(n);
        }
    }*/

    //TODO 滑动到顶部而不是只是滑动到可见状态
    private void moveToPosition(RecyclerView recyclerView,int n) {
        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstItem = layoutManager.findFirstCompletelyVisibleItemPosition();
        int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
        //区分情况
        if (n <= firstItem ){
            //此情况可以直接移动
            recyclerView.smoothScrollToPosition(n);
        }else if ( n <= lastItem ){
            //当要置顶的项已经在屏幕上显示时
            int top = recyclerView.getChildAt(n - firstItem).getTop();
            recyclerView.smoothScrollBy(0, top);
        }else{
            recyclerView.smoothScrollToPosition(n);
        }
    }

    //TODO 滑动到顶部而不是只是滑动到可见状态
    private void moveToPositionIfNoCompletelyVisible(RecyclerView recyclerView,int n) {

        //先从RecyclerView的LayoutManager中获取第一项和最后一项的Position
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (layoutManager.findFirstCompletelyVisibleItemPosition()>n||layoutManager.findLastVisibleItemPosition()==n) {
            Log.i("TAG", "moveToPositionIfNoCompletelyVisible: "+n);
            int firstItem = layoutManager.findFirstCompletelyVisibleItemPosition();
            int lastItem = layoutManager.findLastCompletelyVisibleItemPosition();
            //区分情况
            if (n <= firstItem) {
                //此情况可以直接移动
                recyclerView.smoothScrollToPosition(n);
            } else if (n <= lastItem) {
                //当要置顶的项已经在屏幕上显示时
                int top = recyclerView.getChildAt(n - firstItem).getTop();
                recyclerView.smoothScrollBy(0, top);
            } else {
                recyclerView.smoothScrollToPosition(n);
            }

        }else{
            return;
        }
    }

    public void setOnItemClickListener(SubClassAdapter.OnItemClickListener listener){
        mSubClassAdapter.setOnItemClickListener(listener);
    }

}
