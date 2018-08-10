package net.simplifiedlearning.recyclerviewexample.roomdb;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

public class TableRepository {

    private TableDao mTableDao;
    private LiveData<List<TableModel>> mAllWords;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    TableRepository(Application application) {
        TableRoomDatabase db = TableRoomDatabase.getDatabase(application);
        mTableDao = db.tableDao();
      //  mTableDao = (TableDao) db.tableDao();
        mAllWords = mTableDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<TableModel>> getAllWords() {
        return mAllWords;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (TableModel table) {
        new insertAsyncTask(mTableDao).execute(table);
    }

    private static class insertAsyncTask extends AsyncTask<TableModel, Void, Void> {

        private TableDao mAsyncTaskDao;

        insertAsyncTask(TableDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TableModel... params) {
            Log.i("displaout","hello");
            long hg= mAsyncTaskDao.insert(params[0]);
            Log.i("displaout",hg+"result");
            return null;
        }
    }
}
