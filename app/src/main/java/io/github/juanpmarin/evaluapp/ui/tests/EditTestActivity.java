package io.github.juanpmarin.evaluapp.ui.tests;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.ActivityEditTestBinding;

public class EditTestActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityEditTestBinding binding;
    private EditTestViewModel editTestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_test);

        binding.toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        editTestViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EditTestViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_test, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_save:
                saveTest();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveTest() {
        editTestViewModel.saveTest(binding.title.getText().toString());
        finish();
    }

}
