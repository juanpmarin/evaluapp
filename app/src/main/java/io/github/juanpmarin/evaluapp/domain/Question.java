package io.github.juanpmarin.evaluapp.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.UUID;

import io.github.juanpmarin.evaluapp.db.Converters;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Test.class,
        parentColumns = "id",
        childColumns = "test_id",
        onDelete = CASCADE),
        indices = {@Index(value = "test_id"), @Index(value = "answer_id")})
@TypeConverters(Converters.class)
public class Question {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "test_id")
    private String testId;

    @ColumnInfo(name = "type")
    private QuestionType type;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "answer_id")
    private String answerId;

    @Ignore
    public Question(String testId, QuestionType type, String description) {
        this.id = UUID.randomUUID().toString();
        this.testId = testId;
        this.type = type;
        this.description = description;
    }

    public Question(@NonNull String id, String testId, QuestionType type, String description, String answerId) {
        this.id = id;
        this.testId = testId;
        this.type = type;
        this.description = description;
        this.answerId = answerId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!id.equals(question.id)) return false;
        if (testId != null ? !testId.equals(question.testId) : question.testId != null)
            return false;
        if (type != question.type) return false;
        if (description != null ? !description.equals(question.description) : question.description != null)
            return false;
        return answerId != null ? answerId.equals(question.answerId) : question.answerId == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (testId != null ? testId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (answerId != null ? answerId.hashCode() : 0);
        return result;
    }

}
