package net.simplifiedlearning.recyclerviewexample.roomdb;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import net.simplifiedlearning.recyclerviewexample.Product;

import java.util.List;
@Dao
public interface ProductDao {

    @Query("SELECT * from `room_table` ORDER BY words ASC")
    LiveData<List<Product>> getAlphabetizedWords();

    @Insert
    long insert(Product room1);

    @Query("DELETE FROM `room_table`")
    void deleteAll();

}
