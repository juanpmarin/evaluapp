package io.github.juanpmarin.evaluapp.ui.solve;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.ActivitySolveTestBinding;
import io.github.juanpmarin.evaluapp.ui.MainActivity;

public class SolveTestActivity extends DaggerAppCompatActivity {

    public static final String TEST_ID = "testId";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private SolveTestViewModel solveTestViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySolveTestBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_solve_test);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        solveTestViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(SolveTestViewModel.class);

        solveTestViewModel.setStudent(getIntent().getStringExtra(MainActivity.USER_ID));

        StepperAdapter adapter = new StepperAdapter(getSupportFragmentManager(), this);

        solveTestViewModel.setTestId(getIntent().getStringExtra(TEST_ID));

        solveTestViewModel.getTest().observe(this, test -> {
            assert test != null;
            getSupportActionBar().setTitle(test.getName());
        });

        solveTestViewModel.findAllQuestionIds().observe(this, ids -> {
            assert ids != null;
            adapter.setQuestionIds(ids);
            runOnUiThread(() -> binding.stepper.setAdapter(adapter));
        });

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

    private static class StepperAdapter extends AbstractFragmentStepAdapter {

        @NonNull
        private List<String> questionIds;


        public StepperAdapter(@NonNull FragmentManager fm,
                              @NonNull Context context) {
            super(fm, context);
            this.questionIds = Collections.emptyList();
        }

        @Override
        public Step createStep(int position) {
            return SolveQuestionFragment.newInstance(questionIds.get(position));
        }

        @Override
        public int getCount() {
            return questionIds.size();
        }

        public void setQuestionIds(@NonNull List<String> questionIds) {
            this.questionIds = questionIds;
        }

    }

}
