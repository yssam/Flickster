package com.example.sam.flickster.models;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sam.flickster.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        ImageView imageView = (ImageView) findViewById(R.id.ivMovieImageDetail);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitleDetail);
        TextView tvOverview = (TextView) findViewById(R.id.tvOverviewDetail);
        TextView tvDate = (TextView) findViewById(R.id.tvDateDetail);

        Picasso.with(this).load(movie.getBackdropPath())
                .placeholder(R.drawable.user_placeholder)
                .error(R.drawable.user_placeholder_error)
                .resize(0, 500)
                .into(imageView);
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());
        tvDate.setText(movie.getDate());

    }
}
