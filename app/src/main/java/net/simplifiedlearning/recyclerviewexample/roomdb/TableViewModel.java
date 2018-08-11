package net.simplifiedlearning.recyclerviewexample.roomdb;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import net.simplifiedlearning.recyclerviewexample.Product;

import java.util.List;

public class TableViewModel extends AndroidViewModel {

    private TableRepository mRepository;
    private ProductRepository mRepository1;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<TableModel>> mAllWords;
    //add
    private LiveData<List<Product>> mAllWords1;

    public TableViewModel (Application application) {
        super(application);
        mRepository = new TableRepository(application);
        mRepository1 = new ProductRepository(application);
        mAllWords = mRepository.getAllWords();
        mAllWords1 = mRepository1.getAllWords1();
    }

    public LiveData<List<TableModel>> getAllWords() { return mAllWords; }

    public void insert(TableModel word) { mRepository.insert(word); }



    public LiveData<List<Product>> getAllWords1() { return mAllWords1; }

    public void insert1(Product room1) { mRepository1.insert1(room1); }

}