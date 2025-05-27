package ru.webrise.handlers;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "ru/webrise/handlers")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
}
