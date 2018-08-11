package net.simplifiedlearning.recyclerviewexample.roomdb;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import net.simplifiedlearning.recyclerviewexample.Product;

import java.util.List;


public class ProductRepository {

    private ProductDao mpoductDao;
    private LiveData<List<Product>> mAllWords1;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ProductRepository(Application application) {
        TableRoomDatabase db = TableRoomDatabase.getDatabase(application);
        mpoductDao = db.productDao();



        mAllWords1 = mpoductDao.getAlphabetizedWords();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Product>> getAllWords1() {
        return mAllWords1;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.



    public void insert1 (Product table) { new insertAsyncTask(mpoductDao).execute(table); }

    private static class insertAsyncTask extends AsyncTask<Product, Void, Void> {

        private ProductDao mAsyncTaskDao;

        insertAsyncTask(ProductDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Product... params) {
            Log.i("plaout","hello");
           long hg= mAsyncTaskDao.insert(params[0]);
            Log.i("plaout",hg+"result");
            return null;
        }
    }
}