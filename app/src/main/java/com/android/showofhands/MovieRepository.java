package com.android.showofhands;

import android.app.Application;

import android.util.Log;

import com.android.showofhands.api.MovieService;
import com.android.showofhands.model.Movie;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class MovieRepository {

    private MutableLiveData<List<Movie>> movies;
    private CompositeDisposable disposable = new CompositeDisposable();
    private static final String TAG = "MovieViewModel";

    @Inject
    MovieService movieService;

    private static final String MOVIE_DATABASE_API_KEY="7f7d6745e4a6fc46b6ab115e9839c3ad";

    public MovieRepository() {

        if(movies == null){
            movies = new MutableLiveData<List<Movie>>();
            loadMovies();
        }

    }

    public LiveData<List<Movie>> getMovies(){

        return movies;
    }

    private void loadMovies() {

        disposable.add(
                movieService.getNowPlayingMovies(MOVIE_DATABASE_API_KEY)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                movieResponse -> {
                                    movies = movieResponse.results;
                                },
                                t -> {
                                    Log.e(TAG, "Error obtaining movies", t);
                                    // Toast.makeText(get(), "Error obtaining movies", Toast.LENGTH_SHORT).show();

                                }
                        )
        );

    }
}
