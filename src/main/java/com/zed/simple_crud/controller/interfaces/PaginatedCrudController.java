package com.zed.simple_crud.controller.interfaces;

import com.fasterxml.jackson.annotation.JsonView;
import com.zed.simple_crud.json_views.SimpleCrudViews;
import com.zed.simple_crud.model.CrudEntityImpl;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

public interface PaginatedCrudController<T extends CrudEntityImpl<ID>, ID> extends CrudController<T, ID> {

    @NotNull
    @JsonView(SimpleCrudViews.Public.class)
    @GetMapping("/page")
    ResponseEntity getPage(@RequestParam int page,
                           @RequestParam(required = false) Integer size,
                           @RequestParam(required = false) Sort sort);

}
