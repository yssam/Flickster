package com.example.sam.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sam.flickster.R;
import com.example.sam.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sam on 7/18/16.
 */
public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    private static class ViewHolder{
        ImageView ivImage;
        TextView tvTitle;
        TextView tvOverview;
    }
    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for position
        Movie movie = getItem(position);

        //check the existing view being reused
        ViewHolder viewHolder; // view lookup cache stored in tag

        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            ImageView ivImage;
            TextView tvTitle;
            TextView tvOverview;

            LayoutInflater inflater = LayoutInflater.from(getContext());
            if(movie.getVoteAverage() >= 5.0) { //item_movie_back_drop
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie_back_drop, null);
                //find the image view
                ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage2);
                tvTitle = (TextView) convertView.findViewById(R.id.tvTitle2);
                tvOverview = (TextView) convertView.findViewById(R.id.tvOverview2);
            }
            else {//item_movie
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_movie, null);
                //find the image view
                ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
                tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            }

            tvTitle.setText(movie.getOriginalTitle());
            tvOverview.setText(movie.getOverview());

            if(movie.getVoteAverage() >= 5.0) { //item_movie_back_drop
                Picasso.with(getContext()).load(movie.getBackdropPath())
                        .placeholder(R.drawable.user_placeholder)
                        .error(R.drawable.user_placeholder_error)
                        //.fit().centerCrop()
                        .resize(900,0)
                        .into(ivImage);
            }
            else {//item_movie
                Picasso.with(getContext()).load(movie.getPosterPath())
                        .placeholder(R.drawable.user_placeholder)
                        .error(R.drawable.user_placeholder_error)
                        //.resize(0,400)
                        .into(ivImage);
            }


        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (convertView == null) {
                viewHolder = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.from(getContext()).inflate(R.layout.item_movie, parent, false);

                //find the image view
                viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

                //clear out image from convertView
                viewHolder.ivImage.setImageResource(0);

                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

                convertView.setTag(viewHolder);
            }
            else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tvTitle.setText(movie.getOriginalTitle());
            viewHolder.tvOverview.setText(movie.getOverview());

            if(movie.getVoteAverage() >= 5.0) {
                Picasso.with(getContext()).load(movie.getBackdropPath())
                        .placeholder(R.drawable.user_placeholder)
                        .error(R.drawable.user_placeholder_error)
                        .resize(0, 500)
                        .into(viewHolder.ivImage);
            }
            else {
                Picasso.with(getContext()).load(movie.getPosterPath())
                        .placeholder(R.drawable.user_placeholder)
                        .error(R.drawable.user_placeholder_error)
                        .resize(0, 500)
                        .into(viewHolder.ivImage);
            }
        }


        //return the view
        return convertView;


    }
}
