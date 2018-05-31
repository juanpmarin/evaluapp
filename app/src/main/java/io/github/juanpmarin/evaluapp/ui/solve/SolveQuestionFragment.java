package io.github.juanpmarin.evaluapp.ui.solve;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.databinding.FragmentSolveQuestionBinding;
import io.github.juanpmarin.evaluapp.domain.QuestionOption;
import io.github.juanpmarin.evaluapp.domain.QuestionType;
import io.github.juanpmarin.evaluapp.domain.QuestionWithAllOptions;
import io.github.juanpmarin.evaluapp.ui.results.ViewResultActivity;


public class SolveQuestionFragment extends Fragment implements BlockingStep {

    private static final String QUESTION_ID = "questionId";
    private static final String ERROR_MESSAGE = "Debes contestar la pregunta";

    private String questionId;

    private SolveTestViewModel solveTestViewModel;
    private FragmentSolveQuestionBinding binding;

    private LiveData<QuestionWithAllOptions> question;

    public SolveQuestionFragment() {

    }

    public static SolveQuestionFragment newInstance(String questionId) {
        SolveQuestionFragment fragment = new SolveQuestionFragment();
        Bundle args = new Bundle();
        args.putString(QUESTION_ID, questionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionId = getArguments().getString(QUESTION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_solve_question, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        solveTestViewModel = ViewModelProviders.of(getActivity())
                .get(SolveTestViewModel.class);

        this.question = solveTestViewModel.findQuestionWithAnswersById(questionId);

        this.question.observe(this, question -> {
            assert question != null;
            binding.setQuestion(question.question);

            if (question.question.getType() == QuestionType.MULTIPLE_CHOICE) {
                binding.radioGroup.removeAllViews();

                for (QuestionOption option : question.options) {
                    RadioButton radioButton = new RadioButton(getContext());
                    radioButton.setId(option.getId().hashCode());
                    radioButton.setText(option.getDescription());
                    radioButton.setHeight(getResources().getDimensionPixelSize(R.dimen.radio_height));

                    binding.radioGroup.addView(radioButton);
                }
            }
        });
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        QuestionWithAllOptions question = this.question.getValue();

        if (question != null) {
            QuestionType questionType = question.question.getType();

            if (questionType == QuestionType.TRUE_FALSE) {
                if (!binding.radioFalse.isChecked() &&
                        !binding.radioTrue.isChecked()) {
                    return new VerificationError(ERROR_MESSAGE);
                }

                String answer = binding.radioTrue.isChecked() ? "Verdadero" : "Falso";
                boolean isRight = question.options.get(0).getDescription().equals(answer);

                solveTestViewModel.setAnswer(questionId, question.question.getDescription(), answer, isRight);
            } else if (questionType == QuestionType.MULTIPLE_CHOICE) {
                RadioButton checked = null;

                for (QuestionOption questionOption : question.options) {
                    RadioButton radioButton = getView().findViewById(questionOption.getId().hashCode());

                    if (radioButton.isChecked()) {
                        checked = radioButton;
                    }
                }

                if (checked == null) {
                    return new VerificationError(ERROR_MESSAGE);
                }

                boolean isRight = checked.getId() == question.question.getAnswerId().hashCode();
                solveTestViewModel.setAnswer(questionId, question.question.getDescription(), checked.getText().toString(), isRight);
            } else if (questionType == QuestionType.COMPLETE) {
                String answer = binding.answer.getText().toString().trim();

                if (TextUtils.isEmpty(answer)) {
                    return new VerificationError(ERROR_MESSAGE);
                }

                boolean isRight = answer.toLowerCase().equals(question.options.get(0).getDescription().toLowerCase());
                solveTestViewModel.setAnswer(questionId, question.question.getDescription(), answer, isRight);
            }
        }

        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Snackbar.make(getView(), error.getErrorMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        String id = solveTestViewModel.saveAnswers();

        Intent intent = new Intent(getActivity(), ViewResultActivity.class);
        intent.putExtra(ViewResultActivity.RESULT_ID, id);

        startActivity(intent);
        getActivity().finish();

        callback.complete();
    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();
    }
}
