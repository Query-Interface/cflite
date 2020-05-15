package com.queryinterface.cflite.cloudcontroller.common;

import java.util.ArrayList;
import java.util.List;

public class Errors {
    private List<Error> errors = new ArrayList<>();

    public void addError(Error error) {
        errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
