package com.example.eKart.controller;

import com.example.eKart.dto.ProductListDto;
import com.example.eKart.entity.ProductCart;
import com.example.eKart.response.ResponseClass;
import com.example.eKart.service.ProductService;
import com.example.eKart.serviceImpl.ProductServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class productController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addProducts")
    public ResponseEntity<ResponseClass> addAllProducts( @RequestBody ProductListDto productListDto, HttpServletRequest request){
        return productService.addAllProductsList(productListDto,request);
    }

    @GetMapping("/viewAllProducts")
    public ResponseEntity<ResponseClass> viewAllProduct(HttpServletRequest request){
        return productService.getAllProductList(request);
    }

    @GetMapping("/filterByProduct")
    public ResponseEntity<ResponseClass> filterProductByCategory(@RequestParam String category,HttpServletRequest request){
        return productService.getProductByCategory(category,request);
    }

    @GetMapping("/filterAllCategories")
    public ResponseEntity<ResponseClass> filterProductsByAllCategories(HttpServletRequest request){
        return productService.getAllCategories(request);
    }

    @PostMapping("/addToCart")
    public ResponseEntity<ResponseClass> addProductsToCart(@RequestBody ProductCart productCart,HttpServletRequest request){
        return productService.addItemsToCart(productCart,request);
    }

    @GetMapping("/viewAllCartProducts")
    public ResponseEntity<ResponseClass> viewAllProductsInCart(@RequestParam Long cartId,HttpServletRequest request){
        return productService.cartProducts(cartId,request);
    }


}
