package io.github.juanpmarin.evaluapp.ui.results;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.ActivityViewResultBinding;

public class ViewResultActivity extends DaggerAppCompatActivity {

    public static final String RESULT_ID = "resultId";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ActivityViewResultBinding binding;
    private ViewResultViewModel viewResultViewModel;
    private ViewResultController viewResultController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_result);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewResultViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ViewResultViewModel.class);
        viewResultController = new ViewResultController(this);

        viewResultViewModel.setResultId(getIntent().getStringExtra(RESULT_ID));

        viewResultViewModel.getResult().observe(this, result -> {
            if (result != null && getSupportActionBar() != null) {
                runOnUiThread(() -> getSupportActionBar().setTitle(result.getTest()));
            }
        });

        initAnswersList();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initAnswersList() {
        binding.setAdapter(viewResultController.getAdapter());
        viewResultViewModel.getResult().observe(this, result -> {
            if (result != null) {
                viewResultController.setData(result);
            }
        });
    }


}
