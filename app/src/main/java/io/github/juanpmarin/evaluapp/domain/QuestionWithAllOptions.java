package io.github.juanpmarin.evaluapp.domain;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class QuestionWithAllOptions {

    @Embedded
    public Question question;

    @Relation(parentColumn = "id", entityColumn = "question_id")
    public List<QuestionOption> options;

}
