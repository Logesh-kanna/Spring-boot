package com.project.banking.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.banking.utils.CommonUtils;
import com.project.banking.wrapper.HttpResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@ControllerAdvice
public class ErrorHandling implements AuthenticationEntryPoint {

	@Autowired
	CommonUtils utils;

	private static final Logger logger = LoggerFactory.getLogger(ErrorHandling.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthenticated error : {}", authException.getMessage());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		final Map<String, Object> body = new HashMap<String, Object>();
		body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
		body.put("error", "Unauthorized");
		body.put("message", authException.getMessage());

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), body);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<HttpResponse> handdleValidationRequest(MethodArgumentNotValidException ex) {
		List<Object> list = new ArrayList<>();
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		list.add(errors);
		return utils.error(400, "Invalid Request Body", list);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		List<Object> list = new ArrayList<>();
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Request body is missing or malformed.");
        list.add(errors);
		return utils.error(400, "Invalid Request Body", list);
    }

}
