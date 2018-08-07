package com.example.lenovo.itrextesttask.rest;

import com.example.lenovo.itrextesttask.models.FilmResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmsApi {

    @GET("57cffac8260000181e650041")
    Call<FilmResponse> getFilms();
}
