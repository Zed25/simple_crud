package com.zed.simple_crud.service;

import com.zed.simple_crud.enumeration.OperationDeniedException;
import com.zed.simple_crud.model.CrudEntityImpl;
import com.zed.simple_crud.model.interfaces.CrudEntity;
import com.zed.simple_crud.service.interfaces.CrudService;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;

public abstract class CrudServiceImpl<T extends CrudEntity<ID>, ID> implements CrudService<T, ID> {

    /**
     * ----------------------------------------
     * OVERRIDE THIS METHODS ONLY IF NECESSARY
     * ----------------------------------------
     * */

    @Override
    public T saveOne(@NotNull T t) throws OperationDeniedException {
        boolean exists = false;
        if (t.getID() != null) {
            exists = this.getRepo().existsById(t.getID());
        }
        if (!exists) return this.getRepo().save(t);
        else throw new OperationDeniedException("This element already exists");
    }

    @Override
    public T getOneByID(@NotNull ID id) {
        return this.getRepo().findById(id).orElse(null);
    }

    @Override
    public Iterable<T> getAll() {
        return this.getRepo().findAll();
    }

    @Override
    public T updateOne(@NotNull ID id, @NotNull  T t) throws OperationDeniedException {
        T toUpdate = this.getRepo().findById(id).orElse(null);
        if (toUpdate != null) {
            if (toUpdate.sameID(t.getID())) {
                if (toUpdate.update(t)) {
                    return this.getRepo().save(toUpdate);
                } else {
                    throw new OperationDeniedException("Update passed could be dangerous for this element");
                }
            } else throw new OperationDeniedException("ID passed is different from account one");
        }  else {
            throw new OperationDeniedException("no element whit this ID recorded");
        }
    }

    @Override
    public void deleteOne(@NotNull ID id) {
        this.getRepo().deleteById(id);
    }

    @NotNull public abstract CrudRepository<T, ID> getRepo();
}
