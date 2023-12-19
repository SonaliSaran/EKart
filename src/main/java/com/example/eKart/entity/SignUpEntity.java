package com.example.eKart.entity;


import com.example.eKart.dto.SignUpDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "signUp")
@Entity // insists JPA to create a table
public class SignUpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String name;
    private String phoneNumber;

    private String password;
    private String email;

    private String age;
    private String gender;

    public SignUpEntity signUpEntity(SignUpDto signUpDto){
        SignUpEntity signUpEntity = new SignUpEntity();
        signUpEntity.setUserId(signUpDto.getUserId());
        signUpEntity.setName(signUpDto.getName());
        signUpEntity.setPhoneNumber(signUpDto.getPhoneNumber());
        signUpEntity.setEmail(signUpDto.getEmail());
        signUpEntity.setAge(signUpDto.getAge());
        signUpEntity.setPassword(signUpDto.getPassword());
        signUpEntity.setGender(signUpDto.getGender());
        return signUpEntity;
    }



}
