package com.example.mda2v2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            findViewById(R.id.btnViewWildwood).setOnClickListener(v ->
                    startActivity(new Intent(this, FilmsActivity.class)));

            BottomNavigationView nav = findViewById(R.id.bottomNavigationView);
            nav.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.films) {
                    startActivity(new Intent(this, FilmsActivity.class));
                }
                return true;
            });


        }
}
