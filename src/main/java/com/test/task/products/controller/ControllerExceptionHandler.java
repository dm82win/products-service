package com.test.task.products.controller;

import com.test.task.products.controller.dto.ApiResponse;
import com.test.task.products.exception.CustomException;
import com.test.task.products.exception.impl.NotFoundException;
import com.test.task.products.service.TraceService;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ControllerExceptionHandler {

    private final TraceService traceService;

    private static final Map<Class<? extends CustomException>, HttpStatus> HTTP_STATUS_FOR_CUSTOM = Map.of(
            NotFoundException.class, HttpStatus.NOT_FOUND
    );

    private static final Map<Class<? extends Exception>, HttpStatus> HTTP_STATUS_BY_EXCEPTION = Map.of(
            BindException.class, HttpStatus.BAD_REQUEST
    );


    /**
     * В CustomException возвращаем сообщение из исключения
     * @param exc
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handler(CustomException exc) {
        HttpStatus status = HTTP_STATUS_FOR_CUSTOM.getOrDefault(exc.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        log.error("{} : Server error: {}", status.value(), exc.getMessage(), exc);
        return ResponseEntity.status(status).body(
                ApiResponse.builder()
                        .status(status.value())
                        .error(exc.getMessage())
                        .timestamp(LocalDateTime.now())
                        .requestId(traceService.getTraceId())
                        .build()
        );
    }

    /**
     * В прочих исключениях реальную причину выводим в лог
     * @param exc
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handler(Exception exc) {
        HttpStatus status = HTTP_STATUS_BY_EXCEPTION.getOrDefault(exc.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        log.error("{} : Server error: {}", status.value(), exc.getMessage(), exc);
        return ResponseEntity.status(status).body(
                ApiResponse.builder()
                        .status(status.value())
                        .error(status.getReasonPhrase())
                        .timestamp(LocalDateTime.now())
                        .requestId(traceService.getTraceId())
                        .build()
        );
    }
}
