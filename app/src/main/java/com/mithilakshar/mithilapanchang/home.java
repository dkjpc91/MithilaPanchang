package com.mithilakshar.mithilapanchang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {

    ImageSwitcher imageSwitcher;
    CardView calendar;
    int index=0;
    int[] imageList={R.drawable.mp,R.drawable.logo,R.drawable.festival,R.drawable.calendar,R.drawable.eclipse,R.drawable.mantra,R.drawable.manuscript};
    TextView textViewMonth,textViewDate,textViewDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        imageSwitcher=findViewById(R.id.imageSwitcher);
        calendar=findViewById(R.id.calendar);
        textViewMonth=findViewById(R.id.textViewMonth);
        textViewDate=findViewById(R.id.textViewDate);
        textViewDay=findViewById(R.id.textViewDay);

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


        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");

        String currentMonth = monthFormat.format(new Date());
        String currentDay = dayFormat.format(new Date());
        String currentDate = dateFormat.format(new Date());


        String hindiMonth = translateToHindi(currentMonth);
        String  hindiDay = translateToHindiday(currentDay);

        textViewMonth.setText(hindiMonth);
        textViewDate.setText(currentDate);
        textViewDay.setText(hindiDay);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),calendar.class);
                startActivity(i);
            }
        });

    }

    private String translateToHindi(String currentMonth) {
        // Manually create a mapping for English to Hindi month names
        Map<String, String> monthTranslation = new HashMap<>();
        monthTranslation.put("January", "जनवरी");
        monthTranslation.put("February", "फ़रवरी");
        monthTranslation.put("March", "मार्च");
        monthTranslation.put("April", "अप्रैल");
        monthTranslation.put("May", "मई");
        monthTranslation.put("June", "जून");
        monthTranslation.put("July", "जुलाई");
        monthTranslation.put("August", "अगस्त");
        monthTranslation.put("September", "सितंबर");
        monthTranslation.put("October", "अक्टूबर");
        monthTranslation.put("November", "नवंबर");
        monthTranslation.put("December", "दिसंबर");
        // Return the translated month name
        return monthTranslation.get(currentMonth);
    }

    private String translateToHindiday(String currentDay) {
        // Manually create a mapping for English to Hindi month names
        Map<String, String> monthTranslation = new HashMap<>();
        monthTranslation.put("Mon", "सोमवार");
        monthTranslation.put("Tue", "मंगलवार");
        monthTranslation.put("Wed", "बुधवार");
        monthTranslation.put("Thu", "गुरुवार");
        monthTranslation.put("Fri", "शुक्रवार");
        monthTranslation.put("Sat", "शनिवार");
        monthTranslation.put("Sun", "रविवार");
        // Return the translated month name
        return monthTranslation.get(currentDay);
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