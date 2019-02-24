package com.android.showofhands.view.viewmodel;

import com.android.showofhands.MovieRepository;
import com.android.showofhands.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;


    private LiveData<List<Movie>> movies;


    public MovieViewModel( ) {
        //super(application);
        movieRepository = new MovieRepository();
        movies = movieRepository.getMovies();
    }


    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
