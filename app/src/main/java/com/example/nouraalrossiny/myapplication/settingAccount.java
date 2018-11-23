package com.example.nouraalrossiny.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class settingAccount extends AppCompatActivity implements View.OnClickListener {


    EditText ePassword;
    FirebaseUser user;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_account);

        ePassword =findViewById(R.id.epass);
        user = FirebaseAuth.getInstance().getCurrentUser();
        progressBar=findViewById(R.id.progressbar);

        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.deleteAccount).setOnClickListener(this);
        //update password
        //phone if we need
        //delete an account
    }

    public void Update() {
        String Password = ePassword.getText().toString().trim();

        if (user != null) { //logged in
            progressBar.setVisibility(View.VISIBLE);
            user.updatePassword(Password)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "تم تحديث كلمة المرور بنجاح..", Toast.LENGTH_SHORT).show();

                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
        public void delete () {
        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
             if(task.isSuccessful())
             {
                 Toast.makeText(getApplicationContext(),"تم حذف الحساب بنجاح",Toast.LENGTH_LONG).show();
             }
             else{
                 Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
             }
            }
        });

        }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                Update();
                finish();
                startActivity(new Intent(this, ProfileActivity.class));
                break;

            case R.id.deleteAccount:
                delete();
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}

