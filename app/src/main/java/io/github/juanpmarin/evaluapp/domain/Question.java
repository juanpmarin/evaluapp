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
import lombok.Data;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Data
@Entity(foreignKeys = @ForeignKey(entity = Test.class,
        parentColumns = "id",
        childColumns = "test_id",
        onDelete = CASCADE),
        indices = {@Index(value = "test_id")})
@TypeConverters(Converters.class)
public class Question {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "test_id")
    private String testId;

    @ColumnInfo(name = "type")
    private QuestionType type;

    @Ignore
    public Question(String testId) {
        this.id = UUID.randomUUID().toString();
        this.testId = testId;
    }

    public Question(@NonNull String id, String testId, QuestionType type) {
        this.id = id;
        this.testId = testId;
        this.type = type;
    }

}
