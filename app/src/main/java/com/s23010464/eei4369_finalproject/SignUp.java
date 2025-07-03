package com.s23010464.eei4369_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    Button btnLogin, btnSignUp;
    EditText editUser, editEmail, editPassword;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editUser = findViewById(R.id.editUser);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnLogin = findViewById(R.id.buttonLogin);
        btnSignUp = findViewById(R.id.buttonSignUp);

        DB = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });

        btnSignUp.setOnClickListener(v -> {
            String username = editUser.getText().toString();
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            if (username.equals("") || email.equals("") || password.equals("")) {
                Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean insert = DB.insertUser(username, email, password);
                if (insert) {
                    Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                } else {
                    Toast.makeText(SignUp.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

