package com.android.showofhands.view.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.showofhands.R;
import com.android.showofhands.model.Movie;
import com.android.showofhands.model.Show;
import com.android.showofhands.view.adapter.MovieAdapter;
import com.android.showofhands.view.adapter.ShowsAdapter;
import com.android.showofhands.view.viewmodel.MovieViewModel;
import com.android.showofhands.view.viewmodel.ShowsViewModel;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @BindView(R.id.rvMovies)
    RecyclerView rvMovies;
    @BindView(R.id.rvShows)
    RecyclerView rvShows;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        subscribeMoviesUI();
        return view;
    }

    private void subscribeShowsUI() {
        ShowsViewModel showsViewModel = ViewModelProviders.of(this).get(ShowsViewModel.class);
        ShowsAdapter showsAdapter = new ShowsAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvShows.setLayoutManager(layoutManager);
        rvShows.setAdapter(showsAdapter);
        rvShows.setHasFixedSize(true);

        showsViewModel.getShows().observe(this, new Observer<List<Show>>() {
            @Override
            public void onChanged(List<Show> shows) {
                showsAdapter.updateMovies(shows);
            }
        });
    }

    private void subscribeMoviesUI() {
        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

        MovieAdapter movieAdapter = new MovieAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setAdapter(movieAdapter);
        rvMovies.setHasFixedSize(true);

        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.updateMovies(movies);
                //Toast.makeText(getContext(), "change", Toast.LENGTH_SHORT).show();
            }
        });


        subscribeShowsUI();

    }


}
