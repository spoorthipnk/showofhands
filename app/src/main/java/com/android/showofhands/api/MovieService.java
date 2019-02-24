package com.android.showofhands.api;

import com.android.showofhands.model.Movie;
import com.android.showofhands.model.MovieResponse;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("/movie/now_playing")
    Observable<MovieResponse> getNowPlayingMovies(@Query("api_key")String apiKey);

}
