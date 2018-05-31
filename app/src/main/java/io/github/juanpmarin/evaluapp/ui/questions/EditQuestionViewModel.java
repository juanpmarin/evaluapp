package io.github.juanpmarin.evaluapp.ui.questions;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.domain.Question;
import io.github.juanpmarin.evaluapp.domain.QuestionOption;
import io.github.juanpmarin.evaluapp.domain.QuestionType;
import io.github.juanpmarin.evaluapp.repository.QuestionRepository;

public class EditQuestionViewModel extends ViewModel {

    private final QuestionRepository questionRepository;

    private MutableLiveData<Integer> error;
    private String testId;

    @Inject
    EditQuestionViewModel(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;

        this.error = new MutableLiveData<>();
    }

    private void saveQuestionWithOptions(Question question, List<QuestionOption> options) {
        questionRepository.saveWithOptions(question, options);
    }

    public boolean saveQuestion(String description, Boolean answer) {
        description = description.trim();

        if (TextUtils.isEmpty(description)) {
            error.setValue(R.string.error_must_write_description);
            return false;
        }

        Question question = new Question(testId, QuestionType.TRUE_FALSE, description);
        QuestionOption option = new QuestionOption(question.getId(), answer ? "Verdadero" : "Falso");
        question.setAnswerId(option.getId());

        saveQuestionWithOptions(question, Collections.singletonList(option));

        return true;
    }

    public boolean saveQuestion(String description, String answer) {
        Question question = new Question(testId, QuestionType.COMPLETE, description);
        QuestionOption option = new QuestionOption(question.getId(), answer);
        question.setAnswerId(option.getId());

        saveQuestionWithOptions(question, Collections.singletonList(option));

        return true;
    }

    public boolean saveQuestion(String description, List<String> answers) {
        description = description.trim();

        if (TextUtils.isEmpty(description)) {
            error.setValue(R.string.error_must_write_description);
            return false;
        }

        if (TextUtils.isEmpty(answers.get(0).trim())) {
            error.setValue(R.string.error_must_insert_right_answer);
            return false;
        }

        Question question = new Question(testId, QuestionType.MULTIPLE_CHOICE, description);

        List<QuestionOption> options = new ArrayList<>();
        Integer empty = 0;

        for (String answer : answers) {
            answer = answer.trim();

            if (TextUtils.isEmpty(answer)) {
                if (empty == 2) {
                    error.setValue(R.string.error_must_insert_two_answers_at_least);
                    return false;
                }

                empty++;
            }

            options.add(new QuestionOption(question.getId(), answer));
        }

        question.setAnswerId(options.get(0).getId());
        saveQuestionWithOptions(question, options);

        return true;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public LiveData<Integer> getError() {
        return error;
    }

}
