package com.s23010464.eei4369_finalproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Reviews extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    Button backBtnrew;
    MyDatabaseHelper myDB;
    ArrayList<String> com_id, name, comment;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reviews);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        ExtendedFloatingActionButton back_button = findViewById(R.id.back_button);
        back_button.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), aboutBook.class));
            finish();
        });

//        backBtnrew = findViewById(R.id.backRev);
//
//        backBtnrew.setOnClickListener(v -> {
//            startActivity(new Intent(getApplicationContext(), aboutBook.class));
//            finish();
//        });

        add_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Reviews.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(Reviews.this);
        com_id = new ArrayList<>();
        name = new ArrayList<>();
        comment = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(Reviews.this, this, com_id, name, comment);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Reviews.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                com_id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                comment.add(cursor.getString(2));

            }
        }
    }



}