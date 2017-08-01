package com.winning.health.rims.recoverydemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.winning.health.rims.recoverydemo.R;
import com.winning.health.rims.recoverydemo.adapter.RecycleAdapter;
import com.winning.health.rims.recoverydemo.model.Project;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicDefaultFooter;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Project> projectList = new ArrayList<Project>();
    private PtrClassicFrameLayout ptrClassicLayout;
    public static String[] eatFoodyImages = {
            "http://upload-images.jianshu.io/upload_images/2229183-e46e778a6fdebc82.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240",
            "http://i.imgur.com/C9pBVt7.jpg",
            "http://i.imgur.com/rT5vXE1.jpg",
            "http://i.imgur.com/aIy5R2k.jpg",
            "http://i.imgur.com/MoJs9pT.jpg",
            "http://i.imgur.com/S963yEM.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg",
            "http://i.imgur.com/COzBnru.jpg",
            "http://i.imgur.com/Z3QjilA.jpg",
            "http://i.imgur.com/rLR2cyc.jpg",
            "http://i.imgur.com/SEPdUIx.jpg",
            "http://i.imgur.com/aC9OjaM.jpg",
            "http://i.imgur.com/76Jfv9b.jpg",
            "http://i.imgur.com/fUX7EIB.jpg",
            "http://i.imgur.com/syELajx.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDate();
        initView();
    }

    //初始化数据
    private void initDate() {
        for (int i = 0; i < 20; i++) {
            Project project = new Project();
            project.setItemPic(R.mipmap.ic_launcher_round);
            project.setItemTitle("微波治疗");
            project.setItemDuration(10);
            project.setStartTask("开始");
            project.setTvDelete("取消");
            projectList.add(project);
        }
    }

    //初始化控件
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        ptrClassicLayout = (PtrClassicFrameLayout) findViewById(R.id.ptr_classic_frame_layout);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecycleAdapter(eatFoodyImages, projectList, MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnSlidingViewClickListener(new RecycleAdapter.IonSlidingViewClickListener() {
            @Override
            public void onStartItemClick(View view, final RecycleAdapter.MyViewHolder holder, int position) {
                //判断状态调收费接口
                CountDownTimer timer = new CountDownTimer((20 * 1000), 1000) {
                    @Override
                    public void onTick(long l) {
                        holder.itemDuration.setText(l / 1000 + "");
                        holder.startTask.setBackgroundResource(R.drawable.button_load_back);
                        holder.startTask.setTextColor(Color.parseColor("#0BC0BA"));
                        holder.startTask.setText("进行");

                    }

                    @Override
                    public void onFinish() {
                        holder.itemDuration.setText("00:00:00");
                        holder.startTask.setBackgroundResource(R.drawable.button_no_back);
                        holder.startTask.setText("完成");
                    }
                };
                timer.start();
            }

            @Override
            public void onDeleteBtnClick(View view, RecycleAdapter.MyViewHolder holder, int position) {
                Toast.makeText(MainActivity.this, "onDeleteBtnClick", Toast.LENGTH_SHORT).show();

            }
        });
        /**
         * 经典 风格的头部实现
         */
        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        PtrClassicDefaultFooter footer = new PtrClassicDefaultFooter(this);
        footer.setPadding(0, 0, 0, PtrLocalDisplay.dp2px(15));

        ptrClassicLayout.setHeaderView(header);
        ptrClassicLayout.addPtrUIHandler(header);

        ptrClassicLayout.setFooterView(footer);
        ptrClassicLayout.addPtrUIHandler(footer);
        //mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        ptrClassicLayout.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler2.checkContentCanBePulledUp(frame, content, footer);
            }

            /**
             * 加载更多的回调
             * @param frame
             */
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            Project project = new Project();
                            project.setItemTitle("电子生物反馈疗法");
                            project.setItemDuration(30);
                            project.setStartTask("开始");
                            project.setTvDelete("取消");
                            projectList.add(project);
                        }
                        mAdapter.notifyDataSetChanged();
                        ptrClassicLayout.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler2.checkContentCanBePulledDown(frame, content, header);
            }

            /**
             * 下拉刷新的回调
             * @param frame
             */
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        ptrClassicLayout.refreshComplete();
                    }
                }, 1000);
            }
        });

    }
}
