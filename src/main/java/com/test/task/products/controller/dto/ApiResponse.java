package com.test.task.products.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ApiResponse {

    private int status;

    @JsonInclude(Include.NON_NULL)
    private String error;

    @JsonInclude(Include.NON_NULL)
    private String message;
    private LocalDateTime timestamp;

    private String requestId;

}
