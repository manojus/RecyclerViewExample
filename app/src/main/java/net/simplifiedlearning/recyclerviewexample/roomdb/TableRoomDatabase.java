package net.simplifiedlearning.recyclerviewexample.roomdb;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {TableModel.class}, version = 2)
public abstract class TableRoomDatabase extends RoomDatabase {

    public abstract TableDao tableDao();

    private static TableRoomDatabase INSTANCE;

    static TableRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TableRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TableRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TableDao mDao;

        PopulateDbAsync(TableRoomDatabase db) {
            mDao = db.tableDao();
          //  mDao = (TableDao) db.tableDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            TableModel word = new TableModel("Hello","hello","hello");
            mDao.insert(word);
         /*   word = new TableModel("World");
            mDao.insert(word)*/;
            return null;
        }
    }

}
