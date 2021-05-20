package com.compassouol.error;

import java.util.Date;
import java.util.Set;

public class ApiErrorDTO {
    //public Date timestamp;
    public Integer status_code;
    public Set<ErrorDTO> errors;

    public ApiErrorDTO() {
    }

    public ApiErrorDTO(Date timestamp, Integer status_code, Set<ErrorDTO> errors) {
        //this.timestamp = timestamp;
        this.status_code = status_code;
        this.errors = errors;
    }
}