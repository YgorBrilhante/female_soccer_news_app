package com.brilhante.appfutebolfeminino.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import data.remote.Repository;
import domain.News;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel(){

    }

    public LiveData<List<News>> loadFavoriteNews() {

        return Repository.getInstance().getLocalDb().NewsDao().loadFavoriteNews();
    }

    public void saveNews(News news){
        AsyncTask.execute (() -> Repository.getInstance().getLocalDb().NewsDao().save(news));
    }
}