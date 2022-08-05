package data;

import java.util.List;

import domain.News;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsApi {
    @GET("news.json")
    Call<List<News>> getNews();


}
