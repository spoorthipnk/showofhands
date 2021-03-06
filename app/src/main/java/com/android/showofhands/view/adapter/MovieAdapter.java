package com.android.showofhands.view.adapter;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.showofhands.R;
import com.android.showofhands.model.Movie;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    private List<Movie> movies = new ArrayList<>();
    private static final String MOVIE_IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/w342/";

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bind(movies.get(position));

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void updateMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_image)
        ImageView movie_image;
        @BindView(R.id.movie_title)
        TextView movie_title;
        @BindView(R.id.detailsLayout)
        ConstraintLayout detailsLayout;
     /*   @BindView(R.id.movie_rating)
        TextView movie_rating;*/
      /*  @BindView(R.id.movie_date)
        TextView movieDate;
        @BindView(R.id.movie_overview)
        TextView movieOverview;*/


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Movie movie) {

            movie_title.setText(movie.getOriginalTitle());

            Picasso.get()
                    .load(MOVIE_IMAGE_URL_PREFIX + movie.getPosterPath())
                    .into(new Target() {
                        @Override
                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                            movie_image.setImageBitmap(bitmap);
                            Palette.from(bitmap)
                                    .generate(new Palette.PaletteAsyncListener() {
                                        @Override
                                        public void onGenerated(@Nullable Palette palette) {
                                            Palette.Swatch textSwatch = palette.getDarkVibrantSwatch();
                                            if(textSwatch!=null) {
                                                detailsLayout.setBackgroundColor(textSwatch.getRgb());
                                            }
                                            else{
                                                detailsLayout.setBackgroundColor(Color.GRAY);
                                            }
                                        }
                                    });
                        }

                        @Override
                        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                        }

                        @Override
                        public void onPrepareLoad(Drawable placeHolderDrawable) {

                        }
                    });


                           //movieDate.setText(movie.getReleaseDate());
                           //movieOverview.setText(movie.getOverview());
                           //String rating = String.valueOf(movie.getVoteAverage() / 2);
                           //movie_rating.setText(rating);

        }

    }
}
