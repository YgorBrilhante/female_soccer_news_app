package data.remote;

import androidx.room.Room;

import com.brilhante.appfutebolfeminino.App;

import data.SoccerNewsApi;
import data.local.AppDatabase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    private static final String REMOTE_API_URL = "https://ygorbrilhante.github.io/female_soccer_news_API/";
    private static final String LOCAL_DB_NAME = "Soccer News - Female";

    private SoccerNewsApi remoteApi;
    private AppDatabase localDb;

    public SoccerNewsApi getRemoteApi() {return remoteApi;}

    public AppDatabase getLocalDb() {return localDb;}

    private Repository () {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SoccerNewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), AppDatabase.class, LOCAL_DB_NAME)
                .build();
    }

    public static Repository getInstance() {return LazyHolder.INSTANCE;}

    private static class LazyHolder {
        private static final Repository INSTANCE = new Repository();
    }
}
