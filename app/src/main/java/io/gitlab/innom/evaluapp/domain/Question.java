package io.gitlab.innom.evaluapp.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Test.class,
        parentColumns = "id",
        childColumns = "test_id",
        onDelete = CASCADE))
public class Question {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "test_id")
    private Long testId;

}
