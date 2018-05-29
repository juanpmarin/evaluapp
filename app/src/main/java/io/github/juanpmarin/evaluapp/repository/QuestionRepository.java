package io.github.juanpmarin.evaluapp.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.AppExecutors;
import io.github.juanpmarin.evaluapp.db.QuestionDao;
import io.github.juanpmarin.evaluapp.domain.Question;

public class QuestionRepository {

    private final QuestionDao questionDao;

    private final AppExecutors appExecutors;

    @Inject
    public QuestionRepository(QuestionDao questionDao, AppExecutors appExecutors) {
        this.questionDao = questionDao;
        this.appExecutors = appExecutors;
    }

    public LiveData<List<Question>> findAllByTestId(String testId) {
        return this.questionDao.findAllByTestId(testId);
    }

}
