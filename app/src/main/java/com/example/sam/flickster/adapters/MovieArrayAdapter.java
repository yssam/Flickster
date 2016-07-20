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
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);

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

        //populate data
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());


        int orientation = getContext().getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivImage);
        }
        else if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            Picasso.with(getContext()).load(movie.getBackdropPath()).into(viewHolder.ivImage);
        }


        //return the view
        return convertView;


    }
}
