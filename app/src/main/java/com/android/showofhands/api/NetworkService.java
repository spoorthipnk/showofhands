package com.android.showofhands.api;

import com.android.showofhands.model.Movie;
import com.android.showofhands.model.MovieResponse;
import com.android.showofhands.model.TrendingShowsResponse;

import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkService {

    @GET("/3/movie/now_playing/")
    Observable<MovieResponse> getNowPlayingMovies(@Query("api_key")String apiKey);

    @GET("/3/trending/tv/week")
    Observable<TrendingShowsResponse> getTrendingShows(@Query("api_key")String apiKey);
}
