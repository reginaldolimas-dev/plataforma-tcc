package com.br.productservice.advice;

import com.br.productservice.dto.ApiResponse;
import com.br.productservice.filter.RequestTimingFilter;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ApiResponseWrapperAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !ApiResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        long elapsed = elapsedSince(request);
        return ApiResponse.success(body, "Operação realizada com sucesso", elapsed);
    }

    private long elapsedSince(ServerHttpRequest request) {
        Long start = (Long) ((ServletServerHttpRequest) request)
                .getServletRequest()
                .getAttribute(RequestTimingFilter.START_TIME_ATTRIBUTE);
        return start != null ? System.currentTimeMillis() - start : 0;
    }
}