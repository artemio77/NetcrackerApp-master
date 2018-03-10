package com.gmail.derevets.artem.springsecurityapp.model;

import java.io.Serializable;

public class Department extends Model implements Serializable{


    private String name;
    private String loc;

    public Department() {
    }

    public Department(String name, String loc) {
        this.name = name;
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", loc='" + loc + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
