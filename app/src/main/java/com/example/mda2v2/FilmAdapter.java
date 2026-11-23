package com.example.mda2v2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class FilmAdapter extends ArrayAdapter<Film> {
    public FilmAdapter(Context context, ArrayList<Film> films) {
        super(context, 0, films);
    }

    @NonNull @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(getContext())
                    .inflate(R.layout.film_item, parent, false);
        }
        Film film = getItem(position);
        ImageView image = row.findViewById(R.id.imgFilm);
        TextView name = row.findViewById(R.id.tvFilmName);
        TextView info = row.findViewById(R.id.tvFilmInfo);
        if (film != null) {
            image.setImageResource(film.imageId);
            name.setText(film.name);
            info.setText(film.description);
        }
        return row;
    }
}