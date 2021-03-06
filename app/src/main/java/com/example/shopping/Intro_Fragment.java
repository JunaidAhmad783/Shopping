package com.example.shopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Intro_Fragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView textView;
    ImageView image;
    private String mParam1;
    private String mParam2;

    public Intro_Fragment() {
        // Required empty public constructor
    }


    public static Intro_Fragment newInstance(String param1, String param2) {
        Intro_Fragment fragment = new Intro_Fragment();
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
        View view= inflater.inflate(R.layout.fragment_intro_, container, false);
        textView=view.findViewById(R.id.text_Frag);
        image=view.findViewById(R.id.img_tab);
        //ImageView imageView=getArguments().getString("img",);
      //  image.setImageURI(imageView.getU);

        String title=getArguments().getString("title");
        textView.setText(title);

        return  view;
    }
}