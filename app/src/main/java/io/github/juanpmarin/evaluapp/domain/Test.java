package io.github.juanpmarin.evaluapp.domain;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Test {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String name;

    public Test() {

    }

    public Test(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
