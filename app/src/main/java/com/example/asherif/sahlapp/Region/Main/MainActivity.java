package com.example.asherif.sahlapp.Region.Main;

import android.content.Intent;
import android.graphics.Color;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.CreateAdvertisment.Create_Advertisment_Activity;
import com.example.asherif.sahlapp.Region.Main.profile.fragment_profile;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

//updated version 2nd edition feshar
//updated version 3rd edition Abdelrahman

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView((R.id.pager))
    ViewPager viewPager;
    @BindView(R.id.tabs)
     TabLayout tabLayout;
    @BindView(R.id.activity_main)
     DrawerLayout dl;
    private ActionBarDrawerToggle t;
    @BindView(R.id.nv)
     NavigationView nv;
    private ViewPagerAdapter adapter;
    @BindString(R.string.myads) String myads;
    @BindString(R.string.favorites) String favorite;
    @BindString(R.string.profile) String profile;
    @BindString(R.string.newestads) String newestads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //hide soft keyboard at the beginning
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //initialize the components
        init();

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.cars:
                        Toast.makeText(MainActivity.this, "cars",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.buildings:
                        Toast.makeText(MainActivity.this, "buildings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hospitals:
                        Toast.makeText(MainActivity.this, "Hospitals",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });


        // Add Fragments to adapter one by one
        NavigateToProfile();
        NavigateToMyADS();
        NavigateToFavorites();
        NavigateToNewestADS();
        viewPager.setAdapter(adapter);

        //customize Tabs Tittles ,texts and icons
        TabTittle();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.createadd:
                NavigateToCreateAdvertisment();
                break;
        }
        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    private void init() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        t = new ActionBarDrawerToggle(this, dl,R.string.navigation_drawer_open ,R.string.closeDrawer);


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
                        getSupportActionBar().setTitle(profile);
                        break;

                    case 1:
                        viewPager.setCurrentItem(1);
                        getSupportActionBar().setTitle(myads);
                        break;

                    case 2:
                        viewPager.setCurrentItem(2);
                        getSupportActionBar().setTitle(favorite);
                        break;

                    case 3:
                        viewPager.setCurrentItem(3);
                        getSupportActionBar().setTitle(newestads);
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
        adapter.addFragment(new fragment_profile(), profile);

    }

    @Override
    public void NavigateToMyADS() {
        adapter.addFragment(new fragment_myADS(), myads);

    }

    @Override
    public void NavigateToFavorites() {
        adapter.addFragment(new fragment_favorites(), favorite);

    }

    @Override
    public void NavigateToNewestADS() {
        adapter.addFragment(new Fragment_NewestADS(), newestads);

    }

    @Override
    public void NavigateToCreateAdvertisment() {
        Intent CreateADS=new Intent(MainActivity.this,Create_Advertisment_Activity.class);
        startActivity(CreateADS);
        finish();
    }

}
