package com.mithilakshar.mithilapanchang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class home extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    int index=0;
    int[] imageList={R.drawable.mp,R.drawable.logo};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSwitcher=findViewById(R.id.imageSwitcher);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });


        imageSwitcher.setImageResource(imageList[0]);
        startImageSlide();

    }

    private void startImageSlide() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                index=(index+1)% imageList.length;
                imageSwitcher.setImageResource(imageList[index]);
                startImageSlide();
            }
        }, 2500);

    }
}