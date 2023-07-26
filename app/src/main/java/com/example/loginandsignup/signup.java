package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    Button signup,cancel;
    EditText email,pass;
    TextView t1;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup=findViewById(R.id.signup1);
        cancel=findViewById(R.id.cancel);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        t1=findViewById(R.id.intent);
        mauth=FirebaseAuth.getInstance();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signup.this, login.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createuser();
            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public  void createuser()
    {
        String email1=email.getText().toString().trim();
        String pass1=pass.getText().toString().trim();

        if(TextUtils.isEmpty(email1))
        {
            email.setError("Email Cannot be Empty");
            email.requestFocus();
        } else if (TextUtils.isEmpty(pass1))
        {
            pass.setError("password Cannot be Empty");
            pass.requestFocus();
        }
        else
        {
            mauth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(signup.this, "User Register Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this, login.class));
                    }
                    else
                    {
                        Toast.makeText(signup.this, "Registeratio Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



}