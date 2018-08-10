package net.simplifiedlearning.recyclerviewexample;

/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.simplifiedlearning.recyclerviewexample.roomdb.TableModel;

import java.util.List;


public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.WordViewHolder> {

    class WordViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;
        private final TextView wordItemView1;
        private final TextView wordItemView2;
        private final TextView wordItemView3;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            wordItemView1 = itemView.findViewById(R.id.text1);

            wordItemView2 = itemView.findViewById(R.id.text2);

            wordItemView3 = itemView.findViewById(R.id.text3);


        }
    }

    private final LayoutInflater mInflater;
    private List<TableModel> mWords; // Cached copy of words

    TableListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        TableModel current = mWords.get(position);
        holder.wordItemView.setText(current.getTablemodel()+"  "+current.getTablemodel1()+" "+current.getTablemodel2()+"\n");
        holder.wordItemView1.setText(current.getTablemodel());
        holder.wordItemView2.setText(current.getTablemodel1());
        holder.wordItemView3.setText(current.getTablemodel2());
    }

    void setWords(List<TableModel> words){
        mWords = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWords != null)
            return mWords.size();
        else return 0;
    }
}


