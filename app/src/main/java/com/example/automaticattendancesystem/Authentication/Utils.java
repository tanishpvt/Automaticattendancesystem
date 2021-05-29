package com.example.automaticattendancesystem.Authentication;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

public class Utils {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void  blackIconStatusBar(Activity activity, int color)
    {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, color));
    }
}
