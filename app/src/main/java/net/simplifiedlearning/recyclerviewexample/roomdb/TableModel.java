package net.simplifiedlearning.recyclerviewexample.roomdb;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "word_table")
public class TableModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mTablemodel;
    @ColumnInfo(name = "word1")
    private String mTablemodel1;
    @ColumnInfo(name = "word2")
    private String mTablemodel2;

    public TableModel(@NonNull String mTablemodel,String mTablemodel1,String mTablemodel2 ) {
        this.mTablemodel = mTablemodel;
        this.mTablemodel1 = mTablemodel1;
        this.mTablemodel2 = mTablemodel2;
    }
// public String getWord(){return this.mTablemodel;}

    @NonNull
    public String getTablemodel() {
        return mTablemodel;
    }
    //second value
    @NonNull
    public String getTablemodel1() {
        return mTablemodel1;
    }
    //third value
    @NonNull
    public String getTablemodel2() {
        return mTablemodel2;
    }




}
