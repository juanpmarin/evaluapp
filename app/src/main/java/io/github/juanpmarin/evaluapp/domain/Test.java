package io.github.juanpmarin.evaluapp.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

import io.github.juanpmarin.evaluapp.db.Converters;

@Entity
@TypeConverters(Converters.class)
public class Test {

    @NonNull
    @PrimaryKey
    private String id;

    private String name;

    private Date created;

    @Ignore
    public Test(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.created = new Date();
    }

    public Test(@NonNull String id, String name, Date created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreated() {
        return created;
    }

}
