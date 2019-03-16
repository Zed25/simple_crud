package com.zed.simple_crud.model.interfaces;

public interface CrudEntity<ID> {

    /* return entity id */
    ID getID();

    /* check id is the same */
    boolean sameID(ID id);

    /* update entity object*/
    boolean update(Object obj);
}
