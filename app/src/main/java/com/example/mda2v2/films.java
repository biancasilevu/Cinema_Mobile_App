package com.example.mda2v2;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class films extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_films);

        ListView listFilms = findViewById(R.id.listFilms);
        ArrayList<Film> films = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            films.add(new Film("Biscuit", "Dog, 3 years", R.drawable.dog));
            films.add(new Film("Pickles", "Cat, 2 years", R.drawable.cat));
            films.add(new Film("Noodle", "Rabbit, 1 year", R.drawable.rabbit));
        }
        FilmAdapter adapter = new FilmAdapter(this, films);
        listFilms.setAdapter(adapter);

        listFilms.setOnItemClickListener((parent, view, position, id) -> {
            Film selected = films.get(position);
            Toast.makeText(this, "Selected: " + selected.name,
                    Toast.LENGTH_SHORT).show();
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}