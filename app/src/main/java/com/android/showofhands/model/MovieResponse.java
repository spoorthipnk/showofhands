package com.android.showofhands.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class MovieResponse {
  @SerializedName("results")
  @Expose
  private List<Movie> movies = null;

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}