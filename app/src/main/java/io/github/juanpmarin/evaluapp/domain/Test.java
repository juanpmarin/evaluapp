package io.github.juanpmarin.evaluapp.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

import io.github.juanpmarin.evaluapp.db.Converters;
import lombok.Data;

@Data
@Entity
@TypeConverters(Converters.class)
public class Test {

    @NonNull
    @PrimaryKey
    private String id;

    private String name;

    private Boolean temp;

    private Date created;

    @Ignore
    public Test() {
        this.id = UUID.randomUUID().toString();
        this.temp = true;
        this.created = new Date();
    }

    public Test(@NonNull String id, String name, Boolean temp, Date created) {
        this.id = id;
        this.name = name;
        this.temp = temp;
        this.created = created;
    }

}
