package io.github.juanpmarin.evaluapp.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import static android.arch.persistence.room.ForeignKey.CASCADE;

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

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionOption that = (QuestionOption) o;

        if (!id.equals(that.id)) return false;
        if (questionId != null ? !questionId.equals(that.questionId) : that.questionId != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (questionId != null ? questionId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

}
