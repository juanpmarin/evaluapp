package io.github.juanpmarin.evaluapp.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.ActivityMainBinding;
import io.github.juanpmarin.evaluapp.ui.results.ResultsFragment;
import io.github.juanpmarin.evaluapp.ui.tests.TestsFragment;

public class MainActivity extends DaggerAppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        binding.setNavigationCallback(this);
        binding.setNavigationAdapter(getNavigationAdapter());

        mainViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        mainViewModel.getCurrentNavigationItem()
                .observe(this, binding::setCurrentNavigationItem);
    }

    private PagerAdapter getNavigationAdapter() {
        return new NavigationPagerAdapter(getSupportFragmentManager());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int item = -1;

        switch (menuItem.getItemId()) {
            case R.id.navigation_tests:
                item = 0;
                break;
            case R.id.navigation_results:
                item = 1;
                break;
        }

        if (item >= 0) {
            mainViewModel.setCurrentNavigationItem(item);
            return true;
        }

        return false;
    }

    private static class NavigationPagerAdapter extends FragmentPagerAdapter {

        NavigationPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TestsFragment.newInstance();
                case 1:
                    return ResultsFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

}
