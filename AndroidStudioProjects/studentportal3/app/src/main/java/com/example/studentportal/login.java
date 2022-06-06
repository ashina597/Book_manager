package com.example.studentportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email,password;
    Button login;
    FirebaseAuth myauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        myauth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (username.isEmpty() || pass.isEmpty())
                {
                    email.setError("Please fill in credentials");
                }
                myauth.signInWithEmailAndPassword(username,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(com.example.studentportal.login.this, "you are logged in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(com.example.studentportal.login.this, Homepage.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(com.example.studentportal.login.this, "you are not logged in", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}