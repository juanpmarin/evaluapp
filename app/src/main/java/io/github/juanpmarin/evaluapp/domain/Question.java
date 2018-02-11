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

@Entity(foreignKeys = @ForeignKey(entity = Test.class,
        parentColumns = "id",
        childColumns = "test_id",
        onDelete = CASCADE),
        indices = {@Index(value = "test_id")})
public class Question {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "test_id")
    private String testId;

    @Ignore
    public Question(String testId) {
        this.id = UUID.randomUUID().toString();
        this.testId = testId;
    }

    public Question(@NonNull String id, String testId) {
        this.id = id;
        this.testId = testId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getTestId() {
        return testId;
    }

}
