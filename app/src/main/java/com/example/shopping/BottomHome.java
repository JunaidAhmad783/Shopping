package com.example.shopping;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteBlobTooBigException;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class BottomHome extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    ArrayList<Data> list;
    EditText editText;
    adapter adapter;

    public BottomHome() {
        // Required empty public constructor
    }

    public static BottomHome newInstance(String param1, String param2) {
        BottomHome fragment = new BottomHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_bottom_home, container, false);
         floatingActionButton=view.findViewById(R.id.floating);
         editText=view.findViewById(R.id.Search_item);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


         floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getActivity(),AddItemActivity.class));
             }
         });
        recyclerView=view.findViewById(R.id.recy);
        list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        Cursor cursor=new dbmanager(getActivity()).read_data();
       try{ while(cursor.moveToNext())
        {
            Data data =new Data(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getBlob(4));
            list.add(data);
        }}
       catch (SQLiteBlobTooBigException expected ){}
         adapter=new adapter(list,getActivity());
        recyclerView.setAdapter(adapter);
   return  view;
    }
    private void filter(String toString) {
        ArrayList<Data> Filteredlist = new ArrayList<>();
        for (Data item : list) {
            if (item.getName().toLowerCase().contains(toString.toLowerCase())) {
                Filteredlist.add(item);
            }
        }

        adapter.filterlist(Filteredlist);
    }
}