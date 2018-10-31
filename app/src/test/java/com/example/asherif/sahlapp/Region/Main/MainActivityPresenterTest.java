package com.example.asherif.sahlapp.Region.Main;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.asherif.sahlapp.App.Main.MainActivityPresenter;
import com.example.asherif.sahlapp.App.Main.MainView;

import org.junit.Assert;
import org.junit.Test;

public class MainActivityPresenterTest {
    @Test
    public void showIdPass(){
        Assert.assertEquals(1,2);
    }
    @Test
    public void showpassdatatoview(){
        //given
MainView view=new MockView();
        //when
        MainActivityPresenter mainActivityPresenter=new MainActivityPresenter(view);

        //then
    }
    private class MockView implements MainView{

        @Override
        public void NavigateToMyADS() {

        }

        @Override
        public void NavigateToFavorites() {

        }

        @Override
        public void NavigateToNewestADS() {

        }

        @Override
        public void NavigateToCreateAdvertisment() {

        }

        @Override
        public void NavigteToProfile() {

        }

        @Override
        public void ChangeTabColor(TabLayout tabLayout, ViewPager viewPager, String myads, String favorite, String newestads) {

        }

        @Override
        public void checkVisitor() {

        }

        @Override
        public void SetNavigationMenu(NavigationView nv) {

        }

        @Override
        public void TabIcons(TabLayout tabLayout, ViewPager viewPager, String myads) {

        }
    }

}