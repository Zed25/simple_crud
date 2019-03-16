package com.zed.simple_crud.controller.interfaces;

import com.fasterxml.jackson.annotation.JsonView;
import com.zed.simple_crud.json_views.SimpleCrudViews;
import com.zed.simple_crud.model.interfaces.CrudEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

public interface CrudController<T extends CrudEntity<ID>, ID> {

    @NotNull
    @JsonView(SimpleCrudViews.Details.class)
    @GetMapping("/{id}")
    ResponseEntity getOneByID(@NotNull @PathVariable ID id);

    @NotNull
    @JsonView(SimpleCrudViews.Public.class)
    @GetMapping ResponseEntity getAll();

    @JsonView(SimpleCrudViews.Public.class)
    @NotNull @PostMapping ResponseEntity createOne(@NotNull @RequestBody T obj);

    @NotNull
    @JsonView(SimpleCrudViews.Details.class)
    @PutMapping("/{id}") ResponseEntity updateOne(@NotNull @PathVariable ID id, @NotNull  @RequestBody T obj);

    @NotNull @DeleteMapping("/{id}") ResponseEntity deleteOne(@NotNull @PathVariable ID id);
}
