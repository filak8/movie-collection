package com.example.lenovo.itrextesttask.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.itrextesttask.R;
import com.squareup.picasso.Picasso;

public class Main2Activity extends AppCompatActivity {

    public static final String ARGS_KEY_FILM_NAME = "ARGS_KEY_FILM_NAME";
    public static final String ARGS_KEY_FILM_NAME_ENG = "ARGS_KEY_FILM_NAME_ENG";
    public static final String ARGS_KEY_FILM_IMAGE = "ARGS_KEY_FILM_IMAGE";
    public static final String ARGS_KEY_FILM_PREMIERE = "ARGS_KEY_FILM_PREMIERE";
    public static final String ARGS_KEY_FILM_DESCRIPTION = "ARGS_KEY_FILM_DESCRIPTION";

    private ImageView mIvImageFilm;
    private TextView mTvNameFilm;
    private TextView mTvNameFilmEng;
    private TextView mTvPremiere;
    private TextView mTvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mIvImageFilm = (ImageView) findViewById(R.id.iv_image_film);
        mTvNameFilm = (TextView) findViewById(R.id.tv_name_film);
        mTvNameFilmEng = (TextView) findViewById(R.id.tv_eng_name_film);
        mTvPremiere = (TextView) findViewById(R.id.tv_premiere);
        mTvDescription = (TextView) findViewById(R.id.tv_description);

        Intent intent = getIntent();

        Picasso.get().load(Uri.parse(intent.getStringExtra(ARGS_KEY_FILM_IMAGE))).into(mIvImageFilm);
        mTvNameFilm.setText(intent.getStringExtra(ARGS_KEY_FILM_NAME));
        mTvNameFilmEng.setText(intent.getStringExtra(ARGS_KEY_FILM_NAME_ENG));
        mTvPremiere.setText(intent.getStringExtra(ARGS_KEY_FILM_PREMIERE));
        mTvDescription.setText(intent.getStringExtra(ARGS_KEY_FILM_DESCRIPTION));
    }
}
