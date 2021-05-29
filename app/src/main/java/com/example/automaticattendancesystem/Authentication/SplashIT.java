package com.example.automaticattendancesystem.Authentication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.automaticattendancesystem.R;

public class SplashIT extends AppCompatActivity {

    private ImageView img_logo, img_bottom;
    private TextView tv_name;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_i_t);

        Utils.blackIconStatusBar(SplashIT.this,R.color.light_Background);

        img_logo =findViewById(R.id.img_logo);
        img_bottom =findViewById(R.id.img_bottom);
        tv_name =findViewById(R.id.tv_name);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashIT.this,LoginActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashIT.this,
                        Pair.create(img_logo,"logo"),
                        Pair.create(img_bottom,"img_tree"),
                        Pair.create(tv_name,"logo_text"));
                startActivity(intent,options.toBundle());
            }
        },300);
    }
}