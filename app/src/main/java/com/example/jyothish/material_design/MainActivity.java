package com.example.jyothish.material_design;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.jyothish.material_design.fragments.FragmentDrawer;
import com.example.jyothish.material_design.fragments.FriendsFragment;
import com.example.jyothish.material_design.fragments.LoginFragment;
import com.example.jyothish.material_design.fragments.MessageFragment;
import com.example.jyothish.material_design.fragments.TrafficNewsFragment;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {
    private static final String TAG = "MainActivity";

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        displayView(0);
    }
    

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new LoginFragment();
                title = getString(R.string.title_map);
                break;
            case 1:
                fragment = new FriendsFragment();
                title = getString(R.string.title_traffic_news);
                break;
            case 2:
                fragment = new MessageFragment();
                title = getString(R.string.title_emergency );
                break;
            case 3:
                fragment = new MessageFragment();
                title = getString(R.string.title_emergency );
            case 4:
                fragment = new TrafficNewsFragment();
                title = getString(R.string.title_traffic_news );
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}
