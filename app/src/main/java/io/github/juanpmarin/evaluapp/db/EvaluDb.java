package io.github.juanpmarin.evaluapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import io.github.juanpmarin.evaluapp.domain.Question;
import io.github.juanpmarin.evaluapp.domain.Test;

@Database(entities = {
        Question.class,
        Test.class}, version = 1)
public abstract class EvaluDb extends RoomDatabase {

    abstract public TestDao testDao();

    abstract public QuestionDao questionDao();

}
