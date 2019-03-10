package com.android.showofhands.view.viewmodel;

import com.android.showofhands.repository.MoviesRepository;
import com.android.showofhands.model.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MovieViewModel extends ViewModel {

    private MoviesRepository moviesRepository;


    private LiveData<List<Movie>> movies;


    public MovieViewModel( ) {
        //super(application);
        moviesRepository = new MoviesRepository();
        movies = moviesRepository.getMovies();
    }


    public LiveData<List<Movie>> getMovies() {
        return movies;
    }
}
