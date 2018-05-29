package io.github.juanpmarin.evaluapp.ui.questions;


import android.app.Dialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Arrays;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.FragmentEditQuestionBinding;
import io.github.juanpmarin.evaluapp.di.Injectable;
import io.github.juanpmarin.evaluapp.domain.QuestionType;

public class EditQuestionFragment extends DialogFragment implements Injectable {

    private static final String TEST_ID = "testId";
    private static final String QUESTION_TYPE = "questionType";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private FragmentEditQuestionBinding binding;

    private String testId;
    private QuestionType questionType;
    private EditQuestionViewModel editQuestionViewModel;

    public static EditQuestionFragment newInstance(String testId, QuestionType questionType) {
        EditQuestionFragment fragment = new EditQuestionFragment();
        Bundle args = new Bundle();
        args.putString(TEST_ID, testId);
        args.putInt(QUESTION_TYPE, questionType.ordinal());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            testId = getArguments().getString(TEST_ID);
            questionType = QuestionType.values()[getArguments().getInt(QUESTION_TYPE)];
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.edit_question);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_edit_question,
                null,
                false);

        binding.setQuestionType(questionType);

        builder.setView(binding.getRoot());
        builder.setPositiveButton(R.string.save, null);
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dismiss());

        editQuestionViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EditQuestionViewModel.class);

        editQuestionViewModel.setTestId(testId);

        initializeErrorPresenter();

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(d -> {
            Button positiveButton = ((AlertDialog) d).getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(v -> saveQuestion());

            binding.description.requestFocus();
            ((AlertDialog) d).getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        });

        return dialog;
    }

    private void initializeErrorPresenter() {
        editQuestionViewModel.getError().observe(this, resId -> {
            if (resId != null) {
                Snackbar.make(binding.getRoot(), resId, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void saveQuestion() {
        Boolean result = true;

        if (questionType == QuestionType.TRUE_FALSE) {
            result = editQuestionViewModel.saveQuestion(
                    binding.description.getText().toString(),
                    binding.radioTrue.isChecked());
        } else if (questionType == QuestionType.MULTIPLE_CHOICE) {
            result = editQuestionViewModel.saveQuestion(
                    binding.description.getText().toString(),
                    Arrays.asList(
                            binding.rightAnswer.getText().toString(),
                            binding.incorrectAnswer1.getText().toString(),
                            binding.incorrectAnswer2.getText().toString(),
                            binding.incorrectAnswer3.getText().toString()
                    ));
        } else if (questionType == QuestionType.COMPLETE) {
            result = editQuestionViewModel.saveQuestion(
                    binding.description.getText().toString(),
                    binding.answer.getText().toString());
        }

        if (result) {
            dismiss();
        }
    }

}
