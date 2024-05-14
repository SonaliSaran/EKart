package com.example.eKart.repository;

import com.example.eKart.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCartRepository extends JpaRepository <ProductCart,Long> {
    @Query("select p from ProductCart p where p.cartId= ?1")
    ProductCart findByCartId(Long cartId);
}
