package com.example.asherif.sahlapp.App.Main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.asherif.sahlapp.App.Login.LoginActivity;
import com.example.asherif.sahlapp.App.Main.NewestADS.Fragment_NewestADS;
import com.example.asherif.sahlapp.App.Network.Model.File;
import com.example.asherif.sahlapp.App.Network.Model.FileContent;
import com.example.asherif.sahlapp.App.Network.Model.User;
import com.example.asherif.sahlapp.App.base.BasePresenter;
import com.example.asherif.sahlapp.App.profile.ProfileActivity;
import com.example.asherif.sahlapp.R;


public class MainActivityPresenter extends BasePresenter {
    private File file;
    private FileContent fileContent;
    private User user;
    MainView view;

   MainActivity context;
    TabLayout tabLayout;
    public MainActivityPresenter(){

    }
    public MainActivityPresenter(MainView view) {
        this.view = view;

    }


    public MainActivityPresenter(MainView view,MainActivity context,TabLayout tabLayout) {
        this.view = view;
this.tabLayout=tabLayout;
        this.context=context;
    }

    void MainActivity() {

    }

   /* public void checkVisitor() {
        view.checkVisitor();
    }*/
    public void NavigationMenu(NavigationView nv){
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.cars:

                        Toast.makeText((Context) view,"cars",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.buildings:
                        Toast.makeText(context, "buildings",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.hospitals:
                        Toast.makeText(context, "Hospitals",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }
    public void TabIcons(TabLayout tabLayout,ViewPager viewPager,String myads){
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#B00020"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * context.getResources().getDisplayMetrics().density));
        if(tabLayout!=null){
        tabLayout.getTabAt(0).setIcon(R.drawable.ads);
        tabLayout.getTabAt(1).setIcon(R.drawable.favorites);
        tabLayout.getTabAt(2).setIcon(R.drawable.trending1);

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#fffff8"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#727272"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#727272"), PorterDuff.Mode.SRC_IN);

        context.getSupportActionBar().setTitle(myads);}


    }
    public void ChangeTabColor(TabLayout tabLayout, final ViewPager viewPager, final String myads,final String favorite, final String newestads){
        //switch text colors from normal color to selected color
        tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#fffff8"));
        //change toolbar tittle depending on the fragment type
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#fffff8"), PorterDuff.Mode.SRC_ATOP);
                switch (tab.getPosition()) {

             /*       case 0:
                        viewPager.setCurrentItem(0);
                        getSupportActionBar().setTitle(profile);
                        break;*/

                    case 0:
                        viewPager.setCurrentItem(0);
                        context.getSupportActionBar().setTitle(myads);
                        break;

                    case 1:
                        viewPager.setCurrentItem(1);
                        context.getSupportActionBar().setTitle(favorite);
                        break;

                    case 2:
                        viewPager.setCurrentItem(2);
                        context.getSupportActionBar().setTitle(newestads);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#727272"), PorterDuff.Mode.SRC_ATOP);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }
   public void NavigteToProfile( ){
        MainActivityModel mainActivityModel=new MainActivityModel(context);
        if (mainActivityModel.flag != null) {
            if (mainActivityModel.flag.equals("true")) {
                //in case of visitor go to login activity
                Intent Login = new Intent(context, LoginActivity.class);
                context.startActivity(Login);
                context.finish();
            }
            else {
                Intent profile = new Intent(context, ProfileActivity.class);
                context.startActivity(profile);
                context.finish();
            }
        }

    }
   public void checkVisitor(TabLayout tabLayout,ViewPager viewPager,ViewPagerAdapter adapter ){
        MainActivityModel mainActivityModel=new MainActivityModel(context);
       Toast.makeText(context, mainActivityModel.flag , Toast.LENGTH_SHORT).show();

               // context.getSupportActionBar().setTitle(newestads);

                tabLayout.setVisibility(View.GONE);
                // Add Newest Fragment only to adapter in case of visitor
                adapter.addFragment(new Fragment_NewestADS(), "New ADS");
                viewPager.setAdapter(adapter);


    }
    public  void hideoptionmenu(Menu menu){
        MainActivityModel mainActivityModel=new MainActivityModel(context);
        if (mainActivityModel.flag != null) {
            if (mainActivityModel.flag.equals("true")) {
                MenuItem createAd = menu.findItem(R.id.createadd);
                createAd.setVisible(false);
            }}
    }
    public void checkflag(){

    }

}
