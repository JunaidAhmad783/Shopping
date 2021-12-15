package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Intro_design extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_design);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Tab 1"+R.drawable.tree);
        arrayList.add("Tab 2"+R.drawable.sky);
        arrayList.add("Tab 3");
        Prepareviewpager(viewPager,arrayList);
       // tabLayout.setupWithViewPager(viewPager);
     // tabLayout.getTabAt(0).setText("Next");

    }

    private void Prepareviewpager(ViewPager viewPager, ArrayList<String> arrayList) {

        MainAdapter main=new MainAdapter(getSupportFragmentManager());

        Intro_Fragment intro_fragment=new Intro_Fragment();
        for(int i=0;i<arrayList.size();i++)
        {
            Bundle bundle=new Bundle();
            bundle.putString("title",arrayList.get(i));
            intro_fragment.setArguments(bundle);
            main.addFragment(intro_fragment,arrayList.get(i));
            intro_fragment=new Intro_Fragment();

        }
        viewPager.setAdapter(main);

    }

    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<String> arrayList=new ArrayList<>();
        List<Fragment>list=new ArrayList<>();

        public void addFragment(Fragment fragment,String title)
        {
            arrayList.add(title);
            list.add(fragment);
        }
        public MainAdapter(@NonNull FragmentManager fm) {

            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return arrayList.get(position);
        }
    }
}