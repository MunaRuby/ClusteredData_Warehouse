package com.clustereddatawarehouse.exception;

import com.clustereddatawarehouse.service.validation.ErrorValidator;

import java.util.List;

public class ValidationException extends RuntimeException {

    private final List<ErrorValidator> errorValidators;

    public ValidationException(List<ErrorValidator> errorValidators) {
        super("error");
        this.errorValidators = errorValidators;
    }

    public List<ErrorValidator> getValidatorErrors() {
        return errorValidators;
    }
}
