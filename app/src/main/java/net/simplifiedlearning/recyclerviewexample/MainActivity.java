package net.simplifiedlearning.recyclerviewexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.simplifiedlearning.recyclerviewexample.roomdb.TableModel;
import net.simplifiedlearning.recyclerviewexample.roomdb.TableViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private TableViewModel mTableViewModel;
   // private TableViewModel mTableViewModel1;


    //a list to store all the products
    List<Product> productList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



//add me
      RecyclerView recyclerView1 = findViewById(R.id.recyclerview);
        final TableListAdapter adapter1 = new TableListAdapter(this);
        recyclerView1.setAdapter(adapter1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTableViewModel = ViewModelProviders.of(this).get(TableViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        //creating recyclerview adapter

        mTableViewModel.getAllWords().observe(this, new Observer<List<TableModel>>() {
            @Override
            public void onChanged(@Nullable final List<TableModel> words) {
                // Update the cached copy of the words in the adapter.
                adapter1.setWords(words);
            }
        });


//add float button
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });

//end
        //initializing the productlist
        productList = new ArrayList<>();


          Product add=(
                new Product(
                        45,
                        "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                        "13.3 inch, Silver, 1.35 kg",
                        44.3,
                        60000,
                        R.drawable.macbook));
        productList.add(add);
        mTableViewModel.insert1(add);
      /*  Product e = new Product(
                22,
                "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                "14 inch, Gray, 1.659 kg",
                5.3,
                60000,
                R.drawable.dellinspiron);
        productList.add(e);
        mTableViewModel.insert1(e);*/


  /*      productList.add(
                new Product(
                        1,
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        4.3,
                        60000,
                        R.drawable.surface));*/

        final ProductAdapter adapter = new ProductAdapter(this, productList);

        mTableViewModel
                .getAllWords1()
                .observe(this, new Observer<List<Product>>() {
                    @Override
                    public void onChanged(@Nullable final List<Product> words) {
                        // Update the cached copy of the words in the adapter.
                        Log.d("hfdr", words.size()+"");
                        adapter.setWords1(words);
                    }
                });
        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

    }



    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
           TableModel word = new TableModel(data.getStringExtra(NewWordActivity.EXTRA_REPLY),data.getStringExtra(NewWordActivity.EXTRA_REPLY1),data.getStringExtra(NewWordActivity.EXTRA_REPLY2));
          //  TableModel word1 = new TableModel(data.getStringExtra(NewWordActivity.EXTRA_REPLY1));
           // TableModel word2 = new TableModel(data.getStringExtra(NewWordActivity.EXTRA_REPLY2));

            mTableViewModel.insert(word);
       //     mTableViewModel.insert(word1);
        //    mTableViewModel.insert(word2);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }




}
