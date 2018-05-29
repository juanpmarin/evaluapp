package io.github.juanpmarin.evaluapp.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import lombok.Data;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Data
@Entity(foreignKeys = @ForeignKey(entity = Question.class,
        parentColumns = "id",
        childColumns = "question_id",
        onDelete = CASCADE),
        indices = {@Index(value = "question_id")})
public class QuestionOption {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "question_id")
    private String questionId;

    @ColumnInfo(name = "description")
    private String description;

    @Ignore
    public QuestionOption(String questionId, String description) {
        this.id = UUID.randomUUID().toString();
        this.questionId = questionId;
        this.description = description;
    }

    public QuestionOption(@NonNull String id, String questionId, String description) {
        this.id = id;
        this.questionId = questionId;
        this.description = description;
    }

}
