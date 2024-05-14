package com.example.eKart.repository;

import com.example.eKart.entity.SignUpEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository  extends JpaRepository<SignUpEntity,Long>{
    @Query("select s from SignUpEntity s where s.email=?1 or s.phoneNumber = ?2")
    Optional<SignUpEntity> findByEmailOrPhoneNumber(String email, String phoneNumber);
    SignUpEntity save(SignUpEntity signUpEntity);

    SignUpEntity findByEmail(String username);

    SignUpEntity findByPhoneNumber(String username);
}
