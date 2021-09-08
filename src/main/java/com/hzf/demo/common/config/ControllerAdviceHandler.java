package com.hzf.demo.common.config;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
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
@RestControllerAdvice
public class ControllerAdviceHandler {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public Result<?> handler(Exception e) {
        log.error("[ControllerAdvice] ", e);
        String message = "";
        String arg = "";

        if (e instanceof BindException) {
            message = ((BindException)e).getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        }

        if (e instanceof MethodArgumentNotValidException) {
            message = ((MethodArgumentNotValidException)e).getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).limit(1).collect(Collectors.joining());
            arg = ((MethodArgumentNotValidException)e).getBindingResult().getAllErrors().stream()
                .map(o -> ((FieldError)o).getField()).limit(1).collect(Collectors.joining());
        }

        if (e instanceof ConstraintViolationException) {
            message = ((ConstraintViolationException)e).getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.joining());
        }

        if (e instanceof MissingServletRequestParameterException) {
            message = ((MissingServletRequestParameterException)e).getMessage();
        }

        if (e instanceof HttpMessageNotReadableException) {
            message = ((HttpMessageNotReadableException)e).getMessage();
        }
        return Result.of(message, new String[] {arg});
    }
}
