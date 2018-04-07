package srp.com.fallrooz.Db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import srp.com.fallrooz.Db.dao.Type1Dao;
import srp.com.fallrooz.Db.tables.Type1Db;
import srp.com.fallrooz.Db.tables.Type2Db;
import srp.com.fallrooz.Db.tables.Type3Db;
import srp.com.fallrooz.Db.tables.UinfoDb;

/**
 * Created by s.rahmanipour on 4/3/2018.
 */

@Database(entities = {Type1Db.class,Type2Db.class,Type3Db.class,UinfoDb.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract Type1Dao type1Dao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "type1-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}