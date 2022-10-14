package com.clustereddatawarehouse.service.error;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenericResponse<T> {
    public static final String SUCCESS_KEY = "Success";
    public static final String FAILED_KEY = "Failed";

    private String status;
    private String message;
    private T data;

}
