package com.brilhante.appfutebolfeminino.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import domain.News;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Remover Mock de noticias
        List<News> news = new ArrayList<>();
        news.add(new News("Ferroviária tem desfalque importante", "Um texto qualquer"));
        news.add(new News("Ferrinha joga no sábado", "Um texto qualquer"));
        news.add(new News("Copa do mundo feminina está terminando", "Um texto qualquer"));

        this.news.setValue(news);
    }

    @SuppressWarnings("SameReturnValue")
    public static LiveData<List<News>> getText() {
        return null;
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}