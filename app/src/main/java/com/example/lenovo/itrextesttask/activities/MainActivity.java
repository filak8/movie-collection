package com.example.lenovo.itrextesttask.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lenovo.itrextesttask.OnFilmClickListener;
import com.example.lenovo.itrextesttask.R;
import com.example.lenovo.itrextesttask.adapters.RecyclerAdapter;
import com.example.lenovo.itrextesttask.dataBase.DBHelper;
import com.example.lenovo.itrextesttask.models.Film;
import com.example.lenovo.itrextesttask.models.FilmResponse;
import com.example.lenovo.itrextesttask.rest.FilmsApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnFilmClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Film> myDataset;
    private DBHelper dbHelper;

    public static final String ARGS_KEY_BASE_URL = "http://www.mocky.io/v2/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dbHelper = new DBHelper(this);

        getRetroList();
    }

    public void getRetroList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ARGS_KEY_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FilmsApi filmsApi = retrofit.create(FilmsApi.class);

        filmsApi.getFilms().enqueue(new Callback<FilmResponse>()

        {
            @Override
            public void onResponse(Call<FilmResponse> call, Response<FilmResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    myDataset = response.body().getList();
                    dbHelper.fillOrUpdateDataBase(myDataset);

                    mAdapter = new RecyclerAdapter(myDataset, MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);

                } else {
                    mAdapter = new RecyclerAdapter(dbHelper.getFilmsFromDataBase(), MainActivity.this);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<FilmResponse> call, Throwable t) {
                mAdapter = new RecyclerAdapter(dbHelper.getFilmsFromDataBase(), MainActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }
        });
    }

    @Override
    public void onFilmClick(Film film) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(Main2Activity.ARGS_KEY_FILM_IMAGE, film.getImage());
        intent.putExtra(Main2Activity.ARGS_KEY_FILM_NAME, film.getName());
        intent.putExtra(Main2Activity.ARGS_KEY_FILM_NAME_ENG, film.getName_eng());
        intent.putExtra(Main2Activity.ARGS_KEY_FILM_PREMIERE, film.getPremiere());
        intent.putExtra(Main2Activity.ARGS_KEY_FILM_DESCRIPTION, film.getDescription());
        startActivity(intent);
    }
}
