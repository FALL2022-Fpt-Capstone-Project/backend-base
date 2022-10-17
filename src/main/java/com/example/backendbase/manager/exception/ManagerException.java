package com.example.backendbase.manager.exception;

public class ManagerException extends Exception {

    public ManagerException(String msg) {
        super(msg);
    }

    public ManagerException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public ManagerException() {
    }
}
