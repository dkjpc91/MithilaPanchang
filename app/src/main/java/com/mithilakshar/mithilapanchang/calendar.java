package com.mithilakshar.mithilapanchang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class calendar extends AppCompatActivity {

    ImageSwitcher calendarImageSwitcher;
    TextView monthName;
    int index=0;

    int[] calendarImageList={R.drawable.mp,R.drawable.logo,R.drawable.festival,R.drawable.calendar,R.drawable.eclipse,R.drawable.mantra,R.drawable.manuscript};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendarImageSwitcher=findViewById(R.id.calendarImageSwitcher);
        monthName=findViewById(R.id.monthName);

        Intent intent = getIntent();
        String hindiMonth = intent.getStringExtra("month");
        monthName.setText(hindiMonth);




        // Create a FragmentManager to manage fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Begin a new FragmentTransaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Add the fragment to the container (R.id.fragment_container is assumed to be a FrameLayout in your activity)
        calendarfragment cf=new calendarfragment();
        fragmentTransaction.replace(R.id.fragmentContainer,cf);

        // Commit the transaction
        fragmentTransaction.commit();

        calendarImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                return imageView;
            }
        });


        calendarImageSwitcher.setImageResource(calendarImageList[0]);
        startImageSlide();



    }

    private void startImageSlide() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                index=(index+1)% calendarImageList.length;
                calendarImageSwitcher.setImageResource(calendarImageList[index]);
                startImageSlide();
            }
        }, 2500);
    }


}