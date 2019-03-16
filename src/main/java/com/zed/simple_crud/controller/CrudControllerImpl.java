package com.zed.simple_crud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zed.simple_crud.controller.interfaces.CrudController;
import com.zed.simple_crud.enumeration.OperationDeniedException;
import com.zed.simple_crud.json_views.SimpleCrudViews;
import com.zed.simple_crud.model.CrudEntityImpl;
import com.zed.simple_crud.service.interfaces.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

public abstract class CrudControllerImpl<T extends CrudEntityImpl<ID>, ID> implements CrudController<T, ID> {

    @Override
    @JsonView(SimpleCrudViews.Details.class)
    @GetMapping("/{id}")
    public @NotNull ResponseEntity getOneByID(@PathVariable ID id) {
        T element = this.getService().getOneByID(id);
        if (element != null) return new ResponseEntity<>(element, HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    @JsonView(SimpleCrudViews.Public.class)
    @GetMapping
    public @NotNull ResponseEntity getAll() {
        return new ResponseEntity<>(this.getService().getAll(), HttpStatus.OK);
    }

    @Override
    @JsonView(SimpleCrudViews.Public.class)
    @PostMapping
    public @NotNull ResponseEntity createOne(@RequestBody T obj) {
        try {
            return new ResponseEntity<>(this.getService().saveOne(obj), HttpStatus.CREATED);
        } catch (OperationDeniedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    @JsonView(SimpleCrudViews.Details.class)
    @PutMapping("/{id}")
    public @NotNull ResponseEntity updateOne(@PathVariable ID id, @RequestBody T obj) {
        try {
            return new ResponseEntity<>(this.getService().updateOne(id, obj), HttpStatus.OK);
        } catch (OperationDeniedException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public @NotNull ResponseEntity deleteOne(@PathVariable ID id) {
        this.getService().deleteOne(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public abstract CrudService<T, ID> getService();
}
