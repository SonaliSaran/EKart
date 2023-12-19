package com.example.eKart.service;

import com.example.eKart.dto.ProductListDto;
import com.example.eKart.entity.ProductCart;
import com.example.eKart.response.ResponseClass;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface ProductService {


    ResponseEntity<ResponseClass> addAllProductsList(ProductListDto productListDto, HttpServletRequest request);

    ResponseEntity<ResponseClass> getAllProductList(HttpServletRequest request);

    ResponseEntity<ResponseClass> getProductByCategory(String category,HttpServletRequest request);

    ResponseEntity<ResponseClass> getAllCategories(HttpServletRequest request);

    ResponseEntity<ResponseClass> addItemsToCart(ProductCart productCart, HttpServletRequest request);

    ResponseEntity<ResponseClass> cartProducts(Long cartId, HttpServletRequest request);
}
