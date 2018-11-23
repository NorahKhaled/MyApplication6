package com.example.nouraalrossiny.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity{

    FirebaseAuth mAuth;
    EditText RestEmailInput;
    Button ResetPasswordSendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        RestEmailInput = findViewById(R.id.newPass);
        ResetPasswordSendEmailButton=findViewById(R.id.submit);

    }

    public void updatePass(View view) {
        String userEmail=RestEmailInput.getText().toString().trim();

        if(TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(getApplicationContext(),"please enter valid email first..",Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(getApplicationContext(),"please enter your email to reset Password..",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    else
                    {
                        //type of error
                        String message= task.getException().getMessage();
                        Toast.makeText(getApplicationContext(),"Error occured"+message,Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}
