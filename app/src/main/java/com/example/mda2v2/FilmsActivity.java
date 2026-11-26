package com.example.mda2v2;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class FilmsActivity extends AppCompatActivity {

    private final ArrayList<Film> allFilms = new ArrayList<>();
    private final ArrayList<Film> shownFilms = new ArrayList<>();
    private FilmAdapter adapter;
    private Spinner spinnerGenre, spinnerAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_films);

        ListView listFilms = findViewById(R.id.listFilms);
        spinnerGenre = findViewById(R.id.spinnerGenre);
        spinnerAge = findViewById(R.id.spinnerAge);
//        ArrayList<Film> films = new ArrayList<>();
//        for (int i = 0; i < 1; i++) {
            allFilms.add(new Film("Primetime", "An ambitious TV presenter and a young actor become involved in a reality show designed to catch paedophiles in this drama based on a true story.\n", R.drawable.primetime_movie, "Genre: Action / Drama / Fantasy", "18"));
            allFilms.add(new Film("Spiderman", "Follows an isolated adult Peter Parker living anonymously in New York City after the world forgot his true identity, as full-time vigilante work and loneliness trigger a dangerous physical evolution while he confronts a new threat.\n ", R.drawable.spiderman, "Sci-Fin / Fantasy", "12A"));
            allFilms.add(new Film("The Odyssey", "An epic action-fantasy film written and directed by Christopher Nolan, adapting Homer's ancient Greek poem about King Odysseus's perilous voyage home to Ithaca after the Trojan War.\n", R.drawable.odyssey, " Sci-Fi / Fantasy", "15"));
            allFilms.add(new Film("Obsession", "Follows Bear, who uses a 'One Wish Willow' to make his friend Nikki love him. The wish backfires into violent, terrifying consequences as Nikki loses her autonomy and the situation spirals out of control.\n ", R.drawable.obsession, "Horror", "18"));
            allFilms.add(new Film("Wildwood", " Is a dark fantasy stop-motion animated film from LAIKA Studios about a young girl who enters a hidden, magical forest outside Portland, Oregon, to rescue her kidnapped baby brother.\n ", R.drawable.wildwood, "Animation / Fantasy / Adventure", "12A"));
            allFilms.add(new Film("Clayface", " Follows a disfigured actor who undergoes an experimental treatment that turns him into a shape-shifting monster driven by revenge. \n", R.drawable.clayface, "Horror  / Psychological thriller", "18"));
            allFilms.add(new Film("Star Wars: Episode III – Revenge of the Sith", " Concludes the prequel trilogy by following Anakin Skywalker's fall to the dark side as Darth Vader and the rise of the Galactic Empire. \n", R.drawable.starwars,"Sci-Fi / action / Adventure / Fantasy", "12A"));
            allFilms.add(new Film("The Hunger Games: Sunrise on the Reaping", " Sunrise on the Reaping will revisit the world of Panem twenty-four years before the events of The Hunger Games, starting on the morning of the reaping of the Fiftieth Hunger Games, also known as the Second Quarter Quell. \n", R.drawable.hungergames, "Dystopian / Sci-Fi", "12A"));
            allFilms.add(new Film("Project Hail Mary", "Ryland Grace, a former science teacher who wakes up with amnesia aboard a deep-space ship on a desperate mission to save Earth from a dying sun.\n ", R.drawable.hailmary,"Science fiction / Fantasy", "12A"));
            allFilms.add(new Film("The Drama", "A happily engaged couple is put to the test when an unexpected turn sends their wedding week off the rails. \n", R.drawable.drama,"Romance / Comedy / Drama", "12A"));
            allFilms.add(new Film("Transformer", "The story follows teenager Sam Witwicky as he gets caught in a war on Earth between the heroic Autobots and evil Decepticons over the powerful.\n ", R.drawable.transformers,"Sci-Fi / Action / Adventure", "12"));
            allFilms.add(new Film("The Twilight Saga: Breaking Dawn - Part 2", "Following Bella's transformation into a vampire and the birth of her daughter Renesmee, the Cullens and their allies gather to defend the child from the Volturi.\n ", R.drawable.twilight,"Paranormal romance / Fantasy", "15"));

        String[] genres = {"All genres", "Action", "Adventure", "Animation", "Comedy",
                "Drama", "Dystopian", "Fantasy", "Horror", "Romance", "Sci-Fi", "Thriller"};
        String[] ages = {"All ages", "12A", "18", "15"};

        spinnerGenre.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, genres));
        spinnerAge.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, ages));

        shownFilms.addAll(allFilms);
        adapter = new FilmAdapter(this, shownFilms);   // assigns the field, no "FilmAdapter" type before it
        listFilms.setAdapter(adapter);

//        FilmAdapter adapter = new FilmAdapter(this, films);
//        listFilms.setAdapter(adapter);
//
//        listFilms.setOnItemClickListener((parent, view, position, id) -> {
//            Film selected = films.get(position);
//            Toast.makeText(this, "Selected: " + selected.name,
//                    Toast.LENGTH_SHORT).show();
//        });
        AdapterView.OnItemSelectedListener filterListener =
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        applyFilters();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                };
        spinnerGenre.setOnItemSelectedListener(filterListener);
        spinnerAge.setOnItemSelectedListener(filterListener);

        listFilms.setOnItemClickListener((parent, view, position, id) -> {
            Film selected = adapter.getItem(position); // not allFilms.get(position)
            if (selected != null) {
                Toast.makeText(this, "Selected: " + selected.name,
                        Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void applyFilters() {
        String genre = spinnerGenre.getSelectedItem().toString();
        String age = spinnerAge.getSelectedItem().toString();

        shownFilms.clear();
        for (Film f : allFilms) {
            boolean genreOk = genre.equals("All genres") || f.genre.equals(genre);
            boolean ageOk = age.equals("All ages") || f.age.equals(age);
            if (genreOk && ageOk) {
                shownFilms.add(f);
            }
        }
        adapter.notifyDataSetChanged();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}