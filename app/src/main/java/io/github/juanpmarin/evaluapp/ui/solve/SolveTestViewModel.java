package io.github.juanpmarin.evaluapp.ui.solve;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.github.juanpmarin.evaluapp.domain.Answer;
import io.github.juanpmarin.evaluapp.domain.QuestionWithAllOptions;
import io.github.juanpmarin.evaluapp.domain.Result;
import io.github.juanpmarin.evaluapp.domain.Test;
import io.github.juanpmarin.evaluapp.repository.QuestionRepository;
import io.github.juanpmarin.evaluapp.repository.ResultRepository;
import io.github.juanpmarin.evaluapp.repository.TestRepository;

import static android.arch.lifecycle.Transformations.switchMap;

public class SolveTestViewModel extends ViewModel {

    private final QuestionRepository questionRepository;
    private final ResultRepository resultRepository;

    private final MutableLiveData<String> testId;
    private final LiveData<Test> test;
    private final LiveData<List<String>> questionIds;
    private final Map<String, Answer> answers;
    private String student;

    @Inject
    public SolveTestViewModel(TestRepository testRepository, QuestionRepository questionRepository, ResultRepository resultRepository) {
        this.questionRepository = questionRepository;
        this.resultRepository = resultRepository;
        this.testId = new MutableLiveData<>();

        this.test = switchMap(testId, testRepository::findById);
        this.questionIds = switchMap(testId, questionRepository::findAllIdsByTestId);
        this.answers = new HashMap<>();
    }

    public LiveData<List<String>> findAllQuestionIds() {
        return questionIds;
    }

    public LiveData<Test> getTest() {
        return test;
    }

    public LiveData<QuestionWithAllOptions> findQuestionWithAnswersById(String id) {
        return questionRepository.findQuestionWithAnswersById(id);
    }

    public void setTestId(String testId) {
        this.testId.setValue(testId);
    }

    public void setAnswer(String questionId, String question, String answer, Boolean right) {
        answers.put(questionId, new Answer(question, answer, right));
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String saveAnswers() {
        Result result = new Result(testId.getValue(), test.getValue().getName(), student, new ArrayList<>(answers.values()));

        return resultRepository.saveAnswers(result);
    }

}
