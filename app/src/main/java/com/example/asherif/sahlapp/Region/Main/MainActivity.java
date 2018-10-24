package com.example.asherif.sahlapp.Region.Main;

import android.graphics.Color;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toolbar;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.Main.profile.fragment_profile;

import butterknife.BindView;
import butterknife.ButterKnife;

//updated version 2nd edition feshar
//updated version 3rd edition Abdelrahman

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView((R.id.pager))
    ViewPager viewPager;
    @BindView(R.id.tabs)
     TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //hide soft keyboard at the beginning
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //initialize the components
        init();

        // Add Fragments to adapter one by one
        NavigateToProfile();
        NavigateToMyADS();
        NavigateToFavorites();
        NavigateToNewestADS();
        viewPager.setAdapter(adapter);

        //customize Tabs Tittles ,texts and icons
        TabTittle();


    }

    private void init() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

    }
    //tab tittle`

    private void TabTittle() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        tabLayout.getTabAt(0).setIcon(R.drawable.person);
        tabLayout.getTabAt(2).setIcon(R.drawable.favorites);
        tabLayout.getTabAt(1).setIcon(R.drawable.ads);
        tabLayout.getTabAt(3).setIcon(R.drawable.trending1);

        //change toolbar tittle depending on the fragment type
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {

                    case 0:
                        viewPager.setCurrentItem(0);
                        getSupportActionBar().setTitle("Profile");
                        break;

                    case 1:
                        viewPager.setCurrentItem(1);
                        getSupportActionBar().setTitle("My ADS");
                        break;

                    case 2:
                        viewPager.setCurrentItem(2);
                        getSupportActionBar().setTitle("Favorites");
                        break;

                    case 3:
                        viewPager.setCurrentItem(3);
                        getSupportActionBar().setTitle("Newest ADS");
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


      /*  for (int i = 0; i < tabLayout.getTabCount(); i++) {

        }*/

    }

    @Override
    public void NavigateToProfile() {
        adapter.addFragment(new fragment_profile(), "PROFILE");

    }

    @Override
    public void NavigateToMyADS() {
        adapter.addFragment(new fragment_myADS(), "MY ADS");

    }

    @Override
    public void NavigateToFavorites() {
        adapter.addFragment(new fragment_favorites(), "FAVORITES");

    }

    @Override
    public void NavigateToNewestADS() {
        adapter.addFragment(new Fragment_NewestADS(), "NEWEST ADS");

    }

}
