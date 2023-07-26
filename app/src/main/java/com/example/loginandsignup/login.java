package com.example.loginandsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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


public class login extends AppCompatActivity {
    Button login,cancel;
    EditText email,pass;
    TextView t1;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login1);
        cancel=findViewById(R.id.cancel);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        t1=findViewById(R.id.intent);
        mauth=FirebaseAuth.getInstance();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, signup.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
    }
    public void loginuser()
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
            mauth.signInWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"));
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(login.this, "Registeratio Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }
}