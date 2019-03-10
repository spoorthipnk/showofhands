package com.android.showofhands.repository;

import android.util.Log;

import com.android.showofhands.api.NetworkApi;
import com.android.showofhands.model.Show;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import static com.android.showofhands.BuildConfig.MOVIE_DATABASE_API_KEY;

public class ShowsRepository {

    private MutableLiveData<List<Show>> shows;
    private CompositeDisposable disposable = new CompositeDisposable();
    private static final String TAG = "ShowsRepository";

    public ShowsRepository(){
        if(shows == null){
            shows = new MutableLiveData<List<Show>>();
            loadTrendingShows();
        }
    }

    public MutableLiveData<List<Show>> getShows() {
        return shows;
    }

    private void loadTrendingShows(){

        disposable.add(
                NetworkApi.getInstance().getTrendingShows(MOVIE_DATABASE_API_KEY)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                trendingShowsResponse -> {shows.setValue(trendingShowsResponse.getShows());}
                                , t -> {
                                    Log.e(TAG, "loadTrendingShows: ",t );
                                }

                        ));
    }
}
