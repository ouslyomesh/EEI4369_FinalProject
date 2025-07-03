package com.s23010464.eei4369_finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Update extends AppCompatActivity {

    EditText name_input, comment_input;
    Button update_button, delete_button;

    ImageView backArrow;

    String id, name, comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        name_input = findViewById(R.id.name_input2);
        comment_input = findViewById(R.id.comment_input2);
        update_button = findViewById(R.id.btn_update_button);
        delete_button = findViewById(R.id.btn_delete_button);
        backArrow = findViewById(R.id.imgArrowBack);


        backArrow.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Reviews.class));
            finish();
        });


        //First call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null){
            ab.setTitle(name);
        }


//        update_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //And only then we call this
//                MyDatabaseHelper myDB = new MyDatabaseHelper(Update.this);
//                myDB.updateData(id, name, comment);
//            }
//        });

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update.this);

                //  Get updated values from EditText
                String updatedName = name_input.getText().toString().trim();
                String updatedComment = comment_input.getText().toString().trim();

                myDB.updateData(id, updatedName, updatedComment);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });




    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
            getIntent().hasExtra("comment")){

            // Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            comment = getIntent().getStringExtra("comment");

            // Setting intent Data
            name_input.setText(name);
            comment_input.setText(comment);

        }else{
            Toast.makeText(this,"No Data !", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Update.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}