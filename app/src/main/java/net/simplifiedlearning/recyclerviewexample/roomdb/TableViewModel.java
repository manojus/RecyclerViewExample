package net.simplifiedlearning.recyclerviewexample.roomdb;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TableViewModel extends AndroidViewModel {

    private TableRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<TableModel>> mAllWords;

    public TableViewModel (Application application) {
        super(application);
        mRepository = new TableRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    public LiveData<List<TableModel>> getAllWords() { return mAllWords; }

    public void insert(TableModel word) { mRepository.insert(word); }

}