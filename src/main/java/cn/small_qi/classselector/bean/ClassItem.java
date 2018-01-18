package cn.small_qi.classselector.bean;

import java.util.List;

/**
 * Created by chengqi_nong on 2018/1/17.
 */

public class ClassItem {
    public static int TYPE_MAIN_CLASS=1;//一级分类
    public static int TYPE_SUB_CLASS_HEADER=2;//二级分类
    public static int TYPE_SUB_CLASS_CONTENT=3;//三级分类

    private String levelOneName;
    private String levelTwoName;
    private int type;
    private int iconResId;
    private int level;

    public ClassItem() {
    }

    public ClassItem(String levelOneName, String levelTwoName, int type, int iconResId, int level) {
        this.levelOneName = levelOneName;
        this.levelTwoName = levelTwoName;
        this.type = type;
        this.iconResId = iconResId;
        this.level = level;
    }

    public String getLevelOneName() {
        return levelOneName;
    }

    public void setLevelOneName(String levelOneName) {
        this.levelOneName = levelOneName;
    }

    public String getLevelTwoName() {
        return levelTwoName;
    }

    public void setLevelTwoName(String levelTwoName) {
        this.levelTwoName = levelTwoName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
