package com.example.eKart.utils;

import com.example.eKart.entity.SignUpEntity;
import com.example.eKart.repository.SignUpRepository;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements UserDetailsService {

    @Autowired private SignUpRepository signUpRepository;

    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("entered loadUserByUserName method ");
        SignUpEntity signUpEntity = signUpRepository.findByEmail(username);
        if (signUpEntity == null) {
            SignUpEntity signUpEntity1 = signUpRepository.findByPhoneNumber(username);
            logger.info("entered findByPhoneNumber method");
            return new org.springframework.security.core.userdetails.User(String.valueOf(signUpEntity1.getPhoneNumber()), signUpEntity1.getPassword(),
                    new ArrayList<>());
        }
        return new org.springframework.security.core.userdetails.User(signUpEntity.getEmail(), signUpEntity.getPassword(),
                new ArrayList<>());
    }

}
