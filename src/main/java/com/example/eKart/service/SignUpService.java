package com.example.eKart.service;

import com.example.eKart.dto.SignUpDto;
import com.example.eKart.response.ResponseClass;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface SignUpService {
    ResponseEntity<ResponseClass> userSignUpService(SignUpDto signUpDto);

    ResponseEntity<ResponseClass> loginService(SignUpDto signUpDto);

    ResponseEntity<ResponseClass> logout(String token);
}
