package com.example.demo.global.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Data
public class ErrorResponse {
    private boolean check;

    private Object error;

    @Builder
    public ErrorResponse(boolean check, Object error) {
        this.check = check;
        this.error = error;
    }
}
