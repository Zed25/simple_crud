package com.zed.simple_crud.enumeration;

public class OperationDeniedException extends Exception {
    public OperationDeniedException(String msg) {
        super(msg);
    }
}
