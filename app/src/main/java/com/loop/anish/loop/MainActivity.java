package com.loop.anish.loop;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.loop.anish.loop.adapter.MainPagerAdapter;
import com.loop.anish.loop.view.SnapTabsView;

public class MainActivity extends AppCompatActivity {

    //First Push Ever

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View background = findViewById(R.id.am_background_view);
        ViewPager viewPager = (ViewPager) findViewById(R.id.am_view_pager);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        SnapTabsView snapTabsView = (SnapTabsView) findViewById(R.id.am_snap_tabs);
        snapTabsView.setUpWithViewPager(viewPager);

        viewPager.setCurrentItem(1);

        final int colorBlue = ContextCompat.getColor(this, R.color.light_blue);
        final int colorPurple = ContextCompat.getColor(this, R.color.light_purple);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if(position==0){
                    background.setBackgroundColor(colorBlue);
                    background.setAlpha(1-positionOffset);

                }else if (position==1){

                    background.setBackgroundColor(colorPurple);
                    background.setAlpha(positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
