package com.example.lenovo.itrextesttask.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.itrextesttask.R;

/**
 * Created by lenovo on 14.03.2018.
 */

public class FilmViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvFilmName;
    private TextView mTvFilmNameEng;
    private ImageView mIvFilmImage;
    private LinearLayout mLlContainer;



    public FilmViewHolder(View v) {
        super(v);
        mTvFilmName = (TextView) v.findViewById(R.id.tv_name_film);
        mTvFilmNameEng = (TextView) v.findViewById(R.id.tv_eng_name_film);
        mIvFilmImage = (ImageView) v.findViewById(R.id.iv_image_film);
        mLlContainer = (LinearLayout) v.findViewById(R.id.ll_container);

    }

    public TextView getTvFilmName() {
        return mTvFilmName;
    }

    public void setTvFilmName(TextView mTvFilmName) {
        this.mTvFilmName = mTvFilmName;
    }

    public TextView getTvFilmNameEng() {
        return mTvFilmNameEng;
    }

    public void setTvFilmNameEng(TextView mTvFilmNameEng) {
        this.mTvFilmNameEng = mTvFilmNameEng;
    }

    public ImageView getIvFilmImage() {
        return mIvFilmImage;
    }

    public void setIvFilmImage(ImageView mIvFilmImage) {
        this.mIvFilmImage = mIvFilmImage;
    }

    public LinearLayout getLlContainer() {
        return mLlContainer;
    }

    public void setLlContainer(LinearLayout mLlContainer) {
        this.mLlContainer = mLlContainer;
    }
}
