package com.android.showofhands.view.ui;


import android.os.Bundle;
import android.widget.Toast;

import com.android.showofhands.R;
import com.android.showofhands.ShowOfHandsApp;
import com.android.showofhands.model.Movie;
import com.android.showofhands.view.viewmodel.MovieViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((ShowOfHandsApp)getApplicationContext()).appComponent().inject(this);
         MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
         movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
             @Override
             public void onChanged(List<Movie> movies) {
                 Toast.makeText(getApplicationContext(),"change",Toast.LENGTH_SHORT).show();
             }
         });
    }
}
