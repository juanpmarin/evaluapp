package io.gitlab.innom.evaluapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import io.gitlab.innom.evaluapp.domain.Question;
import io.gitlab.innom.evaluapp.domain.Test;

@Database(entities = {
        Question.class,
        Test.class}, version = 1)
public abstract class EvaluDb extends RoomDatabase {

    abstract public TestDao testDao();

}
