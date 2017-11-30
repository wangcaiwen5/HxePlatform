package com.hxe.hxeplatform.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bumptech.glide.Glide;
import com.bwie.uploadpicture.view.CircleImageView;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.MyLeftAdapter;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.myview.MyToolBar;
import com.hxe.hxeplatform.ui.fragment.JokesFragment;
import com.hxe.hxeplatform.ui.fragment.RecommendFragment;
import com.hxe.hxeplatform.ui.fragment.VideoFragment;
import com.hxe.hxeplatform.utils.ToastShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fl_Container)
    FrameLayout flContainer;
    @BindView(R.id.bottomBar)
    BottomNavigationBar bottomBar;
    @BindView(R.id.m_toolbar)
    MyToolBar mToolbar;
    @BindView(R.id.dl_leftRight)
    DrawerLayout drawerLayout;
    @BindView(R.id.rl_left_menu)
    RelativeLayout LeftMenu;
    @BindView(R.id.tv_nikename)
    TextView tvNikename;
    @BindView(R.id.civ_imgView)
    CircleImageView ivHead;
    @BindView(R.id.iv_gender)
    ImageView ivGender;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    private List<Fragment> fragments = new ArrayList<>();
    private int curFragment = -1;
    private FragmentManager manager;

    @Override
    protected boolean getIsWindow() {
        return false;
    }

    @Override
    protected void init() {
        Glide.with(getApplicationContext()).load("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=311229171,4189910992&fm=27&gp=0.jpg").into(ivHead);

        manager = getSupportFragmentManager();
        initView();
        initData();
        initBottom();
        /*SpaceItemDecoration spaceItemDecoration = new SpaceItemDecoration.Builder().setTopEdge(50).build();
        rvList.addItemDecoration(spaceItemDecoration);*/
        rvList.setLayoutManager(new LinearLayoutManager(this));
        MyLeftAdapter adapter = new MyLeftAdapter(this);
        rvList.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastShow.getSingleton(MainActivity.this).showToast("点击了"+position);
            }
        });

    }


    private void initView() {
       // final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        mToolbar.setLeftTitleDrawable(R.mipmap.ic_launcher_round);
        mToolbar.setMainTitle("推荐");
        mToolbar.setRightTitleDrawable(R.mipmap.write);
        mToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);


            }
        });

        mToolbar.setRightTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           gotoActivity(WriteActivity.class,false);
            }
        });
    }





    private void initBottom() {
        bottomBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomBar.addItem(new BottomNavigationItem(R.mipmap.recommend_select, "推荐").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.recommend_normal))).setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.mipmap.jokes_select, "段子").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.jokes_normal)))
                .addItem(new BottomNavigationItem(R.mipmap.video_select, "视频").setInactiveIcon(ContextCompat.getDrawable(this, R.mipmap.video_normal))).initialise();

        bottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switchTab(position);
                if(position==0){
                    mToolbar.setMainTitle("推荐");
                }else if (position==1){

                    mToolbar.setMainTitle("段子");
                }else{
                    mToolbar.setMainTitle("视频");
                }

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        switchTab(0);


    }

    private void switchTab(int position) {
        Fragment fragment = manager.findFragmentByTag("" + position);
        FragmentTransaction beginTransaction = manager.beginTransaction();
        if (fragment == null) {
            if (manager.findFragmentByTag("" + curFragment) != null) {
                beginTransaction.hide(fragments.get(curFragment));
            }
            beginTransaction.add(R.id.fl_Container, fragments.get(position), "" + position)
                    .show(fragments.get(position))
                    .commit();


        } else if (curFragment != position) {
            beginTransaction.hide(fragments.get(curFragment))
                    .show(fragments.get(position))
                    .commit();
        }
        curFragment = position;
    }

    private void initData() {
        fragments.add(new RecommendFragment());
        fragments.add(new JokesFragment());
        fragments.add(new VideoFragment());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    //声明一个long类型变量：用于存放上一点击“返回键”的时刻
    private long mExitTime;


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (fragments != null) {
            fragments = null;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                ToastShow.getSingleton(this).showToast("再按一次退出程序");
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
