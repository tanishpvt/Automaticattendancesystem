package com.example.automaticattendancesystem.OTP;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.automaticattendancesystem.Intermediate.MainScreen;
import com.example.automaticattendancesystem.MainActivity;
import com.example.automaticattendancesystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import java.util.concurrent.TimeUnit;

public class VerifyOTP extends AppCompatActivity {

    EditText t2;
    Button b2;
    String phonenumber;
    String otpid;
    FirebaseAuth mAuth;

    //remember me
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    //private static final String KEY_EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);


        //this line will hide the status bar from the screen
//        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        phonenumber=getIntent().getStringExtra("mobile").toString();
        t2=(EditText)findViewById(R.id.t2);
        b2=(Button)findViewById(R.id.b2);
        mAuth=FirebaseAuth.getInstance();

        //remember me
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);

        if (name != null){
            Intent intent = new Intent(VerifyOTP.this,MainScreen.class);
            startActivity(intent);
        }



        initiateotp();

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(t2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Blank Field can not be processed",Toast.LENGTH_LONG).show();
                else if(t2.getText().toString().length()!=6)
                    Toast.makeText(getApplicationContext(),"INvalid OTP",Toast.LENGTH_LONG).show();
                else
                {
                    PhoneAuthCredential credential=PhoneAuthProvider.getCredential(otpid,t2.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });

   /*     findViewById(R.id.textResendOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        VerifyOTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationId = newVerificationId;
                                Toast.makeText(VerifyOTP.this, "OTP Sent", Toast.LENGTH_SHORT).show();


                            }
                        }
                );

            }
        });

    */

    }


    private void initiateotp() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        otpid=s;
                    }

                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                    {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });        // OnVerificationStateChangedCallbacks

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {

                            //sharedpref   //remember me
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_NAME,t2.getText().toString());
                            editor.apply();

                            startActivity(new Intent(VerifyOTP.this, MainScreen.class));
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"Signin Code Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}