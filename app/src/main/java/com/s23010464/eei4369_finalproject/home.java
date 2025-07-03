package com.s23010464.eei4369_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home extends AppCompatActivity {

    ImageView img_bookImage, catBackgroud, backrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        img_bookImage = findViewById(R.id.bookImage);

        img_bookImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), aboutBook.class);
                startActivity(intent);
                finish();
            }
        });


        catBackgroud = findViewById(R.id.catBackgroud);
        catBackgroud.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), bookCategories.class));
            finish();
        });


        backrev = findViewById(R.id.revBackground);
        backrev.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Reviews.class));
            finish();
        });

    }
}