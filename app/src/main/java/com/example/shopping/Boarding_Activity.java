package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

public class Boarding_Activity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding);
        fragmentManager=getSupportFragmentManager();
        final PaperOnboardingFragment paperOnboardingFragment=PaperOnboardingFragment.newInstance(getOnBoardingFragment());
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container,paperOnboardingFragment);
        fragmentTransaction.commit();




    }

    private ArrayList<PaperOnboardingPage> getOnBoardingFragment() {
        PaperOnboardingPage paperOnboardingPage=new PaperOnboardingPage("Tree","Beautiful Tree",
                Color.parseColor("#00FF00"),R.drawable.tree,R.drawable.no_image);
        PaperOnboardingPage paperOnboardingPage1=new PaperOnboardingPage("Sky","Beautiful Sky",
                Color.parseColor("#0066CC"),R.drawable.sky,R.drawable.no_image);
        PaperOnboardingPage paperOnboardingPage2=new PaperOnboardingPage("Animal",
                "Beautiful Animal",
                Color.parseColor("#FFFF33"),
                R.drawable.animal,R.drawable.no_image);

        ArrayList<PaperOnboardingPage> paperOnboarding=new ArrayList<>();
        paperOnboarding.add(paperOnboardingPage);
        paperOnboarding.add(paperOnboardingPage1);
        paperOnboarding.add(paperOnboardingPage2);
        return  paperOnboarding;



    }
}