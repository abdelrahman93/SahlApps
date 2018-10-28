package com.example.asherif.sahlapp.Region.Main;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.asherif.sahlapp.R;
import com.example.asherif.sahlapp.Region.CreateAdvertisment.Create_Advertisment_Activity;
import com.example.asherif.sahlapp.Region.Main.NewestADS.Fragment_NewestADS;
import com.example.asherif.sahlapp.Region.Network.Model.File;
import com.example.asherif.sahlapp.Region.Network.Model.FileContent;
import com.example.asherif.sahlapp.Region.Network.Model.User;
import com.example.asherif.sahlapp.Region.base.BaseActivity;
import com.example.asherif.sahlapp.Region.profile.ProfileActivity;

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
    @BindString(R.string.myads) String myads;
    @BindString(R.string.favorites) String favorite;
    @BindString(R.string.profile) String profile;
    @BindString(R.string.newestads) String newestads;
    private ColorStateList colors;



    @NonNull
    @Override
    protected MainActivityPresenter createPresenter(@NonNull Context context) {
        return new MainActivityPresenter (this, new File(), new FileContent(), new User());

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
       // NavigateToProfile();
        NavigateToMyADS();
        NavigateToFavorites();
        NavigateToNewestADS();
        viewPager.setAdapter(adapter);

        //customize Tabs Tittles ,texts and icons
        TabTittle();
        ChangeTabSelectedColor();




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
            case R.id.profile:
                NavigteToProfile();

        }
        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    private void init() {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        t = new ActionBarDrawerToggle(this, dl,R.string.navigation_drawer_open ,R.string.closeDrawer);

        /*Menu menu = nv.getMenu();
        MenuItem tools= menu.findItem(R.id.menu_top);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
        tools.setTitle(s);*/
      //  nv.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) MainActivity.this);

    }

    //Display The Tab Tittle and Icons
    @Override
    public void TabTittle() {
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#fffff8"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        tabLayout.getTabAt(1).setIcon(R.drawable.favorites);
        tabLayout.getTabAt(1).setIcon(R.drawable.favorites);
        tabLayout.getTabAt(0).setIcon(R.drawable.ads);
        tabLayout.getTabAt(2).setIcon(R.drawable.trending1);
        getSupportActionBar().setTitle(myads);


        //change toolbar tittle depending on the fragment type
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {

             /*       case 0:
                        viewPager.setCurrentItem(0);
                        getSupportActionBar().setTitle(profile);
                        break;*/

                    case 0:
                        viewPager.setCurrentItem(0);
                        getSupportActionBar().setTitle(myads);
                        break;

                    case 1:
                        viewPager.setCurrentItem(1);
                        getSupportActionBar().setTitle(favorite);
                        break;

                    case 2:
                        viewPager.setCurrentItem(2);
                        getSupportActionBar().setTitle(newestads);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
           //     tab.getIcon().setColorFilter(Color.parseColor("#727272"), PorterDuff.Mode.SRC_ATOP);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });



      /*  for (int i = 0; i < tabLayout.getTabCount(); i++) {

        }*/

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

    @Override
    public void NavigteToProfile() {
        Intent profile=new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(profile);
        finish();
    }

    @Override
    public void ChangeTabSelectedColor() {

        //switch text colors from normal color to selected color
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#fffff8"));

        //switch icon colors from normal color to selected color
        if (Build.VERSION.SDK_INT >= 23) {
            colors = getResources().getColorStateList(R.color.tab_icon, getTheme());
            Log.i("TAG", "Build.VERSION.SDK_INT >= 23: ");

        }
        else {
            Log.i("TAG", "ChangeTabSelectedColor: ");
            colors = getResources().getColorStateList(R.color.tab_icon);
        }

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            Log.i("TAG", "tabLayout.getTabCount: ");

            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable icon = tab.getIcon();

            if (icon != null) {
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTintList(icon, colors);
            }
        }

    }

}
