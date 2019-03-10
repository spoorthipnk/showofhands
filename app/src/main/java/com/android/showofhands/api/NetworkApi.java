package com.android.showofhands.api;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkApi {

    private static NetworkService instance = null;
    private static final String MOVIE_DATABASE_BASE_URL = "https://api.themoviedb.org/";
    //private  NetworkService movieService;

    public static NetworkService getInstance(){
        if(instance == null){

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.addInterceptor(logging);

            Retrofit retrofit = new Retrofit.Builder()

                    .baseUrl(MOVIE_DATABASE_BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())

                    .build();

            instance = retrofit.create(NetworkService.class);

        }
        return instance;
    }



}
