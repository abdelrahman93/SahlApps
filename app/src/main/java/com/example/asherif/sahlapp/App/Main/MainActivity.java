package com.example.asherif.sahlapp.App.Main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.asherif.sahlapp.App.CreateAdvertisment.Create_Advertisment_Activity;
import com.example.asherif.sahlapp.App.Main.NewestADS.Fragment_NewestADS;
import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.App.Login.LoginActivity;
import com.example.asherif.sahlapp.App.base.BaseActivity;
import com.example.asherif.sahlapp.App.profile.ProfileActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

//updated version 2nd edition feshar
//updated version 3rd edition Abdelrahman

public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainView {
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
    @BindString(R.string.myads)
    String myads;
    @BindString(R.string.favorites)
    String favorite;
    @BindString(R.string.profile)
    String profile;
    @BindString(R.string.newestads)
    String newestads;
    private ColorStateList colors;
    //Get Shared Preferences visitor flag;
    SharedPreferences prefs;
    String flag;


    @NonNull
    @Override
    protected MainActivityPresenter createPresenter(@NonNull Context context) {
        return new MainActivityPresenter(this, MainActivity.this,tabLayout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //hide soft keyboard at the beginning
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //initialize the components
        init();
        checkVisitor();

        dl.addDrawerListener(t);
        t.syncState();
        if (getSupportActionBar() != null){ getSupportActionBar().setDisplayHomeAsUpEnabled(true);}

        //listeners for items in the navigation menu
        SetNavigationMenu(nv);

     /*   // Add Fragments to adapter one by one
        NavigateToMyADS();
        NavigateToFavorites();
        NavigateToNewestADS();
        viewPager.setAdapter(adapter);*/




    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        //in case of visitor hide create icon
        if (flag != null) {
            if (flag.equals("true")) {
                MenuItem createAd = menu.findItem(R.id.createadd);
                createAd.setVisible(false);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.createadd:
                NavigateToCreateAdvertisment();
                break;
            case R.id.profile:
                NavigteToProfile();

        }
        if (t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        t = new ActionBarDrawerToggle(this, dl, R.string.navigation_drawer_open, R.string.closeDrawer);
        prefs = getSharedPreferences("MyPREFERENCES", MODE_PRIVATE);
        flag = prefs.getString("visitor_key", null);
        // mPresenter.checkVisitor();

    }

    //change  The Tab Tittle and Icons colors
    @Override
    public void ChangeTabColor(TabLayout tabLayout, ViewPager viewPager, String myads, String favorite, String newestads) {

        mPresenter.ChangeTabColor(tabLayout, viewPager, myads, favorite, newestads);


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
        Intent CreateADS = new Intent(MainActivity.this, Create_Advertisment_Activity.class);
        startActivity(CreateADS);
        finish();
    }

    @Override
    public void NavigteToProfile() {
        mPresenter.NavigteToProfile();

        if (flag != null) {
            if (flag.equals("true")) {
                //in case of visitor go to login activity
                Intent Login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Login);
                finish();
            } else {
                Intent profile = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profile);
                finish();
            }
        }
    }

    @Override
    public void checkVisitor() {
        if (flag != null) {
            if (flag.equals("true")) {
                /*getSupportActionBar().setTitle(newestads);
                tabLayout.setVisibility(View.GONE);
                // Add Newest Fragment only to adapter in case of visitor
                NavigateToNewestADS();
                viewPager.setAdapter(adapter);*/
                mPresenter.checkVisitor(tabLayout,viewPager,adapter);
            } else {
                // Add Fragments to adapter one by one
                NavigateToMyADS();
                NavigateToFavorites();
                NavigateToNewestADS();
                viewPager.setAdapter(adapter);
                //to set the icons in the Tabs and the default color
                TabIcons(tabLayout, viewPager, myads);

                //change Tabs Tittles and icons colors
                ChangeTabColor( tabLayout,  viewPager,  myads,  favorite,  newestads);
                //customize Tabs Tittles ,texts and icons
               // TabTittle();
            }
        }
    }

    @Override
    public void SetNavigationMenu(NavigationView nv) {
        mPresenter.NavigationMenu(nv);
    }

    @Override
    public void TabIcons(TabLayout tabLayout, ViewPager viewPager, String myads) {
        mPresenter.TabIcons(tabLayout, viewPager, myads);
    }




}