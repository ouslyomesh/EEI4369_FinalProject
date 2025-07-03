//package com.s23010464.eei4369_finalproject;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//public class aboutBook extends AppCompatActivity {
//    Button btn_Review, btn_Shop, button_back;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_about_book);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//
//        btn_Review = findViewById(R.id.buttonReview);
//
//        btn_Review.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Reviews.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
//        btn_Shop = findViewById(R.id.buttonShop);
//
//        btn_Shop.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), ShopLocation.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//
//        button_back = findViewById(R.id.buttonback);
//
//        button_back.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), home.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}



package com.s23010464.eei4369_finalproject;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class aboutBook extends AppCompatActivity implements SensorEventListener {
    Button btn_Review, btn_Shop, button_back;

    private SensorManager sensorManager;
    private Sensor lightSensor;

    private boolean isDarkMode = false; // Keep track of current theme

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//
//        // Set initial theme before setting content view
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//        setContentView(R.layout.activity_about_book);
//
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean isDark = getSharedPreferences("theme", MODE_PRIVATE).getBoolean("isDarkMode", false);
        AppCompatDelegate.setDefaultNightMode(isDark ?
                AppCompatDelegate.MODE_NIGHT_YES :
                AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_book);


        // Initialize light sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        btn_Review = findViewById(R.id.buttonReview);
        btn_Review.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), Reviews.class));
            finish();
        });

        btn_Shop = findViewById(R.id.buttonShop);
        btn_Shop.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ShopLocation.class));
            finish();
        });

        button_back = findViewById(R.id.buttonback);
        button_back.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), home.class));
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (lightSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        float lux = event.values[0];
//
//        if (lux < 9000 && !isDarkMode) {
//            isDarkMode = true;
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            recreate(); // Recreate activity to apply theme
//        } else if (lux >= 9000 && isDarkMode) {
//            isDarkMode = false;
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            recreate(); // Recreate activity to apply theme
//        }
//    }

//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        float lux = event.values[0];
//
//        // Add buffer range to avoid rapid flickering around threshold
//        if (lux < 8500 && !isDarkMode) {
//            isDarkMode = true;
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            recreate();
//        } else if (lux > 9500 && isDarkMode) {
//            isDarkMode = false;
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            recreate();
//        }
//    }

    private long lastSwitchTime = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];

        // Get stored mode
        boolean currentMode = getSharedPreferences("theme", MODE_PRIVATE).getBoolean("isDarkMode", false);
        long now = System.currentTimeMillis();

        if (lux < 8500 && !currentMode && now - lastSwitchTime > 2000) {
            lastSwitchTime = now;
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            saveThemePreference(true);
            recreate();
        } else if (lux > 9500 && currentMode && now - lastSwitchTime > 2000) {
            lastSwitchTime = now;
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            saveThemePreference(false);
            recreate();
        }
    }

    private void saveThemePreference(boolean isDark) {
        getSharedPreferences("theme", MODE_PRIVATE)
                .edit()
                .putBoolean("isDarkMode", isDark)
                .apply();
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not used
    }
}
