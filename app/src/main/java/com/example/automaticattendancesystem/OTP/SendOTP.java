package com.example.automaticattendancesystem.OTP;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.automaticattendancesystem.Intermediate.MainScreen;
import com.example.automaticattendancesystem.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class SendOTP extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText t1;
    Button b1;

    //sharedpref   //remember me
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";

    //sharedpref   //remember me
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME,null);

        if (name != null){
            Intent intent = new Intent(SendOTP.this, MainScreen.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);


        //this line will hide the status bar from the screen
//        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        t1=(EditText)findViewById(R.id.t1);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1=(Button)findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SendOTP.this,VerifyOTP.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });

    }

}