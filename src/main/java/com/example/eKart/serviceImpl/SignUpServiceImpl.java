package com.example.eKart.serviceImpl;

import com.example.eKart.dto.SignUpDto;
import com.example.eKart.entity.SignUpEntity;
import com.example.eKart.exception.UserAlreadyExistsException;
import com.example.eKart.repository.SignUpRepository;
import com.example.eKart.response.ResponseClass;
import com.example.eKart.response.StringConstants;
import com.example.eKart.service.SignUpService;
import com.example.eKart.utils.JwtUtils;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService  {
    @Autowired
    private SignUpRepository signUpRepository;
    @Autowired
    private JwtUtils jwtUtils;
    public ResponseEntity<ResponseClass> userSignUpService(SignUpDto signUpDto) {
        SignUpEntity signUpEntityValue = signUpRepository.findByEmailOrPhoneNumber(signUpDto.getEmail(),signUpDto.getPhoneNumber()).orElse(null);
        if(!Optional.ofNullable(signUpEntityValue).isPresent()){
            SignUpEntity signUpEntity1= new SignUpEntity();
            SignUpEntity signUpEntity = signUpRepository.save(signUpEntity1.signUpEntity(signUpDto));
            SignUpDto signUpDto1 = signUpDto.signUpDto(signUpEntity);
            ResponseClass responseClass = new ResponseClass(HttpStatus.OK,StringConstants.SIGN_UP_SUCCESS,signUpDto1);
            return new ResponseEntity<ResponseClass>(responseClass, HttpStatus.OK);
        }
        else {
            throw new UserAlreadyExistsException("user already exists in the same mail id or phone number");
        }
    }

    public ResponseEntity<ResponseClass> loginService(SignUpDto signUpDto) {
        ResponseClass responseClass = null;
        SignUpEntity signUpEntityValue = signUpRepository.findByEmailOrPhoneNumber(signUpDto.getEmail(), signUpDto.getPhoneNumber()).orElseThrow(() -> new NoSuchElementException("element not present"));
        SignUpDto signUpDto1 = signUpDto.signUpDto(signUpEntityValue);
        System.out.println(Optional.ofNullable(signUpEntityValue.getPassword()));
        if (!signUpEntityValue.getPassword().equals(signUpDto.getPassword())){
            throw new NoSuchElementException("invalid password ");
        }
        String token = jwtUtils.generateJWT(signUpDto1);
        responseClass = new ResponseClass(HttpStatus.OK, StringConstants.LOGGED_IN_SUCCESS, token);
        return new ResponseEntity<ResponseClass>(responseClass, HttpStatus.OK);
    }

    public ResponseEntity<ResponseClass> logout(String token) {
        jwtUtils.invalidatingToken(token);
        ResponseClass responseClass = new ResponseClass(HttpStatus.OK, StringConstants.LOGOUT_SUCCESS);
        return new ResponseEntity<ResponseClass>(responseClass, HttpStatus.OK);

    }
}
