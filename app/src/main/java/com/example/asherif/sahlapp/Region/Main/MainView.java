package com.example.asherif.sahlapp.Region.Main;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public interface MainView {

    void NavigateToMyADS();
    void NavigateToFavorites();
    void NavigateToNewestADS();
    void NavigateToCreateAdvertisment();
    void NavigteToProfile();
    void  ChangeTabColor(TabLayout tabLayout, final ViewPager viewPager, final String myads,final String favorite, final String newestads);
    void checkVisitor();
    void  SetNavigationMenu(NavigationView nv);
    void TabIcons(TabLayout tabLayout,ViewPager viewPager,String myads);
}
