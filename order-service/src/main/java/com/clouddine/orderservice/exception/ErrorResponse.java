package com.clouddine.orderservice.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
}