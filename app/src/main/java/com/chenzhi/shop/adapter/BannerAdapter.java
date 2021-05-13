package com.chenzhi.shop.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BannerAdapter extends PagerAdapter {
    private Context context;
    private int[] ImageUrl;

    public BannerAdapter(Context context, int[] imageUrl) {
        super();
        this.context = context;
        ImageUrl = imageUrl;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView image = new ImageView(context);

        image.setImageResource(ImageUrl[position%ImageUrl.length]);

        image.setScaleType(ImageView.ScaleType.FIT_XY);

        container.addView(image);

        return image;
    }
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView((View)object);
    }
}
