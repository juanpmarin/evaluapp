package io.github.juanpmarin.evaluapp.ui.tests;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.ActivityEditTestBinding;

public class EditTestActivity extends DaggerAppCompatActivity implements EditQuestionsController.AdapterCallbacks {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityEditTestBinding binding;
    private EditTestViewModel editTestViewModel;
    private EditQuestionsController editQuestionsController;

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

        editQuestionsController = new EditQuestionsController(this, this);

        initializeErrorPresenter();
        initializeQuestionsList();
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

    private void initializeErrorPresenter() {
        editTestViewModel.getError().observe(this, resId -> {
            if (resId != null) {
                Snackbar.make(binding.title, resId, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void initializeQuestionsList() {
        binding.setAdapter(editQuestionsController.getAdapter());
        editQuestionsController.setData(Collections.emptyList());
    }

    @Override
    public void addQuestion() {

    }

    private void saveTest() {
        boolean saved = editTestViewModel.saveTest(binding.title.getText().toString());

        if (saved) {
            finish();
        }
    }

}
