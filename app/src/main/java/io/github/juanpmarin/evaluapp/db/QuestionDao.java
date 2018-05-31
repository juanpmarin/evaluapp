package io.github.juanpmarin.evaluapp.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;

import java.util.List;

import io.github.juanpmarin.evaluapp.domain.Question;
import io.github.juanpmarin.evaluapp.domain.QuestionOption;
import io.github.juanpmarin.evaluapp.domain.QuestionWithAllOptions;
import io.github.juanpmarin.evaluapp.domain.QuestionWithAnswer;

@Dao
public interface QuestionDao {

    @Query("SELECT * FROM Question WHERE test_id = :testId")
    LiveData<List<Question>> findAllByTestId(String testId);

    @Query("SELECT Question.id, Question.description AS question, " +
            "QuestionOption.description AS answer " +
            "FROM Question INNER JOIN QuestionOption ON Question.answer_id = QuestionOption.id " +
            "WHERE test_id = :testId")
    LiveData<List<QuestionWithAnswer>> findAllWithAnswerByTestId(String testId);

    @Insert
    void saveWithOptions(Question question, List<QuestionOption> options);

    @Query("SELECT id FROM Question WHERE test_id = :testId")
    LiveData<List<String>> findAllIdsByTestId(String testId);

    @Transaction
    @Query("SELECT * FROM Question WHERE id = :id")
    LiveData<QuestionWithAllOptions> findQuestionWithAnswersById(String id);

}
