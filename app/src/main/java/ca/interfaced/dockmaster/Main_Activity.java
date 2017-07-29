package ca.interfaced.dockmaster;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ca.interfaced.dockmaster.Model.Project;
import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Main_Activity extends AppCompatActivity  {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> tabTitles = new ArrayList<>();
    private MyPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private ViewPager mViewPager;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        setContentView(R.layout.main);

        fragmentList.add(Projectslist_Fragment.newInstance());
        fragmentList.add(MySchedule_Fragment.newInstance());
        fragmentList.add(Settings_Fragment.newInstance());

        tabTitles.add("PROJECTS");
        tabTitles.add("MY SCHEDULE");
        tabTitles.add("SETTINGS");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Realm.init(this);

    }


    private class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragmentList.get(pos);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }


        @Override
        public int getItemPosition(Object object) {
            // refresh all fragments when data set changed
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }



    }
}
