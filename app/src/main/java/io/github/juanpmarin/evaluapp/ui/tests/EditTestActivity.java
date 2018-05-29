package io.github.juanpmarin.evaluapp.ui.tests;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.ActivityEditTestBinding;
import io.github.juanpmarin.evaluapp.domain.QuestionType;
import io.github.juanpmarin.evaluapp.ui.questions.EditQuestionFragment;

public class EditTestActivity extends DaggerAppCompatActivity implements QuestionsController.AdapterCallbacks {

    public static final String TEST_ID = "testId";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityEditTestBinding binding;
    private EditTestViewModel editTestViewModel;
    private QuestionsController questionsController;

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

        questionsController = new QuestionsController(this, this);

        initializeErrorPresenter();
        initializeQuestionsList();

        editTestViewModel.getTest().observe(this, test -> {
            if (test != null) {
                binding.title.setText(test.getName());
            }
        });

        editTestViewModel.setUp(getIntent().getStringExtra(TEST_ID));

        binding.fabTrueFalse.setOnClickListener(v -> editQuestion(QuestionType.TRUE_FALSE));
        binding.fabMultipleChoice.setOnClickListener(v -> editQuestion(QuestionType.MULTIPLE_CHOICE));
        binding.fabComplete.setOnClickListener(v -> editQuestion(QuestionType.COMPLETE));
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
        binding.setAdapter(questionsController.getAdapter());
        editTestViewModel.getQuestionsWithAnswers().observe(this, questions -> {
            if (questions != null) {
                questionsController.setData(questions);
                binding.list.post(() -> binding.setShowHint(questions.isEmpty()));
            }
        });
    }

    private void saveTest() {
        boolean saved = editTestViewModel.saveTest(binding.title.getText().toString());

        if (saved) {
            finish();
        }
    }

    private void editQuestion(QuestionType questionType) {
        binding.fab.close(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        EditQuestionFragment editQuestionFragment = EditQuestionFragment.newInstance(
                editTestViewModel.getTestId().getValue(),
                questionType);
        editQuestionFragment.show(fragmentManager, "edit_question");
    }

}
