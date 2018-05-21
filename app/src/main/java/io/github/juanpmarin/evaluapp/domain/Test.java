package io.github.juanpmarin.evaluapp.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import io.github.juanpmarin.evaluapp.db.Converters;

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

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getTemp() {
        return temp;
    }

    public Date getCreated() {
        return created;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test test = (Test) o;

        if (!id.equals(test.id)) return false;
        if (name != null ? !name.equals(test.name) : test.name != null) return false;
        if (temp != null ? !temp.equals(test.temp) : test.temp != null) return false;
        return created != null ? created.equals(test.created) : test.created == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (temp != null ? temp.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

}
