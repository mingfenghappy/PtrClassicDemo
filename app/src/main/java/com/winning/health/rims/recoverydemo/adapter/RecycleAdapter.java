package com.winning.health.rims.recoverydemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.winning.health.rims.recoverydemo.R;
import com.winning.health.rims.recoverydemo.model.Project;
import com.winning.health.rims.recoverydemo.view.SlidingButtonView;

import java.util.List;

/**
 * desc:
 * author：djj on 2017/7/3 12:49
 * 简书：http://www.jianshu.com/u/dfbde65a03fc
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> implements SlidingButtonView.IonSlidingButtonListener {

    private String[] imageUrls;
    private List<Project> data;
    private Context mContext;
    private SlidingButtonView mMenu = null;

    public RecycleAdapter(String[] imageUrls, List<Project> data, Context mContext) {
        this.imageUrls = imageUrls;
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        //将数据填充到具体的view中
        Glide
                .with(mContext)
                .load(imageUrls[position])
                .placeholder(R.mipmap.nine)//预显示图片
                .error(R.mipmap.ic_launcher)//错误请求显示图片
                .crossFade(500)//淡入淡出动画
                .skipMemoryCache(true)//跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.NONE)//四种情况（根据里面的参数），跳过磁盘缓存，默认全部缓存
                .priority(Priority.IMMEDIATE)//图片请求的优先级（四个级别）
                .thumbnail(0.1f)//缩略图
                .into(holder.itemPic);
        holder.itemTitle.setText(data.get(position).getItemTitle());
        holder.itemDuration.setText(data.get(position).getItemDuration() + "");
        holder.startTask.setText(data.get(position).getStartTask());
        holder.tvDelete.setText(data.get(position).getTvDelete());

        //设置内容布局的宽为屏幕宽度
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        holder.layoutContent.getLayoutParams().width = width;

        // 如果设置了回调，则设置点击事件
        if (mIDeleteBtnClickListener != null) {
            holder.startTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onStartItemClick(view, holder, n);
                }
            });
            holder.layoutContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //判断侧滑是否打开
                    if (menuIsOpen()) {
                        closeMenu();
                    } else {
                        int n = holder.getLayoutPosition();
                        mIDeleteBtnClickListener.onDeleteBtnClick(view, holder, n);
                    }
                }
            });
            holder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onDeleteBtnClick(view, holder, n);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 滑动或者点击了Item监听
     *
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if (menuIsOpen()) {
            if (mMenu != slidingButtonView) {
                closeMenu();
            }
        }
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;
    }

    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if (mMenu != null) {
            return true;
        }
        return false;
    }


    /**
     * 定义接口回调
     */
    private IonSlidingViewClickListener mIDeleteBtnClickListener;

    public void setOnSlidingViewClickListener(IonSlidingViewClickListener listener) {
        this.mIDeleteBtnClickListener = listener;
    }

    public interface IonSlidingViewClickListener {
        void onStartItemClick(View view, MyViewHolder holder, int position);

        void onDeleteBtnClick(View view, MyViewHolder holder, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemPic;
        public TextView itemTitle;
        public TextView itemDuration;
        public TextView startTask;
        public TextView tvDelete;
        public ViewGroup layoutContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemPic = itemView.findViewById(R.id.iv_pic);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemDuration = itemView.findViewById(R.id.item_duration);
            startTask = itemView.findViewById(R.id.start_task);
            tvDelete = itemView.findViewById(R.id.tv_delete);
            layoutContent = itemView.findViewById(R.id.layout_content);
            ((SlidingButtonView) itemView).setSlidingButtonListener(RecycleAdapter.this);

        }
    }
}