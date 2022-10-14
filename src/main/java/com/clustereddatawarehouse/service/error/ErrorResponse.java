package com.clustereddatawarehouse.service.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
    private Short statusCode;
    private String param;
    private Object message;
}
