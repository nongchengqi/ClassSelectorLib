package cn.small_qi.classselector.bean;

import java.util.List;

/**
 * Created by chengqi_nong on 2018/1/17.
 */

public class MainItem {
    private String name;
    private boolean isSelect;
    private int type;
    private List<ClassItem> subItems;

    public MainItem(String name, boolean isSelect, int type, List<ClassItem> subItems) {
        this.name = name;
        this.isSelect = isSelect;
        this.type = type;
        this.subItems = subItems;
    }

    public MainItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public List<ClassItem> getSubItems() {
        return subItems;
    }
    public void setSubItems(List<ClassItem> subItems) {
        this.subItems = subItems;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
