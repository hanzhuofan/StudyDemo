package com.hzf.demo.common.config;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.hzf.demo.common.validation.ValidatorHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hzf.demo.common.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhuofan.han
 * @date 2021/9/4
 */
@Slf4j
@ResponseStatus(HttpStatus.OK)
@RestControllerAdvice
public class ControllerAdviceHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handler(final MethodArgumentNotValidException e) {
        log.error("", e);
        String arg = e
            .getBindingResult().getAllErrors().stream().map(o -> ValidatorHelper
                .parseProperty(((FieldError)o).getField(), ((FieldError)o).getRejectedValue(), o.getDefaultMessage()))
            .collect(Collectors.joining());
        return Result.of("validator.tip", new String[] {arg});
    }

    @ExceptionHandler(BindException.class)
    public Result<?> bindExceptionHandler(final BindException e) {
        log.error("", e);
        String arg = e
            .getBindingResult().getAllErrors().stream().map(o -> ValidatorHelper
                .parseProperty(((FieldError)o).getField(), ((FieldError)o).getRejectedValue(), o.getDefaultMessage()))
            .collect(Collectors.joining());
        return Result.of("validator.tip", new String[] {arg});
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> handler(final ConstraintViolationException e) {
        log.error("", e);
        String message =
            e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return Result.of(message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> handler(MissingServletRequestParameterException e) {
        log.error("", e);
        return Result.of(e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handler(HttpMessageNotReadableException e) {
        log.error("", e);
        return Result.of(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public Result<?> handler(Exception e) {
        log.error("", e);
        return Result.of(e.getMessage());
    }
}
