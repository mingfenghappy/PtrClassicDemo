package com.winning.health.rims.recoverydemo.model;

/**
 * desc: 治疗项目实体类
 * <p>
 * author：djj
 * <p>
 * date: 2017/7/3 13:12
 * <p>
 * blog：http://www.jianshu.com/u/dfbde65a03fc
 */

public class Project {

    private int itemPic;
    private String itemTitle;
    private int itemDuration;
    private String startTask;
    private String tvDelete;

    public int getItemPic() {
        return itemPic;
    }

    public void setItemPic(int itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public int getItemDuration() {
        return itemDuration;
    }

    public void setItemDuration(int itemDuration) {
        this.itemDuration = itemDuration;
    }

    public String getStartTask() {
        return startTask;
    }

    public void setStartTask(String startTask) {
        this.startTask = startTask;
    }

    public String getTvDelete() {
        return tvDelete;
    }

    public void setTvDelete(String tvDelete) {
        this.tvDelete = tvDelete;
    }
}
