package io.github.juanpmarin.evaluapp.ui.tests;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import java.util.List;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.R;
import io.github.juanpmarin.evaluapp.domain.QuestionWithAnswer;
import io.github.juanpmarin.evaluapp.domain.Test;
import io.github.juanpmarin.evaluapp.repository.QuestionRepository;
import io.github.juanpmarin.evaluapp.repository.TestRepository;

import static android.arch.lifecycle.Transformations.switchMap;

public class EditTestViewModel extends ViewModel {

    private TestRepository testRepository;

    private MutableLiveData<String> testId;

    private MutableLiveData<Integer> error;

    private LiveData<Test> test;

    private LiveData<List<QuestionWithAnswer>> questionsWithAnswer;

    @Inject
    EditTestViewModel(TestRepository testRepository, QuestionRepository questionRepository) {
        this.testRepository = testRepository;

        this.testId = new MutableLiveData<>();
        this.error = new MutableLiveData<>();

        this.test = switchMap(testId, testRepository::findById);
        this.questionsWithAnswer = switchMap(testId, questionRepository::findAllWithAnswerByTestId);
    }

    void setUp(String testId) {
        if (testId == null) {
            Test test = new Test();
            testId = test.getId();

            testRepository.insert(test);
        }

        this.testId.setValue(testId);
    }

    public LiveData<Test> getTest() {
        return test;
    }

    public LiveData<String> getTestId() {
        return testId;
    }

    public LiveData<List<QuestionWithAnswer>> getQuestionsWithAnswers() {
        return questionsWithAnswer;
    }

    MutableLiveData<Integer> getError() {
        return error;
    }

    boolean saveTest(String name) {
        name = name.trim();

        if (TextUtils.isEmpty(name)) {
            error.setValue(R.string.error_must_insert_title);
            return false;
        }

        List<QuestionWithAnswer> questions = this.questionsWithAnswer.getValue();

        if (questions == null || questions.isEmpty()) {
            error.setValue(R.string.error_must_add_questions);
            return false;
        }

        Test test = this.test.getValue();
        assert test != null;

        test.setName(name);
        test.setTemp(false);

        testRepository.update(test);

        return true;
    }

}
