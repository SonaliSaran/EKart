package com.example.eKart.repository;

import com.example.eKart.dto.ProductListDto;
import com.example.eKart.entity.ProductList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductListRepository extends JpaRepository<ProductList,Long> {
    @Query("select p from ProductList p where p.productId=?1")
    Optional<ProductList>
    findByProductId(long productId);


    @Query("select p from ProductList p where p.category = ?1")
    List<ProductList> findByCategory(String category);

    @Query("select p from ProductList p where p.category Like CONCAT('%', :category, '%')")
    List<ProductList> findByCategories(@Param("category") String category);


    @Query("select p.category from ProductList p")
    List<String> findAllCategories();
}
