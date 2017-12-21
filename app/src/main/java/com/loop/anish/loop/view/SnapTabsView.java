package com.loop.anish.loop.view;


import android.animation.ArgbEvaluator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.loop.anish.loop.R;

/**
 * Created by Admin on 06-12-2017.
 */

public class SnapTabsView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private ImageView mCenterImage;
    private ImageView mStartImage;
    private ImageView mEndImage;
    private ImageView mBottomimage;

    private View mIndicator;

    private ArgbEvaluator mArgEvaluator;

    private int mCenterColor;
    private int mSideColor;

    private int mEndViewsTranslationX;
    private int mIndicatorTranslationX;
    private int mCenterTranslationY;


    public SnapTabsView(@NonNull Context context) {
        this(context, null);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SnapTabsView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();

    }

    public void setUpWithViewPager(final ViewPager viewPager) {

        viewPager.addOnPageChangeListener(this);

        mStartImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() != 0) {

                    viewPager.setCurrentItem(0);

                }
            }
        });

        mEndImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() != 2) {

                    viewPager.setCurrentItem(2);

                }
            }
        });

    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_snap_tabs, this, true);

        mCenterImage = (ImageView) findViewById(R.id.vst_center_image);
        mStartImage = (ImageView) findViewById(R.id.vst_start_image);
        mBottomimage = (ImageView) findViewById(R.id.vst_bottom_image);
        mEndImage = (ImageView) findViewById(R.id.vst_end_image);

        mIndicator = findViewById(R.id.vst_indicator);

        mCenterColor = ContextCompat.getColor(getContext(), R.color.white);
        mSideColor = ContextCompat.getColor(getContext(), R.color.dark_grey);

        mArgEvaluator = new ArgbEvaluator();

        mIndicatorTranslationX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());

        mBottomimage.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                mEndViewsTranslationX = (int) ((mBottomimage.getX() - mStartImage.getX()) - mIndicatorTranslationX);
                mBottomimage.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                mCenterTranslationY = getHeight() - mBottomimage.getBottom();

            }
        });


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0) {

            setColor(1 - positionOffset);
            moveViews(1 - positionOffset);

            moveAndScaleCenter(1 - positionOffset);

            mIndicator.setTranslationX((positionOffset - 1) * mIndicatorTranslationX);

        } else if (position == 1) {
            setColor(positionOffset);
            moveViews(positionOffset);

            moveAndScaleCenter(positionOffset);

            mIndicator.setTranslationX(positionOffset * mIndicatorTranslationX);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void moveAndScaleCenter(float fractionFromCenter) {

        float scale = .7f + ((1 - fractionFromCenter) * .3f);

        mCenterImage.setScaleX(scale);
        mCenterImage.setScaleY(scale);

        int translation = (int) (fractionFromCenter * mCenterTranslationY);

        mCenterImage.setTranslationY(translation);
        mBottomimage.setTranslationY(translation);

        mBottomimage.setAlpha(1 - fractionFromCenter);

    }

    private void moveViews(float fractionFromCenter) {

        mStartImage.setTranslationX(fractionFromCenter * mEndViewsTranslationX);
        mEndImage.setTranslationX(-fractionFromCenter * mEndViewsTranslationX);

        mIndicator.setAlpha(fractionFromCenter);
        mIndicator.setScaleX(fractionFromCenter);

    }

    private void setColor(float fractionFromCenter) {

        int color = (int) mArgEvaluator.evaluate(fractionFromCenter, mCenterColor, mSideColor);

        mCenterImage.setColorFilter(color);
        mStartImage.setColorFilter(color);
        mEndImage.setColorFilter(color);
    }
}
