package com.example.eKart.dto;

import com.example.eKart.entity.SignUpEntity;
import com.sun.istack.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class SignUpDto {

    private long userId;
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String age;
    @NotNull
    private String gender;


    public SignUpDto signUpDto(SignUpEntity signUpEntity){
        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setUserId(signUpEntity.getUserId());
        signUpDto.setName(signUpEntity.getName());
        signUpDto.setPhoneNumber(signUpEntity.getPhoneNumber());
        signUpDto.setEmail(signUpEntity.getEmail());
        signUpDto.setPassword(signUpEntity.getPassword());
        signUpDto.setAge(signUpEntity.getAge());
        signUpDto.setGender(signUpEntity.getGender());
        return signUpDto;
    }
}
