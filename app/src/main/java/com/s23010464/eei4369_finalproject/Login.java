package com.s23010464.eei4369_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    Button btnSignUp, btnLogin;
    EditText editUser, editPassword;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUser = findViewById(R.id.editUser);
        editPassword = findViewById(R.id.editPassword);
        btnSignUp = findViewById(R.id.buttonSignUpLG);
        btnLogin = findViewById(R.id.buttonLoginLG);

        DB = new DatabaseHelper(this);

        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignUp.class);
            startActivity(intent);
            finish();
        });

        btnLogin.setOnClickListener(v -> {
            String username = editUser.getText().toString();
            String password = editPassword.getText().toString();

            if (username.equals("") || password.equals("")) {
                Toast.makeText(Login.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean check = DB.checkUser(username, password);
                if (check) {
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), home.class));
                    finish();
                } else {
                    Toast.makeText(Login.this, "Invalid Login Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
