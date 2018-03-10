package com.gmail.derevets.artem.springsecurityapp.model;

import java.io.Serializable;

public abstract class Model  implements Serializable{
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

