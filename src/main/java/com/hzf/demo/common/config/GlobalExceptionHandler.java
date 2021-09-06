package com.hzf.demo.common.config;

import com.hzf.demo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author zhuofan.han
 * @date 2021/9/4
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BindException.class)
    public Result<?> bindExceptionHandler(final BindException e) {
        log.error("", e);
        String message = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return Result.of(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handler(final MethodArgumentNotValidException e) {
        log.error("", e);
        String message = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).limit(1).collect(Collectors.joining());
        String arg = e.getBindingResult().getAllErrors().stream().map(o -> ((FieldError)o).getField()).limit(1)
            .collect(Collectors.joining());
        return Result.of(message, new String[]{arg});
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<?> handler(final ConstraintViolationException e) {
        log.error("", e);
        String message =
            e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return Result.of(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> handler(MissingServletRequestParameterException e) {
        log.error("", e);
        return Result.of(e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handler(HttpMessageNotReadableException e) {
        log.error("", e);
        return Result.of(e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result<?> handler(Exception e) {
        log.error("", e);
        return Result.of(e.getMessage());
    }
}
