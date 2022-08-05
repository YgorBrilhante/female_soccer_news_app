package data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import domain.News;

public class database {
    @Database(entities = {News.class}, version = 1)
    public abstract static class AppDatabase extends RoomDatabase {
        public abstract NewsDao NewsDao();
    }
}
