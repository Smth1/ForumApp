package com.roma.forum.controller;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ControllerUtils {
    public ControllerUtils() {
    }

    static Map<String, String> getErros(BindingResult bindingResult) {
        Collector<FieldError, ?, Map<String, String>> collector = Collectors.toMap((fieldError) -> {
            return fieldError.getField() + "Error";
        }, DefaultMessageSourceResolvable::getDefaultMessage);
        return bindingResult.getFieldErrors().stream().collect(collector);
    }
}
