package com.project.banking.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleToLongFunction;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.project.banking.wrapper.HttpResponse;

@Component
public class CommonUtils {

	public String generateToken() {
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Random random = new Random();
		StringBuilder token = new StringBuilder();
		for (int i = 0; i <= 11; i++) {
			int index = random.nextInt(alphabet.length());
			token.append(alphabet.charAt(index));
		}
		return token.toString();
	}
	
	public String generateAccountNumber() {
		final BigInteger bigNumber = new BigInteger("100000000000000000000");
		Random random = new Random();
		int bitLength = 100;
		BigInteger randomBigInt = new BigInteger(bitLength, random);
		StringBuilder acc_no = new StringBuilder();
		BigInteger boundedBigInt = randomBigInt.mod(bigNumber);
        acc_no.append(boundedBigInt);
		return acc_no.toString();
	}

	public ResponseEntity<HttpResponse> success(int status, String message, List<Object> data) {
		HttpResponse response = new HttpResponse(status, message, data);
		return new ResponseEntity<HttpResponse>(response, HttpStatusCode.valueOf(status));
	}

	public ResponseEntity<HttpResponse> success(int status, String message) {
		List<Object> data = new ArrayList<>();
		HttpResponse response = new HttpResponse(status, message, data);
		return new ResponseEntity<HttpResponse>(response, HttpStatusCode.valueOf(status));
	}

	public ResponseEntity<HttpResponse> error(int status, String message) {
		List<Object> data = new ArrayList<>();
		HttpResponse response = new HttpResponse(status, message, data);
		return new ResponseEntity<HttpResponse>(response, HttpStatusCode.valueOf(status));
	}

	public ResponseEntity<HttpResponse> error(int status, String message, List<Object> data) {
		HttpResponse response = new HttpResponse(status, message, data);
		return new ResponseEntity<HttpResponse>(response, HttpStatusCode.valueOf(status));
	}
}
