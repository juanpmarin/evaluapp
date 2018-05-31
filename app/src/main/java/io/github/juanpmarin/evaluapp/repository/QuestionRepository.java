package io.github.juanpmarin.evaluapp.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.AppExecutors;
import io.github.juanpmarin.evaluapp.db.QuestionDao;
import io.github.juanpmarin.evaluapp.domain.Question;
import io.github.juanpmarin.evaluapp.domain.QuestionOption;
import io.github.juanpmarin.evaluapp.domain.QuestionWithAllOptions;
import io.github.juanpmarin.evaluapp.domain.QuestionWithAnswer;

public class QuestionRepository {

    private final QuestionDao questionDao;

    private final AppExecutors appExecutors;

    @Inject
    public QuestionRepository(QuestionDao questionDao, AppExecutors appExecutors) {
        this.questionDao = questionDao;
        this.appExecutors = appExecutors;
    }

    public LiveData<List<QuestionWithAnswer>> findAllWithAnswerByTestId(String testId) {
        return this.questionDao.findAllWithAnswerByTestId(testId);
    }

    public void saveWithOptions(Question question, List<QuestionOption> options) {
        appExecutors.diskIO().execute(() -> {
            this.questionDao.saveWithOptions(question, options);
        });
    }

    public LiveData<List<String>> findAllIdsByTestId(String testId) {
        return questionDao.findAllIdsByTestId(testId);
    }

    public LiveData<QuestionWithAllOptions> findQuestionWithAnswersById(String id) {
        return questionDao.findQuestionWithAnswersById(id);
    }
}
