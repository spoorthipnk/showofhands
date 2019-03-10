package com.android.showofhands.repository;

import android.util.Log;

import com.android.showofhands.api.NetworkApi;
import com.android.showofhands.model.Movie;
import com.android.showofhands.model.Show;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import static com.android.showofhands.BuildConfig.MOVIE_DATABASE_API_KEY;

public class MoviesRepository {

    private MutableLiveData<List<Movie>> movies;

    private CompositeDisposable disposable = new CompositeDisposable();
    private static final String TAG = "MovieViewModel";

    //@Inject
    //NetworkService movieService;

    public MoviesRepository() {

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
                NetworkApi.getInstance().getNowPlayingMovies(MOVIE_DATABASE_API_KEY)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                movieResponse -> {
                                    movies.setValue(movieResponse.getMovies());

                                },
                                t -> {
                                    Log.e(TAG, "Error obtaining movies", t);
                                    // Toast.makeText(get(), "Error obtaining movies", Toast.LENGTH_SHORT).show();

                                }
                        )
        );

    }


}
