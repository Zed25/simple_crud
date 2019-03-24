package com.zed.simple_crud.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.zed.simple_crud.controller.interfaces.PaginatedCrudController;
import com.zed.simple_crud.json_views.SimpleCrudViews;
import com.zed.simple_crud.model.CrudEntityImpl;
import com.zed.simple_crud.model.interfaces.CrudEntity;
import com.zed.simple_crud.service.interfaces.PaginatedCrudService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

public abstract class PaginatedCrudControllerImpl<T extends CrudEntity<ID>, ID>
        extends CrudControllerImpl<T, ID>
        implements PaginatedCrudController<T, ID> {

    @Value("${simplecrud.page.default.size}")
    private int defaultPageSize;

    @Override
    @JsonView(SimpleCrudViews.Public.class)
    @GetMapping("/page")
    public @NotNull ResponseEntity getPage(@RequestParam int page,
                                           @RequestParam(required = false) Integer size,
                                           @RequestParam(required = false) Sort sort) {
        if (size == null || size <= 0) {
            size = this.defaultPageSize;
        }

        Page<T> elementsPage;
        if (sort != null) {
            elementsPage = this.getService().getPage(page, size, sort);
        } else {
            elementsPage = this.getService().getPage(page, size);
        }

        return new ResponseEntity<>(elementsPage, HttpStatus.OK);
    }

    @Override
    public abstract PaginatedCrudService<T, ID> getService();
}
