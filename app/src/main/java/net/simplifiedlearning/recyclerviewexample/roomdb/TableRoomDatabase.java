package net.simplifiedlearning.recyclerviewexample.roomdb;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import net.simplifiedlearning.recyclerviewexample.Product;
import net.simplifiedlearning.recyclerviewexample.R;

@Database(entities = {TableModel.class,Product.class}, version = 4)
public abstract class TableRoomDatabase extends RoomDatabase {

    public abstract TableDao tableDao();
    public abstract ProductDao productDao();

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
            new PopulateDbAsync1(INSTANCE).execute();
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

            TableModel room1 = new TableModel("Hell3o1","hello13","h3ell1o");
            mDao.insert(room1);
         /*   word = new TableModel("World");
            mDao.insert(word)*/;
            return null;
        }
    }
    private static class PopulateDbAsync1 extends AsyncTask<Void, Void, Void> {

        private final ProductDao mDao1;

        PopulateDbAsync1(TableRoomDatabase db) {
            mDao1 = db.productDao();
            //  mDao = (TableDao) db.tableDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
         //   mDao1.deleteAll();

            Product room1 = new Product("Hellod","hello131",120.311 ,1110.31, R.drawable.macbook);
            mDao1.insert(room1);
         /*   word = new TableModel("World");
            mDao.insert(word)*/;
            return null;
        }
    }

}
