package com.so.happynewyear.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * PagerAdapter适配器
 * Created by so on 2018/2/9.
 */

public class GuideAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<ImageView> mImageViews;

    /**
     * @param context
     * @param imageViews
     */
    public GuideAdapter(Context context, ArrayList<ImageView> imageViews) {
        mContext = context;
        mImageViews = imageViews;
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化item
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = mImageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    /**
     * 销毁item
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
