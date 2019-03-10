package com.android.showofhands.view.viewmodel;

import com.android.showofhands.model.Show;
import com.android.showofhands.repository.MoviesRepository;
import com.android.showofhands.repository.ShowsRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ShowsViewModel extends ViewModel {

    private ShowsRepository showsRepository;
    private LiveData<List<Show>> shows;

    public ShowsViewModel(){
        showsRepository = new ShowsRepository();
        shows = showsRepository.getShows();
    }

    public LiveData<List<Show>> getShows() {
        return shows;
    }
}
