package io.github.juanpmarin.evaluapp.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

import io.github.juanpmarin.evaluapp.domain.QuestionType;

public class Converters {

    @TypeConverter
    public static Date timestampToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Integer questionTypeToInteger(QuestionType questionType) {
        return questionType.ordinal();
    }

    @TypeConverter
    public static QuestionType integerToQuestionType(Integer ordinal) {
        return QuestionType.values()[ordinal];
    }

}
