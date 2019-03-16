package com.zed.simple_crud.service;

import com.zed.simple_crud.model.CrudEntityImpl;
import com.zed.simple_crud.repository.PaginatedCrudRepository;
import com.zed.simple_crud.service.interfaces.PaginatedCrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;

public abstract class PaginatedCrudServiceImpl<T extends CrudEntityImpl<ID>, ID>
        extends CrudServiceImpl<T, ID> implements PaginatedCrudService<T, ID> {

    @Override
    @NotNull public Page<T> getPage(@NotNull int page, @NotNull int size) {
        return this.getPage(page, size, Sort.unsorted());
    }

    @Override
    @NotNull public Page<T> getPage(@NotNull int page, @NotNull int size, @NotNull Sort sort) {
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        return this.getRepo().findAll(pageRequest);
    }

    @Override
    @NotNull public abstract PaginatedCrudRepository<T, ID> getRepo();
}
