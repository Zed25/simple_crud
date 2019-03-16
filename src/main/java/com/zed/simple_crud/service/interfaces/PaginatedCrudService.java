package com.zed.simple_crud.service.interfaces;

import com.zed.simple_crud.model.CrudEntityImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;

/**
 * Generic crud service interface
 * @class T : Object class to manage
 * @class ID : ID class
 * */
public interface PaginatedCrudService<T extends CrudEntityImpl<ID>, ID> extends CrudService<T, ID> {

    @NotNull Page<T> getPage(@NotNull int page, @NotNull int size);

    @NotNull Page<T> getPage(@NotNull int page, @NotNull int size, @NotNull Sort sort);

}
