package com.zed.simple_crud.service.interfaces;

import com.zed.simple_crud.enumeration.OperationDeniedException;
import com.zed.simple_crud.model.CrudEntityImpl;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * Generic crud service interface
 * @class T : Object class to manage
 * @class ID : ID class
 * */
public interface CrudService<T extends CrudEntityImpl<ID>, ID> {

    /* create DB record */
    @NotNull T saveOne(@NotNull T t) throws OperationDeniedException;

    /* get DB record by ID */
    @Nullable T getOneByID(@NotNull ID id);

    /* get all DB Records */
    @NotNull Iterable<T> getAll();

    /* update DB record */
    @NotNull T updateOne(@NotNull ID id, @NotNull T t) throws OperationDeniedException;

    /* delete DB record */
    void deleteOne(@NotNull ID id);

}
