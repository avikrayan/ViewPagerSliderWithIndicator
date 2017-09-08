package com.example.avik.viewpagersliderwithindicator;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    SliderPagerAdapter sliderPagerAdapter;
    ArrayList<String> slider_image_list;
    private TextView[] dots;
    int page_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // method for initialisation
        init();
        // method for adding indicators
        addBottomDots(0);
        imageSlideShowAutomatic();

    }

    private void imageSlideShowAutomatic() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == slider_image_list.size()) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                vp_slider.setCurrentItem(page_position, true);
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 5000);
    }

    private void init() {
        vp_slider = (ViewPager) findViewById(R.id.vp_slider);
        ll_dots = (LinearLayout) findViewById(R.id.ll_dots);

        slider_image_list = new ArrayList<>();

        slider_image_list.add("https://spark.adobe.com/images/landing/examples/laborday-etsy-banner.jpg");
        slider_image_list.add("https://cdn-images-1.medium.com/max/800/0*8h8g4Wzps_Bsx77-.png");
        slider_image_list.add("http://www.retail.kiwi/system/images/W1siZiIsIjIwMTcvMDEvMzEvMTJfMDlfMjFfNzUxX1NJbmdsZVBhZ2VCYW5uZXJfV2hhdE1ha2VzWW91ckJ1c2luZXNzU3RhbmRPdXQuanBnIl1d/SInglePageBanner-WhatMakesYourBusinessStandOut.jpg");
        slider_image_list.add("http://www.photohaat.com/images/websiteimage/sliderImage/banner-3-old%20holi.jpg");
        slider_image_list.add("http://racway.com/uploaded_image/amazon-diwali-sale-banner1.jpg");
        slider_image_list.add("http://www.mnb.com.au/libraries/images/master_slider/MNB_banner-05_hotDeals.jpg");


        sliderPagerAdapter = new SliderPagerAdapter(MainActivity.this, slider_image_list);
        vp_slider.setAdapter(sliderPagerAdapter);

        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[slider_image_list.size()];

        ll_dots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.parseColor("#000000"));
            ll_dots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.parseColor("#FFFFFF"));
    }
}
