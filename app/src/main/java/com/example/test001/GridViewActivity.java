package com.example.test001;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    private GridView gridView;
    private List<String> list;
    private List<String> list2;
    private LayoutInflater inflater;
    GridViewAdapter gridViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);
        list2 = new ArrayList<>();
        list = new ArrayList<>();
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        list.add("aaa");
        gridViewAdapter = new GridViewAdapter();
        gridView = findViewById(R.id.GridView);
        gridView.setAdapter(gridViewAdapter);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        findViewById(R.id.Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add("aaa");
                list.add("aaa");
                gridViewAdapter.notifyDataSetChanged();
            }
        });
        list2.addAll(list);
        findViewById(R.id.Button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                list2.addAll(list);
                list = list2;
                gridViewAdapter.notifyDataSetChanged();
            }
        });

    }

    private class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {

            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = inflater.inflate(R.layout.grid_view_item, null);
            return v;
        }
    }
}
