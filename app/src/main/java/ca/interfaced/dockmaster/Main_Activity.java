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

import ca.interfaced.dockmaster.Model.Asset;
import ca.interfaced.dockmaster.Model.Project;
import ca.interfaced.dockmaster.Model.User;
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


        // TODO: only load dummy data if realm is empty

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        Project project1 = realm.createObject(Project.class);
        project1.setId("1");
        project1.setProjectName("111 Richmond");
        project1.setProjectAddress("111 Richmond Street, Toronto");
        project1.setImage("project1");
        realm.insertOrUpdate(project1);

        Project project2 = realm.createObject(Project.class);
        project2.setId("2");
        project2.setProjectName("Manulife Center");
        project2.setProjectAddress("50 Bloor Street West, Toronto");
        project2.setImage("project2");
        realm.insertOrUpdate(project2);

        Project project3 = realm.createObject(Project.class);
        project3.setId("3");
        project3.setProjectName("Yonge and Sheppard");
        project3.setProjectAddress("1856 Sheppard Avenue, Toronto");
        realm.insertOrUpdate(project3);

        User user1 = realm.createObject(User.class);
        user1.setId("1");
        user1.setFirstName("Viviane");
        user1.setLastName("Chan");
        user1.setCompanyName("Interfaced");
        user1.setEmail("vivianechan@hotmail.com");
        user1.setPassword("password");
        user1.setPhoneNumber("6478365162");
        user1.setMobileNumber("6478365162");
        user1.setImage("vivianechan");
        realm.insertOrUpdate(user1);

        User user2 = realm.createObject(User.class);
        user2.setId("2");
        user2.setFirstName("John");
        user2.setLastName("Smith");
        user2.setCompanyName("PCL Constructors Inc.");
        user2.setEmail("john.smith@pcl.com");
        user2.setPassword("password");
        user2.setPhoneNumber("6471234567");
        user2.setMobileNumber("6471234567");
        user2.setImage("johnsmith");
        realm.insertOrUpdate(user2);

        Asset asset1 = realm.createObject(Asset.class);
        asset1.setId("1");
        asset1.setAssetName("Elevator A");
        asset1.setImage("elevator");
        realm.insertOrUpdate(asset1);

        Asset asset2 = realm.createObject(Asset.class);
        asset2.setId("2");
        asset2.setAssetName("Crane 1");
        asset2.setImage("crane");
        realm.insertOrUpdate(asset1);

        project1.getUsers().add(user1);
        project1.getUsers().add(user2);
        project2.getUsers().add(user1);
        project2.getUsers().add(user2);
        project3.getUsers().add(user2);

        project1.getContacts().add(user2);
        project2.getContacts().add(user1);
        project3.getContacts().add(user2);

        project1.getAssets().add(asset1);
        project2.getAssets().add(asset2);

        realm.commitTransaction();

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
