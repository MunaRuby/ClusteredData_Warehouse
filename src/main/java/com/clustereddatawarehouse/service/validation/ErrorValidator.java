package com.clustereddatawarehouse.service.validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorValidator {
    private String errorKey;
    private Object[] arguments;

    List<ErrorValidator> errors = new ArrayList<>();

    public ErrorValidator addError(String errorCode, Object... args) {
        ErrorValidator errorValidator = new ErrorValidator();
        errorValidator.setErrorKey(errorCode);
        errorValidator.setArguments(args);
        errors.add(errorValidator);
        return this;
    }

    public List<ErrorValidator> build() {
        return errors;
    }
}


