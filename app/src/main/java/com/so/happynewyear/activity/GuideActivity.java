package com.so.happynewyear.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.so.happynewyear.R;
import com.so.happynewyear.adapter.GuideAdapter;

import java.util.ArrayList;

public class GuideActivity extends AppCompatActivity {

    private ViewPager vp_pager;
    private LinearLayout ll_guide_point;
    private ImageView iv_red_point;
    private Button bt_start;
    private ArrayList<ImageView> imageViews;

    private int[] mImageIds = new int[]
            {R.drawable.guide_1_720, R.drawable.guide_2_720, R.drawable.guide_3_720};
    private int mPointsDis;
    private static final String TAG = "sorrower";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initUI();
        initData();

        vp_pager.setAdapter(new GuideAdapter(this, imageViews));

        vp_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float leftMargin = mPointsDis * (position + positionOffset);

                RelativeLayout.LayoutParams params
                        = (RelativeLayout.LayoutParams) iv_red_point.getLayoutParams();

                params.leftMargin = (int) leftMargin;

                iv_red_point.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == imageViews.size() - 1) {
                    bt_start.setVisibility(View.VISIBLE);
                } else {
                    bt_start.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        iv_red_point.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        //measure->layout->draw(在onCreate执行完成之后执行)
                        //layout方法执行结束之后的回调
                        mPointsDis = ll_guide_point.getChildAt(1).getLeft() - ll_guide_point.getChildAt(0).getLeft();
                        Log.i(TAG, "mPointsDis: " + mPointsDis);
                    }
                });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < mImageIds.length; i++) {
            //1. 添加图片资源
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            imageViews.add(view);

            //2. 初始化导航圆点
            ImageView point = new ImageView(this);

            //3. 修改属性值
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            if (i > 0) {
                params.leftMargin = 32;
            }

            point.setLayoutParams(params);

            //4. 添加导航圆点
            point.setImageResource(R.drawable.shape_point_gray);
            ll_guide_point.addView(point);
        }
    }

    /**
     * 初始化界面ui
     */
    private void initUI() {
        vp_pager = (ViewPager) findViewById(R.id.vp_pager);
        ll_guide_point = (LinearLayout) findViewById(R.id.ll_guide_point);
        iv_red_point = (ImageView) findViewById(R.id.iv_red_point);
        bt_start = (Button) findViewById(R.id.bt_start);
    }

    /**
     * @param v 开始体验按钮
     */
    public void toMain(View v) {
        finish();
    }
}
