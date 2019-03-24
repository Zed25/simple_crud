package com.zed.simple_crud.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.zed.simple_crud.json_views.SimpleCrudViews;
import com.zed.simple_crud.model.interfaces.CrudEntity;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;


public abstract class CrudEntityImpl<ID> implements CrudEntity<ID> {

    @JsonView(SimpleCrudViews.Public.class)
    @NotNull
    @Id
    protected ID id;

    public CrudEntityImpl() {
    }

    public CrudEntityImpl(@NotNull ID id) {
        this.id = id;
    }

    @Override
    public ID getID() {
        return this.id;
    }

    @Override
    public boolean sameID(@NotNull ID id) {
        return this.id.equals(id);
    }

    @Override
    public boolean update(@NotNull Object obj){
        return false;
    }
}
